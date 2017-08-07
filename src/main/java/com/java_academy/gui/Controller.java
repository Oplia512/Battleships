package com.java_academy.gui;

import com.java_academy.logic.json_model.JsonMessage;
import com.java_academy.logic.json_model.MarkedIndexes;
import com.java_academy.logic.json_model.Message;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.state_machine.core.OnMessageReceiverListener;
import com.java_academy.logic.tools.I18NResolver;
import com.java_academy.logic.tools.JsonParser;
import com.java_academy.network.Connector;
import com.java_academy.network.socket_provider.ClientSocketProvider;
import com.java_academy.network.socket_provider.core.SocketProvider;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class Controller implements Initializable {

	@FXML
    GridPane gridPaneShips;
    @FXML
    GridPane gridPaneShots;
    @FXML
    Label label;
    @FXML
    Label shipDestroyed;
    @FXML
    Button ready;
    @FXML
    CheckBox nukeCheckBox;
    @FXML
    ChoiceBox choiceBoxLangugage;
    @FXML
    public String ip;

    private Connector connector;

    private final View view = new View();
    private final Model model = new Model();
    private Map<Integer, Boolean> board;
    private Boolean isNukeAvailable = true;
    private String playerId;

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    private String ipAdress;

    public void createFleetRandomly(Map<Integer, Boolean> board, boolean isMy) {
    	boolean isMissed = true;
        if(isMy) {
        	isMissed = false;
            for (Node node : gridPaneShips.getChildren()) {
                if (node instanceof Pane) {
                    for(Map.Entry<Integer, Boolean> entry: board.entrySet()){
                        if((Integer.valueOf(entry.getKey() + 100)).equals(transformationOfSourceIntoInteger((node).getId()))) {
                            if(entry.getValue()) {
                                if(board.size() > 9) {
                                    view.drawShips((Pane)node);
                                } else {
                                    view.drawShot((Pane)node);
                                }
                            } else {
                                view.drawMiss((Pane)node);
                            }
                        }
                    }
                }
            }
        } else {
            for (Node node : gridPaneShots.getChildren()) {
                if (node instanceof Pane) {
                    for (Map.Entry<Integer, Boolean> entry : board.entrySet()) {
                        if (entry.getKey().equals(transformationOfSourceIntoInteger((node).getId()))) {
                            if (!entry.getValue()) {
                                view.drawMiss((Pane) node);
                                node.setDisable(true);
                            }
                        }
                    }
                }
            }
            for (Node node : gridPaneShots.getChildren()) {
                if (node instanceof Pane) {
                    for (Map.Entry<Integer, Boolean> entry : board.entrySet()) {
                        if (entry.getKey().equals(transformationOfSourceIntoInteger(((Pane) node).getId()))) {
                            if (entry.getValue()) {
                                view.drawShot((Pane) node);
                                node.setDisable(true);
                                isMissed = false;
                            }
                        }
                    }
                }
            }
        }
        if(isMissed) {
            connector.sendMessage(new MessageObject(null, "to switchBlockingBoard"));
            shipDestroyed.setVisible(false);
        }
        if(board.size() > 9 && playerId.equals("FIRST_PLAYER")) {
            connector.sendMessage(new MessageObject(null, "to whoStartState"));
        }
    }

    public void onShootHandled(MouseEvent event) {
        Object source = event.getSource();
        Integer id = transformationOfSourceIntoInteger(source);
        if(nukeCheckBox.isSelected() && isNukeAvailable) {
        	connector.sendMessage(new MessageObject(null, "n" + id));
        	connector.sendMessage(new MessageObject(null, "n" + id));

        } else {
        	connector.sendMessage(new MessageObject(null, "" + id));
        	connector.sendMessage(new MessageObject(null, "" + id));
        }

    }

    public Integer transformationOfSourceIntoInteger(Object o) {
        return Integer.valueOf(o.toString().replaceAll("\\D+", ""));
    }

    public void connectToServer() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(getIp(), 4000);
        startListeningFromServer();
        connector.connect(inetSocketAddress);
        connector.sendMessage(new MessageObject(null, "dziala"));
        connector.sendMessage(new MessageObject(null, "polaczylem sie prosze o statki"));
        disableVisibilityOfComponents();
    }

    private void startListeningFromServer() {
        connector.addMessageReceiverListenerToSocketProvider(new OnMessageReceiverListener() {
            @Override
            public void onMessageReceived(Supplier<String> messageSupplier) {
                String json = messageSupplier.get();

                JsonMessage jsonMsg = JsonParser.decide(json);
                if (jsonMsg instanceof MarkedIndexes) {
                    MarkedIndexes mi = ((MarkedIndexes)jsonMsg);
                    setIsNukeAvailable(mi);
                    if(mi.getHitAndSink()) {
                        view.setLabelText("ship.destroyed", shipDestroyed);
                        shipDestroyed.setVisible(true);
                        if(mi.getEndOfGame()) { //tutaj żeby wysłało tylko do 1 clienta
                        	connector.sendMessage(new MessageObject(null, "end! show me result"));
                        }
                    }
                    board = mi.getMap();
                    createFleetRandomly(board, mi.isMyBoard());
                } else {
                    view.setLabelText(((Message)jsonMsg).getMessage(), label);

                    if(((Message)jsonMsg).getMessage().equals("who.start")){
                        setButtonsDisabled(false);
                    }
                    if(((Message)jsonMsg).getMessage().equals("new.game")) {
                    	playerId = ((Message)jsonMsg).getPlayer();
                    }
                    if(((Message)jsonMsg).getMessage().equals("not.your.turn")) {
                        setButtonsDisabled(true);
                    }
                    if(((Message)jsonMsg).getMessage().equals("your.turn")) {
                        setButtonsDisabled(false);
                        shipDestroyed.setVisible(false);
                    }
                    if(((Message)jsonMsg).getMessage().equals("you.win") || ((Message)jsonMsg).getMessage().equals("you.lose")) {
                        Platform.runLater(() -> {
                            try {
                                showEndingWindow(((Message)jsonMsg).getMessage());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        //TODO SEND message to server about end of game from each player
                    }
                }
            }
        });
    }

    public void setLocale() {
        if(choiceBoxLangugage.getValue().equals("Polish")) {
        	I18NResolver.updateLocale(new Locale("pl", "PL"));
        } else {
        	I18NResolver.updateLocale(new Locale("en", "EN"));
        } 
    }

    public void setIsNukeAvailable(MarkedIndexes mi) {
    	isNukeAvailable = mi.getIsNukeAvailable();
    	if(!isNukeAvailable) {
    		nukeCheckBox.setDisable(true);
    	}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        I18NResolver.getI18NResolverInstance();
        Socket socket = new Socket();
        SocketProvider socketProvider = new ClientSocketProvider(socket);
        connector = new Connector(socketProvider);
        setButtonsDisabled(true);
        view.setLabelText("connect.to.server",label);
        label.setVisible(false);
        shipDestroyed.setVisible(false);
    }

    private void setButtonsDisabled(boolean flag) {
        nukeCheckBox.setDisable(flag);
        gridPaneShips.setDisable(flag);
        gridPaneShots.setDisable(flag);
     }

    private void disableVisibilityOfComponents(){
        ready.setVisible(false);
        label.setVisible(true);
    }

    private void showEndingWindow(String message) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EndingWindow.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Thanks for playing");
        EndingWindowController controller = fxmlLoader.getController();
        controller.label.setText(I18NResolver.getMsgByKey(message));
        stage.show();
    }

    public Connector getConnector() {
        return connector;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    private String getIp() {
        return ip;
    }
}
