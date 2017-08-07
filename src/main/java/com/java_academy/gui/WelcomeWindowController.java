package com.java_academy.gui;


import com.java_academy.Main;
import com.java_academy.ServerApp;
import com.java_academy.logic.tools.I18NResolver;
import com.java_academy.network.Connector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Bartlomiej Janik
 * @since 8/4/2017
 */
public class WelcomeWindowController {

	@FXML
    AnchorPane anchor;
	
    public void connectToTheServer() throws IOException {
        showGui();
    }

    public void createServerAndConnectClient()  {
        ServerApp serverApp = new ServerApp();
        Thread serverThread = new Thread(serverApp);
        serverThread.start();

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        showGui();
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

        Stage stageClosing = (Stage) anchor.getScene().getWindow();
        stageClosing.close();
    }
}


