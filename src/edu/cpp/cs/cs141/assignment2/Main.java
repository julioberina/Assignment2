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
 * This is the {@link Main} class, which only serves to run the game loop.
 * @author jmb
 */

public class Main {
    
    /**
    This is the main method for the class. It takes a String array of 
    command-line arguments if any and runs the game loop.
    
    @param args     Command-line arguments from the executable call.
    */
    public static void main(String[] args)
    {
        UserInterface ui = new UserInterface();
        ui.run();
    }
}
