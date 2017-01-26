/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs141.assignment2;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jmb
 */
public class Enemy extends ActiveAgent {
    
    private Item item;
    private List<Integer> spawnWeapon;
    private int weaponSpot;
    
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
    
    public void respawn(int itemType)
    {
        item.reassignTo(itemType);
        giveWeapon();
    }
    
    public String getItem()
    {
        return item.getType();
    }
    
    public void dropItem()
    {
        item.reassignTo((-1));
    }
}
