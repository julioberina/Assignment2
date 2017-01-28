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

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a {@link GameEngine}, which passes or returns data to
 * other classes or method to represent game state.
 *
 * @author jmb
 */

public class GameEngine {
    
    /**
    This field represents the primary {@link Player} for the game.
    */
    private Player player;
    
    /**
    This field represents the first and future occurences of {@link Enemy}.
    */
    private Enemy enemy;
    
    /**
    This field is a String array representing the dungeon and the position of
    the {@link Player}.
    */
    private String[] dungeon;
    
    /**
    This field represents the random number generator used for assigning
    {@link Item}s to the {@link Enemy} and determining {@link Player} escape
    success.
    */
    private Random rand;
    
    /**
    This field represents a list of Booleans that determine whether an encounter
    will occur or not. 100 nodes is allocated then filled with {@code true} and
    {@code false} values. It is shuffled three times afterwards.
    */
    private List<Boolean> encounterChances;
    
    /**
    This is an integer representation of the {@link Player}'s position in the
    dungeon and whether or not the {@link Player} has an encounter.
    */
    private int position;
    
    /**
    This field represents the boolean value that determines if {@link Player}
    and {@link Enemy} are engaged in battle.
    */
    private boolean battle;
    
    /**
    This is the constructor for the class. It initializes all fields above.
    */
    public GameEngine()
    {
        player = new Player();
        enemy = null;
        battle = false;
        rand = new Random();
        
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
    
    /**
    This method fills the encounterChances list with boolean values
    that represents encounter or no encounter and shuffles it three times.
    */
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
    
    /**
    This method takes an integer and assigns a {@link Gun} to {@link Player}
    based on the integer's value.
    
    @param choice   Integer representation of {@link Gun} choice.
    */
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
    
    /**
    This method simply returns whether or not the {@link Player} is position at 
    the exit.
    
    @return     {@code true} if position is at the exit, {@code false} if
    anywhere else.
    */
    public boolean stillInDungeon()
    {
        return (!dungeon[10].equals("@"));
    }
    
    /**
    This method simply returns the String array that represents the
    dungeon
    
    @return     The String array representing dungeon
    */
    public String[] getDungeon()
    {
        return dungeon;
    }
    
    /**
    This method simply returns the instance of {@link Player}.
    
    @return     Instance of {@link Player}
    */
    public Player getPlayer()
    {
        return player;
    }
    
    /**
    This method simply returns the amount of hitPoints that {@link Player} has.
    
    @return     The {@link Player}'s hitPoints.
    */
    public int getPlayerHP()
    {
        return player.getHP();
    }
    
    /**
    This method simply returns the type of {@link Gun} that {@link Player}
    currently holds.
    
    @return     The type of {@link Player}'s {@link Gun}.
    */
    public String getPlayerWeapon()
    {
        return player.getGun().getWeaponType();
    }
    
    /**
    This method simply returns the ratio of {@link Player}'s {@link Gun}
    ammo to maxAmmo.
    
    @return    String ratio of ammo to maxAmmo
    */
    public String getPlayerAmmo()
    {
        return player.getGun().getAmmo();
    }
    
    /**
    This method simply returns the instance of {@link Enemy}.
    
    @return     instance of Enemy 
    */
    public Enemy getEnemy()
    {
        return enemy;
    }
    
    /**
    This method simply returns the amount of hitPoints {@link Enemy}
    currently has.
    
    @return     {@link Enemy} hitPoints.
    */
    public int getEnemyHP()
    {
        return enemy.getHP();
    }
    
    /**
    This method returns a String representation of the {@link Gun} type that
    {@link Enemy} currently holds.
    
    @return     {@link Enemy}'s {@link Gun} type.
    */
    public String getEnemyWeapon()
    {
        return enemy.getGun().getWeaponType();
    }
    
    /**
    This method returns the String ratio of ammo to maxAmmo of
    {@link Enemy}'s {@link Gun}.
    
    @return     {@link Enemy}'s {@link Gun} ammo to maxAmmo
    */
    public String getEnemyAmmo()
    {
        return enemy.getGun().getAmmo();
    }
    
    /**
    This method attempts to let the {@link Player} escape from the enemy through
    a randomly generated number from 0-9. Moves the {@link Player} back one
    as well if escape is successful.
    
    @return     {@code true} if successful escape, {@code false} if failed.
    */
    public boolean escapeEnemy()
    {
        if (rand.nextInt(10) == 0)
        {
            enemy = null;
            dungeon[position--] = "_   ";
            dungeon[position] = "#   ";
            return true;
        }
        else
            return false;
    }
    
    /**
    This method simply changes the position of {@link Player} to one forward.
    */
    public void movePlayer()
    {
        ++position;
        dungeon[position-1] = "_   ";
        
        if (position == 10)
            dungeon[position] = "@";
        else
            dungeon[position] = "#   ";
    }
    
    /**
    This method gathers the value on top of the encounterChances list
    indexed at position and returns it.
    
    @return     {@code true} if "encounter occured", {@code false} if not.
    */
    public boolean encounterOccurred()
    {
        if (encounterChances.get(position) == true)
            return true;
        else
            return false;
            
    }
    
    /**
    This method sets battle to {@code true} and creates/recreates the
    {@link Enemy}.
    */
    public void initiateBattle()
    {
        battle = true;
        spawnEnemy();
    }
    
    /**
    This method attempts to simulate the {@link Enemy} attack by calling the
    {@link Enemy} method {@link ActiveAgent#shoot(ActiveAgent)}.
    */
    public void simulateEnemyAttack()
    {
        enemy.shoot(player);
    }
    
    /**
    This method does a check on both the {@link Gun}s of {@link Player} and 
    {@link Enemy}. If both are empty, reload them both.
    */
    public void checkWeaponEmptiness()
    {
        if (player.getGun().isEmpty() && enemy.getGun().isEmpty())
        {
            player.getGun().reload();
            enemy.getGun().reload();
        }
    }
    
    /**
    This method checks if the enemy has been created and also checks which of
    the two {@link ActiveAgent}s dies.  If the {@link Enemy} dies, take the
    {@link Item} it drops and act upon its benefit based on its type.
    
    @return     {@code true} if {@link Player} and {@link Enemy} are engaged in
    battle, {@code false} otherwise.
    */
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
                    player.restoreHP(20);
                else if (enemy.isDead() && enemy.getItem().equals("maxammo"))
                    player.getGun().reload();
            }
        }
        
        return battle;
    }
    
    /**
    This method nullifies the {@link Enemy}.
    */
    public void terminateEnemy()
    {
        enemy = null;
    }
    
    /**
    This method either creates or recreates the {@link Enemy} and assigns or
    reassigns it an {@link Item}.
    */
    public void spawnEnemy()
    {
        if (enemy == null)
            enemy = new Enemy(rand.nextInt(10));
        else
            enemy.respawn(rand.nextInt(10));
    }
}
