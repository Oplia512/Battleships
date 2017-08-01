package com.java_academy.logic.attacks;

import com.java_academy.logic.jsonModel.MarkedIndexes;

/**
 * This interface provides possibility to make different types of attack
 * @author Bartłomiej Janik
 */
@FunctionalInterface
public interface Attack {
	
	MarkedIndexes attack(Integer position);
    
}