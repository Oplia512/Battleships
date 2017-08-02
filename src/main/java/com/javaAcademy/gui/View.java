package com.javaAcademy.gui;

import javafx.scene.layout.Pane;


public class View {

    public void drawShips(Pane pane){
        pane.setStyle("-fx-background-color: black");
    }

    public void drawMiss(Pane pane){
    pane.setStyle("-fx-background-color: cornflowerblue");
    }

    public void drawShot(Pane pane){
        pane.setStyle("-fx-background-color: red");
    }
}
