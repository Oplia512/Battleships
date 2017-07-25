package com.java_academy.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URI url=new File("src/main/java/com/java_academy/gui/board.fxml").toURI();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(url.toURL());
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(new Scene(root, 1050, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
