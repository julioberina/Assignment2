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
 * This class represents generatlizations for {@link Player} or {@link Enemy} as
 * an abstract entity.
 * 
 * @author jmb
 */

public abstract class ActiveAgent {
    
    /**
    This field represents the amount of health that the {@link ActiveAgent}
    currently has.
    */
    private int hitPoints;
    
    /**
    This field represents the {@link Gun} currently possessed by
    {@link ActiveAgent}
    */
    private Gun gun;
    
    /**
    This is the constructor for the class. It takes an integer and assigns it
    to the hitPoints field. 
    */
    public ActiveAgent(int hp)
    {
        hitPoints = hp;
    }
    
    /**
    This method returns whether the hitPoints is positive or not to
    determine if the {@link ActiveAgent} is alive.
    
    @return     {@code true} if hitPoints is positive, 
    {@code false} if zero or negative.
    */
    public boolean isAlive()
    {
        return (hitPoints > 0);
    }
    
    /**
    This method returns whether the hitPoints is zero or negative to
    determine if the {@link ActiveAgent} is dead.
    
    @return     {@code true} if hitPoints is zero or negative, 
    {@code false} if positive.
    */
    public boolean isDead()
    {
        return (hitPoints <= 0);
    }
    
    /**
    This method takes a {@link Gun} parameter and assigns it to the gun field
    for the calling {@link ActiveAgent}
    
    @param gun      The {@link Gun} to be assigned to {@link ActiveAgent}.
    */
    public void assignWeapon(Gun gun)
    {
        this.gun = gun;
    }
    
    /**
    This method takes an integer and deducts it from hitPoints to simulate the
    concept of damage.
    
    @param damage       The amount of damage inflicted by the {@link Gun} of 
    {@link ActiveAgent}
    */
    public void takeDamage(int damage)
    {
        hitPoints -= damage;
    }
    
    /**
    This method takes an {@link ActiveAgent} parameter with the calling agent
    trying to "shoot" the target agent by calling the {@link #shoot} method on
    gun.
    
    @param agent    The target agent to be shot at.
    @return     {@code true} if successful, {@code false} if failed.
    */
    public boolean shoot(ActiveAgent agent)
    {
        return gun.shoot(agent);
    }
    
    /**
    This method simply returns the {@link Gun} of the calling {@link ActiveAgent}
    
    @return     The {@link ActiveAgent}'s {@link Gun}.
    */
    public Gun getGun()
    {
        return gun;
    }
    
    /**
    This method returns the hitPoints of the calling {@link ActiveAgent}.
    
    @return     The hitPoints of the {@link ActiveAgent}.
    */
    public int getHP()
    {
        return hitPoints;
    }
    
    /**
    This method takes an integer and "restores" hitPoints by assigning
    it to the integer parameter
    
    @param hp   The maximum hitPoints for the child of {@link ActiveAgent}.
    */
    public void restoreHP(int hp)
    {
        hitPoints = hp;
    }
}
