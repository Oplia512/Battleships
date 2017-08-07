package com.java_academy;


import com.java_academy.gui.Controller;
import com.java_academy.logic.Game;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.tools.BSLog;
import com.java_academy.network.Connector;
import com.java_academy.network.socket_provider.ServerSocketProvider;
import com.java_academy.network.socket_provider.core.SocketProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/WelcomeWindow.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
