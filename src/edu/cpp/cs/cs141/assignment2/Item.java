/***
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
 * This class represents the {@link Item} class, which is instantiated and 
 * assigned to an {@link Enemy}.
 *
 * @author jmb
 */

public class Item {
    
    /**
    This field represents what kind of item an {@link Enemy} holds.
    */
    private String type;
    
    /**
    The constructor for the class.  It takes in an integer and assings either
    a health pack or a max ammo magazine to {@link Enemy}.
    
    @param item     Integer representation of health or max ammo.
    */
    public Item(int item)
    {
        if (item >= 0 && item <= 2)
           type = "health";
        else
           type = "maxammo";
    }
    
    /**
    This method simply returns the type of {@link Item} held by {@link Enemy}.
    
    @return     The type of item held.
    */
    public String getType()
    {
        return type;
    }
    
    /**
    This method may either assign, reassign, or take away an existing
    {@link Item} currently held by the {@link Enemy}.
    
    @param item     Integer representation of what item will be held.
    */
    public void reassignTo(int item)
    {
        if (item >= 0 && item <= 2)
            type = "health";
        else if (item == -1)
            type = "none";
        else
            type = "maxammo";
    }
}
