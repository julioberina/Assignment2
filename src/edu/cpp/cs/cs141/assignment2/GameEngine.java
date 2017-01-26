/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs141.assignment2;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jmb
 */
public class GameEngine {
    
    private Player player;
    private Enemy enemy;
    private String[] dungeon;
    private Random rand;
    private List<Boolean> encounterChances;
    private int position;
    private boolean battle;
    
    public GameEngine()
    {
        player = new Player();
        enemy = null;
        battle = false;
        
        dungeon = new String[11];
        dungeon[0] = "#   ";
        for (int i = 1; i < 11; i++) {
            if (i == 10)
                dungeon[i] = "|";
            else
                dungeon[i] = "_   ";
        }
        
        position = 0;
        setEncounterChances();
    }
    
    public void setEncounterChances()
    {
        encounterChances = new ArrayList<Boolean>();
        
        for (int i = 0; i < 15; i++)
            encounterChances.add(true);
        for (int i = 15; i < 100; i++)
            encounterChances.add(false);
        for (int i = 0; i < 3; i++)
            Collections.shuffle(encounterChances);
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
    
    public boolean stillInDungeon()
    {
        return (!dungeon[10].equals("@"));
    }
    
    public String[] getDungeon()
    {
        return dungeon;
    }
    
    public Player getPlayer()
    {
        return player;
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
    
    public Enemy getEnemy()
    {
        return enemy;
    }
    
    public int getEnemyHP()
    {
        return enemy.getHP();
    }
    
    public String getEnemyWeapon()
    {
        return enemy.getGun().getWeaponType();
    }
    
    public String getEnemyAmmo()
    {
        return enemy.getGun().getAmmo();
    }
    
    public boolean escapeEnemy()
    {
        if (rand.nextInt(10) == 0)
        {
            enemy = null;
            return true;
        }
        else
            return false;
    }
    
    public void movePlayer()
    {
        ++position;
        dungeon[position-1] = "_   ";
        
        if (position == 10)
            dungeon[position] = "@";
        else
            dungeon[position] = "#   ";
    }
    
    public boolean encounterOccurred()
    {
        return encounterChances.get(position);
    }
    
    public void initiateBattle()
    {
        battle = true;
    }
    
    public void simulateEnemyAttack()
    {
        enemy.shoot(player);
    }
    
    public boolean battleMode()
    {
        if (enemy == null)
            battle = false;
        else
        {
            if (player.isDead() || enemy.isDead())
            {
                battle = false;
                if (enemy.isDead() && enemy.getItem().equals("health"))
                    player.restoreHealth();
                else if (enemy.isDead() && enemy.getItem().equals("maxammo"))
                    player.getGun().reload();
            }
        }
        
        return battle;
    }
    
    public void spawnEnemy()
    {
        if (enemy == null)
            enemy = new Enemy(rand.nextInt(10));
        else
            enemy.respawn(rand.nextInt(10));
    }
}
