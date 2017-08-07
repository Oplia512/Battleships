package com.java_academy.gui;


import com.java_academy.logic.Game;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.tools.BSLog;
import com.java_academy.network.Connector;
import com.java_academy.network.socket_provider.ServerSocketProvider;
import com.java_academy.network.socket_provider.core.SocketProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * @author Bartlomiej Janik
 * @since 8/4/2017
 */
public class WelcomeWindowController {

	@FXML
    AnchorPane anchor;
    @FXML
    TextField ipTextField;
	
    public void connectToTheServer() throws IOException {
        showGui();
    }

    public void createServerAndConnectClient()  {
        Server serverApp = new Server();
        Thread serverThread = new Thread(serverApp);
        serverThread.start();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            BSLog.info(BSLog.getLogger(getClass()), "Client sleep");
        }

        showGui();
    }

    private class Server implements Runnable {
        public Connector connector;

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket();
                SocketProvider socketProvider = new ServerSocketProvider(serverSocket);
                InetSocketAddress inetSocketAddress = new InetSocketAddress(ipTextField.getText(), 4000);
                connector = new Connector(socketProvider);
                BSLog.info(BSLog.getLogger(getClass()), "Server is up and running");
                Game game = new Game(connector::sendMessage, () -> connector.closeConnection());
                connector.addMessageReceiverListenerToSocketProvider(game);
                if (connector.connect(inetSocketAddress)) {
                    game.startGame();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showGui()  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/board.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Battleships");
        stage.show();
        Controller controller = fxmlLoader.getController();

        stage.setOnCloseRequest(event -> {
            Connector connection = controller.getConnector();
                if(connection != null){
                    connection.sendMessage(new MessageObject(null, "CLOSE"));
                    connection.closeConnection();
                }
        });

        Stage stageClosing = (Stage) anchor.getScene().getWindow();
        stageClosing.close();
    }
}


