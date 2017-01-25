/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs141.assignment2;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author jmb
 */
public class Gun {
    
    private String weaponType;
    private List<Boolean> weaponAccuracy;
    private int shotCounter;

    private enum MaxAmmo {
        PISTOL_MAX(15),
        RIFLE_MAX(10),
        SHOTGUN_MAX(5)
        ;
        
        private final int max;
        private MaxAmmo(int max) {
            this.max = max;
        }
        
        private int value()
        {
            return max;
        }
    }

    private MaxAmmo maxAmmo;
    private int ammo;
    
    public Gun(String type)
    {
        weaponType = type;
        shotCounter = 0;
        
        loadWeapon();
        setWeaponAccuracy();
    }
    
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
    
    public void reload()
    {
        ammo = maxAmmo.value();
    }
    
    public void shoot(ActiveAgent agent)
    {
        ammo -= 1;
        if (weaponAccuracy.get((shotCounter++ % 100)))
        {
            if (weaponType.equals("Pistol"))
                agent.takeDamage(1);
            else if (weaponType.equals("Rifle"))
                agent.takeDamage(2);
            else if (weaponType.equals("Shotgun"))
                agent.takeDamage(5);
        }
    }
    
    public boolean isEmpty()
    {
        return (ammo == 0);
    }
    
    public String getWeaponType()
    {
        return weaponType;
    }
    
    public String getAmmo()
    {
        String weaponAmmo = ammo + " / " + maxAmmo.value();
        return weaponAmmo;
    }
}