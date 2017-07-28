package com.java_academy.logic.attacks;

/**
 * @author Bartłomiej Janik
 */
public class NukeAttack implements Attack {

    @Override
    public void attack(int position) {
    	System.out.println("Nuke on position: " + position);
    }
}
