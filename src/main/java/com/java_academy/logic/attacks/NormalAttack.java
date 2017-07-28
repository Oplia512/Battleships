package com.java_academy.logic.attacks;

/**
 * @author Bartłomiej Janik
 */
public class NormalAttack implements Attack {
	
    @Override
    public void attack(int position) {
        System.out.println(position);
    }
}
