package com.java_academy.gui;

import com.java_academy.logic.jsonModel.Message;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.OnMessageReceiverListener;
import com.java_academy.logic.tools.JsonParser;
import com.java_academy.network.Connector;
import com.java_academy.network.socket_provider.ClientSocketProvider;
import com.java_academy.network.socket_provider.core.SocketProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class Controller implements Initializable {

    @FXML
    GridPane gridPaneShips;
    @FXML
    GridPane gridPaneShots;
    @FXML
    Button nukeButton;
    @FXML
    Button randomizer;
    @FXML
    TextField ip;
    @FXML
    TextField host;

    private Connector connector;

    private final View view = new View();
    private final Model model = new Model();
    private List<Integer> board;
    private static final int myShipsBoardStartId = 100;

    public void createFleetRandomly() {
        for (Node n : gridPaneShips.getChildren()) {
            if (n instanceof Pane) {
                for (Integer shipIndex : board) {
                    if ((transformationOfSourceIntoInteger(n)) - myShipsBoardStartId == shipIndex) {
                        view.drawShips((Pane) n);
                    }
                }
            }
        }
        randomizer.setDisable(true);
    }

    public void showNuke() {
        System.out.println("nukeeee");
    }

    public void onShootHandled(MouseEvent event) {
        Object source = event.getSource();
        int id = transformationOfSourceIntoInteger(source);
        if (board.contains(id))
            view.drawShot((Pane) source);
        else
            view.drawMiss((Pane) source);
    }

    public void onShipPlaceHandled(MouseEvent event) {
        System.out.println("event = ship placed");
    }

    public Integer transformationOfSourceIntoInteger(Object o) {
        return Integer.valueOf(o.toString().replaceAll("\\D+", ""));
    }

    public void connectToServer() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 3000);
        startListeningFromServer();
        connector.connect(inetSocketAddress);
        connector.sendMessage(new MessageObject(null, "dziala"));
        setButtonsDisabled(false);

    }

    private void startListeningFromServer() {
        connector.addMessageReseiverListenerToSocketProvider(new OnMessageReceiverListener() {
            @Override
            public void onMessageReceived(Supplier<String> messageSupplier) {
                String json = messageSupplier.get();
                Message message = JsonParser.parseMessageFromJson(json);
                if(message.getDataType().equals("INFORMATION")){
                    //do something
                } else {
                    // do something else
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.createRandomizer();
        board = model.getShips();
        Socket socket = new Socket();
        SocketProvider socketProvider = new ClientSocketProvider(socket);
        connector = new Connector(socketProvider);
        setButtonsDisabled(true);
    }

    private void setButtonsDisabled(boolean flag) {
        randomizer.setDisable(flag);
        nukeButton.setDisable(flag);
        gridPaneShips.setDisable(flag);
        gridPaneShots.setDisable(flag);
    }
}
