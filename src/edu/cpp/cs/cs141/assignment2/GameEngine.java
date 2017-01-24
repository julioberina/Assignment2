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
public class GameEngine {
    private Player player;
    private Enemy enemy;
    private Gun gun;
    
    public void assignPlayerWeapon(int choice)
    {
        switch (choice)
        {
            case 1:
                gun = new Gun("Pistol");
                break;
            case 2:
                gun = new Gun("Rifle");
                break;
            case 3:
                gun = new Gun("Shotgun");
                break;
        }
    }
}
