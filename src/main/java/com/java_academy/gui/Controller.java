package com.java_academy.gui;

import javafx.scene.input.MouseEvent;

public class Controller {


    public void showNuke() {
        System.out.println("nukeeee");
    }

    public void onShootHandled(MouseEvent event){
        System.out.println("event = cell pressed");
    }

    public void onShipPlaceHandled(MouseEvent event){
        System.out.println("event = ship placed");
    }
}
