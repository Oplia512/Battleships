package com.java_academy.gui;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller {


    public void showNuke() {
        System.out.println("nukeeee");
    }

    public void onShootHandled(MouseEvent event){
        Pane pane = (Pane)event.getSource();
        pane.setStyle("-fx-background-color: red;");

        System.out.println("event = [" + ((Pane)event.getSource()).getId() + "]");

    }
}
