package com.java_academy.gui;

import com.java_academy.logic.tools.I18NResolver;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


class View {

    void drawShips(Pane pane){
        pane.setStyle("-fx-background-color: black");
    }

    void drawMiss(Pane pane){
        pane.setStyle("-fx-background-color: cornflowerblue");
    }

    void drawShot(Pane pane){
        pane.setStyle("-fx-background-color: red");
    }
    
     void setLabelText(String s, Label l){
        Platform.runLater(() -> l.setText(I18NResolver.getMsgByKey(s)));
    }

}
