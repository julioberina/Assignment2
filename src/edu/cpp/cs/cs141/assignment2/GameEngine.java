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
    private String[] dungeon;
    private int position;
    
    public GameEngine()
    {
        player = new Player();
        enemy = new Enemy();
        
        dungeon = new String[11];
        dungeon[0] = "#   ";
        for (int i = 1; i < 11; i++) {
            if (i == 10)
                dungeon[i] = "|";
            else
                dungeon[i] = "_   ";
        }
        
        position = 0;
    }
    
    public void assignPlayerWeapon(int choice)
    {
        switch (choice)
        {
            case 1:
                player.assignWeapon(new Gun("Pistol"));
                break;
            case 2:
                player.assignWeapon(new Gun("Rifle"));
                break;
            case 3:
                player.assignWeapon(new Gun("Shotgun"));
                break;
        }
    }
    
    public boolean playerAlive()
    {
        return player.isAlive();
    }
    
    public boolean stillInDungeon()
    {
        return (!dungeon[10].equals("@"));
    }
    
    public String[] getDungeon()
    {
        return dungeon;
    }
    
    public int getPlayerHP()
    {
        return player.getHP();
    }
    
    public String getPlayerWeapon()
    {
        return player.getGun().getWeaponType();
    }
    
    public String getPlayerAmmo()
    {
        return player.getGun().getAmmo();
    }
}
