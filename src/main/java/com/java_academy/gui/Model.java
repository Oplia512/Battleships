package com.java_academy.gui;

import com.java_academy.logic.fleet_settings.FleetBuilder;
import com.java_academy.logic.fleet_settings.ShipSetter;
import com.java_academy.logic.fleet_settings.shipyard_impl.Randomizer;
import com.java_academy.logic.model.BoardManager;
import com.java_academy.logic.model.Cell;
import com.java_academy.logic.model.Ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
