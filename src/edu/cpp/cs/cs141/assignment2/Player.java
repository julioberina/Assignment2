/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #2
 *
 * Implementation of Escape the Dungeon, a console-style turn-based game.
 *
 * Julio Berina
 */

package edu.cpp.cs.cs141.assignment2;

/**
 * This class represents a {@link Player}, the main {@link ActiveAgent} 
 * throughout the game.
 * @author jmb
 */

public class Player extends ActiveAgent {
    
    /**
    This is the constructor for the class. It passes an integer to the
    constructor for {@link ActiveAgent}, which takes an integer that represents
    the hitPoints for the {@link ActiveAgent} during creation.
    */
    public Player()
    {
        super(20);
    }
}
