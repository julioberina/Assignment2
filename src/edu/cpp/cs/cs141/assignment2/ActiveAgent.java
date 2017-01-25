/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs141.assignment2;

/**
 *
 * @author jmb
 */
public abstract class ActiveAgent {
    
    private int hitPoints;
    private Gun gun;
    
    public ActiveAgent(int hp)
    {
        hitPoints = hp;
    }
    
    public boolean isAlive()
    {
        return (hitPoints > 0);
    }
    
    public boolean isDead()
    {
        return (hitPoints <= 0);
    }
    
    public void assignWeapon(Gun gun)
    {
        this.gun = gun;
    }
    
    public void takeDamage(int damage)
    {
        hitPoints -= damage;
    }
    
    public void shoot(ActiveAgent agent)
    {
        gun.shoot(agent);
    }
    
    public Gun getGun()
    {
        return gun;
    }
    
    public int getHP()
    {
        return hitPoints;
    }
}
