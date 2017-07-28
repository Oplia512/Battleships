package com.java_academy.logic.attacks;

/**
 * Created by Bartłomiej Janik on 7/26/2017.
 */
public class NukeAttack implements Attack {

    @Override
    public void attack(int position) {
    	System.out.println("Nuke on position: " + position);
    }
}
