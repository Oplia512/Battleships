package com.javaAcademy.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javaAcademy.logic.fleetSettings.FleetBuilder;
import com.javaAcademy.logic.fleetSettings.ShipSetter;
import com.javaAcademy.logic.fleetSettings.shipyardImpl.Randomizer;
import com.javaAcademy.logic.model.BoardManager;
import com.javaAcademy.logic.model.Cell;
import com.javaAcademy.logic.model.Ships;

/**
 * @author Bartlomiej Janik
 * @since 7/31/2017
 */
public class Model {
    private BoardManager board;
    private Randomizer rand;
    private Ships ships;
    private ShipSetter fleetSetter;


    public void createRandomizer() {
        ships = FleetBuilder.getNonLocalizedShips();
        board = new BoardManager(ships, 10);

        fleetSetter = new ShipSetter(board);
        rand = new Randomizer(ships, fleetSetter);
        rand.setFleet();
    }

    public List<Integer> getShips() {
        List<Integer> ships = new ArrayList<>();
        for(Map.Entry<Integer, Cell> entry: board.getBoardMap().entrySet()) {
            if(entry.getValue().equals(Cell.SHIP_ALIVE)) {
                ships.add(entry.getKey());
            }
        }
        return ships;
    }
}
