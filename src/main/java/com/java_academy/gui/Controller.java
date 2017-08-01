package com.java_academy.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    GridPane gridPane;
    @FXML
    Button randomizer;

    private View view = new View();
    private Model model = new Model();
    private List<Integer> board;
    private static final int myShipsBoardStartId = 100;

    public void createFleetRandomly() {
        for (Node n : gridPane.getChildren()) {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.createRandomizer();
        board = model.getShips();
    }
}
