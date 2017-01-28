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

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the {@link Gun}, which is the weapon held by an
 * {@link ActiveAgent}.
 * @author jmb
 */

public class Gun {
    
    /**
    This field is a string that represents the type of weapon chosen or
    randomly given to the {@link ActiveAgent}.
    */
    private String weaponType;
    
    /**
    This special field represents a list that will store a certain amount of
    {@code true} and {@code false} values based on how accurate the
    {@link ActiveAgent}'s weapon is. Higher accuracy weapons contain more 
    {@code true} values and lower accuracy weapons have more {@link false} ones.
    100 nodes is allocated in initialization to represent a percentage out of 100.
    */
    private List<Boolean> weaponAccuracy;
    
    /**
    This field counts the number of shots taken by the {@link ActiveAgent}.
    It's also utilized as a position in the {@link weaponAccuracy} list to
    retrieve either a hit ({@code true}) or miss ({@code false}).
    */
    private int shotCounter;

    /**
    This is an enumeration that represents the maximum ammo values for each
    weapon. It contains capitalized names with arguments that represents the
    max value, a constant field, a private constructor that assigns a value to 
    the constant field, and a method that returns the value of the constant
    field.
    */
    private enum MaxAmmo {
        /**
        These represent the value of {@link max} by assigning numbers in 
        arguments to enum constants of format (WEAPON)_MAX. 
        */
        PISTOL_MAX(15),
        RIFLE_MAX(10),
        SHOTGUN_MAX(5)
        ;
        
        /**
        This field represents the maximum ammo value provided by arguments
        from enum constants
        */
        private final int max;
        
        /**
        This private constructor takes an argument from the enum constants and
        assings it to the field {@link max}
        
        @param max  the value to be assigned to global constant max
        */
        private MaxAmmo(int max) {
            this.max = max;
        }
        
        /**
        This method simply returns the value assigned to the global constant
        max
        */
        private int value()
        {
            return max;
        }
    }

    /**
    This field is utilized by {@link #loadWeapon} to point to the right enum
    constant based on the weaponType determined by the constructor.
    */
    private MaxAmmo maxAmmo;
    
    /**
    This field represents the amount of ammo left in the weapon
    */
    private int ammo;
    
    /**
    This is the constructor for the class. It sets the weaponType to
    the value passed in the parameter and loads the weapon as well as setting
    its accuracy.
    
    @param type The type of gun chosen or randomly given to the
    {@link ActiveAgent}
    */
    public Gun(String type)
    {
        weaponType = type;
        shotCounter = 0;
        
        loadWeapon();
        setWeaponAccuracy();
    }
    
    /**
    This method assigns the right enum constant to maxAmmo and assigns
    its value to ammo based on the type of weapon in order 
    to "load the weapon"
    */
    public void loadWeapon()
    {
        if (weaponType.equals("Pistol"))
        {
            maxAmmo = MaxAmmo.PISTOL_MAX;
            ammo = maxAmmo.value();
        }
        
        else if (weaponType.equals("Rifle"))
        {
            maxAmmo = MaxAmmo.RIFLE_MAX;
            ammo = maxAmmo.value();
        }
        
        else if (weaponType.equals("Shotgun"))
        {
            maxAmmo = MaxAmmo.SHOTGUN_MAX;
            ammo = maxAmmo.value();
        }
    }
    
    /**
    This method creates 100 nodes of Boolean type.  It fills the list with a
    certain number of {@code true} and {@code false} values based on the 
    percentage accuracy of the weapon. It then shuffles the list three times.
    */
    public void setWeaponAccuracy()
    {
        weaponAccuracy = new ArrayList<Boolean>();
        for (int i = 0; i < 100; i++)
            weaponAccuracy.add(true);
        
        if (weaponType.equals("Pistol"))
            for (int i = 0; i < 25; i++) { weaponAccuracy.set(i, false); }
        
        else if (weaponType.equals("Rifle"))
            for (int i = 0; i < 35; i++) { weaponAccuracy.set(i, false); }
            
        else if (weaponType.equals("Shotgun"))
            for (int i = 0; i < 60; i++) { weaponAccuracy.set(i, false); }
        
        for (int i = 0; i < 3; i++) { Collections.shuffle(weaponAccuracy); }
    }
    
    /**
    This method refills ammo all the way to its max value based on weaponType
    */
    public void reload()
    {
        ammo = maxAmmo.value();
    }
    
    /**
    This method allows one {@link ActiveAgent} to shoot another
    {@link ActiveAgent}. If the calling agent has ammo and gets a "hit"
    ({@code true}) out of the weaponAccuracy list, the target agent
    "takes damage" via the {@link ActiveAgent#takeDamage(int)} method based on weaponType.
    One ammo is deducted whether it's "hit" or "miss". Returns {@code true} if
    "hit" and {@code false} if "miss".
    
    @param agent    The target agent that the calling agent wants to shoot.
    @return     true if "hit", false if "miss".
    */
    public boolean shoot(ActiveAgent agent)
    {
        if (ammo > 0 && weaponAccuracy.get((shotCounter++ % 100)))
        {
            --ammo;
            if (weaponType.equals("Pistol"))
                agent.takeDamage(1);
            else if (weaponType.equals("Rifle"))
                agent.takeDamage(2);
            else if (weaponType.equals("Shotgun"))
                agent.takeDamage(5);
            
            return true;
        }
        else
            return false;
    }
    
    /**
    The method checks if the weapon is empty
    
    @return     true if empty, false if not empty.
    */
    public boolean isEmpty()
    {
        return (ammo == 0);
    }
    
    /**
    This method retrieves the type of weapon assigned to weaponType and
    returns it.
    
    @return     The type of weapon in String format
    */
    public String getWeaponType()
    {
        return weaponType;
    }
    
    /**
    This method returns a String represention of ammo as a ratio with a
    combination of the number of rounds left (ammo), a slash, and
    the maximum number of rounds the {@link Gun} can hold (maxAmmo).
    
    @return     String ratio of ammo and maxAmmo
    */
    public String getAmmo()
    {
        String weaponAmmo = ammo + " / " + maxAmmo.value();
        return weaponAmmo;
    }
}