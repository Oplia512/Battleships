package com.java_academy.logic.attacks;

/**
 * Created by Bartłomiej Janik on 7/26/2017.
 */
public class NormalAttack implements Attack {
	
    @Override
    public void attack(int position) {
        System.out.println(position);
    }
}
