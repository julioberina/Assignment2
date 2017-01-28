/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs141.assignment2;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the fields and methods for an {@link Enemy} in the game.
 * 
 * @author jmb
 */
public class Enemy extends ActiveAgent {
    
    /**
    This field represents the {@link Item} assigned to the enemy during its
    creation.
    */
    private Item item;
    
    /**
    This field represents the probability of the {@link Gun} that the 
    {@link Enemy} will spawn with at creation.  100 nodes of Integer type will
    be created and filled with either a 0, 1, or 2 representing the
    pistol, rifle, or shotgun respectively.
    */
    private List<Integer> spawnWeapon;
    
    /**
    This field represents a spot in spawnWeapon. This is utilized to
    retrieve the value and assign a {@link Gun} to {@link Enemy} based on what
    value sits on top of the spot.
    */
    private int weaponSpot;
    
    /**
    The constructor for the class. It takes an integer and assigns a default
    {@link Item} to {@link Enemy} based on the value it holds. It calls the
    constructor for {@link ActiveAgent} passing in an integer for the
    hitPoints field. It also creates 100 nodes for the spawnWeapon
    list and fills it with a 0, 1, or 2, shuffles it, and assigns the weapon
    based on what weaponSpot lands on.
    
    @param itemType     Integer representation of the item to be held at creation.
    */
    public Enemy(int itemType)
    {
        super(5);
        weaponSpot = 0;
        spawnWeapon = new ArrayList<Integer>();
        
        for (int i = 0; i < 100; i++)
        {
            if (i < 50)
                spawnWeapon.add(0);
            else if (i >= 50 && i < 85)
                spawnWeapon.add(1);
            else if (i >= 85)
                spawnWeapon.add(2);
        }
        
        for (int i = 0; i < 3; i++) { Collections.shuffle(spawnWeapon); }
        giveWeapon();
        item = new Item(itemType);
    }
    
    /**
    This method assigns a {@link Gun} to {@link Enemy} based on the value of
    spawnWeapon indexed at {@link #weaponSpot}.
    */
    public void giveWeapon()
    {
        int tempWeapon = (weaponSpot++ % 100);
        
        switch (tempWeapon)
        {
            case 0:
                assignWeapon(new Gun("Pistol"));
                break;
            case 1:
                assignWeapon(new Gun("Rifle"));
                break;
            case 2:
                assignWeapon(new Gun("Shotgun"));
                break;
        }
    }
    
    /**
    This method works with an integer parameter to reassign an {@link Item} to
    {@link Enemy} when it respawns in an encounter past the first.  It also
    reissues a new {@link Gun} as well.
    
    @param itemType     Integer representation of the type of item held.
    */
    public void respawn(int itemType)
    {
        item.reassignTo(itemType);
        giveWeapon();
    }
    
    /**
    This method returns a String representation of the type of item that the
    {@link Enemy} holds.
    
    @return     The type of the {@link Item} in String form.
    */
    public String getItem()
    {
        return item.getType();
    }
    
    /**
    This method calls {@link Item#reassignTo(int)} which changes the
    item type to "none" after the {@link Player} picks up the {@link Item}.
    */
    public void dropItem()
    {
        item.reassignTo((-1));
    }
}
