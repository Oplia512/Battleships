package com.java_academy.logic.attacks;

/**
 * This interface provides possibility to make different types of attack
 * @author Bartłomiej Janik
 */
@FunctionalInterface
public interface Attack {
	
    void attack(int position);
    
}