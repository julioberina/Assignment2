/**
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

import java.util.Scanner;

/**
 * This class represents the {@link UserInterface}, which gather user input
 * and displays data to the screen.
 * @author jmb
 */

public class UserInterface {
    
    /***
     * This field represents an instance of {@link GameEngine} which will return
     * entities and its data for display on the screen.
     */
    private GameEngine engine;
    
    /***
     * This field represents an instance of the scanner, which will be used to 
     * gather user input.
     */
    private Scanner scan;
    
    /**
     * This represents the default constructor for the class.
     */
    public UserInterface()
    {
        
    }
    
    /***
     * This method represents the game loop for the entire game. It instantiates
     * the scanner and the {@link GameEngine} as well as start the {@link Player}
     * off with a {@link Gun} and starts the game.
     */
    public void run()
    {
        scan = new Scanner(System.in);
        engine = new GameEngine();
        System.out.println("Welcome to Escape the Dungeon!\n");

        engine.assignPlayerWeapon(getWeaponChoice());
        startGame();
    }
    
    /***
     * This method prompts the user for a {@link Gun} to use for the whole game.
     * It then returns the integer representation of their choice.
    
     * @return     Integer representation of chosen {@link Gun}.
     */
    public int getWeaponChoice()
    {
        int choice = 0;
        System.out.println("Please choose a weapon for the game: ");
        System.out.println("1. Pistol (75% accuracy, 1 hp damage)");
        System.out.println("2. Rifle (65% accuracy, 2 hp damage)");
        System.out.println("3. Shotgun (40% accuracy, 5 hp damage)\n");

        while (choice < 1 || choice > 3)
        {
            System.out.print("Enter 1, 2, or 3:  ");
            choice = Integer.parseInt(scan.nextLine());
            if (choice < 1 || choice > 3)
                System.out.println("Invalid input!  Must be 1, 2, or 3!\n");
        }
        
        return choice;
    }
    
    /***
    This method represents a loop that keeps running as long as {@link Player}
    is alive and still in the dungeon. It also checks for encounters, displays
    {@link Player} data as well as the dungeon, and outputs whether you've won
    or lost.
    */
    public void startGame()
    {
        while (engine.getPlayer().isAlive() && engine.stillInDungeon()) {
            if (engine.encounterOccurred())
                engine.initiateBattle();
            
            displayTurn();
            playTurn();
        }
        
        if (engine.getPlayer().isAlive())
        {
            displayTurn();
            System.out.println("You win!\n");
        }
        else if (engine.getPlayer().isDead())
            System.out.println("You lose!\n");
    }
    
    /***
    This method outputs the {@link Player}'s ammo, health, and {@link Gun} to 
    the screen. It also draws the dungeon and the {@link Player}'s current
    position.
    */
    public void displayStats()
    {
        System.out.print("Player HP: " + engine.getPlayerHP());
        if (engine.getEnemy() != null && engine.getEnemy().isAlive())
            System.out.print("\t\t\t\tEnemy HP: " + engine.getEnemyHP());
        
        System.out.print("\n");
        
        System.out.print("Player Weapon: " + engine.getPlayerWeapon());
        if (engine.getEnemy() != null && engine.getEnemy().isAlive())
            System.out.print("\t\t\tEnemy Weapon: " + engine.getEnemyWeapon());
        
        System.out.print("\n");
        
        System.out.print("Player Ammo: " + engine.getPlayerAmmo());
        if (engine.getEnemy() != null && engine.getEnemy().isAlive())
            System.out.print("\t\t\tEnemy Ammo: " + engine.getEnemyAmmo());
        
        System.out.print("\n\n\n");
        
        for (String component: engine.getDungeon())
            System.out.print(component);
        
        System.out.print("\n");
    }
    
    /**
    This method draws out the dungeon and its steps as well as the position of
    the {@link Player}.
    */
    public void displayTurn()
    {
        for (int i = 0; i < 70; i++) {
            if (i == 69)
                System.out.println("-");
            else
                System.out.print("-");
        }
        
        displayStats();
        
        for (int i = 0; i < 70; i++) {
            if (i == 69)
                System.out.println("-");
            else
                System.out.print("-");
        }
        
        System.out.print("\n");
    }
    
    /**
    This method prompts the user to take a step forward and manages input when
    battle occurs.
    */
    public void playTurn()
    {
        if (engine.battleMode())
        {
            manageBattle();
            if (engine.getEnemy() != null)
            {
                if (engine.getEnemy().isDead())
                {
                    System.out.println("Enemy defeated!");
                    if (engine.getEnemy().getItem().equals("health"))
                        System.out.println("Picked up health. Restored HP.\n");
                    else if (engine.getEnemy().getItem().equals("maxammo"))
                        System.out.println("Picked up ammo. Restored ammo.\n");
                    engine.getEnemy().dropItem();
                    engine.terminateEnemy();
                }
            }
            else
                System.out.println("Enemy evaded! Move back one step\n");
        }
        
        System.out.print("Press Enter to take one step: ");
        scan.nextLine();
        engine.movePlayer();
        System.out.print("\n\n\n");
    }
    
    /**
    This method is called when an {@link Enemy} is crossed. It manages attacks
    of {@link ActiveAgent}s as well as {@link Player}'s attempt to escape.
    */
    public void manageBattle()
    {
        System.out.println("You have encountered an enemy!");

        while (engine.battleMode())
        {
            int action = getBattleAction();
            switch (action)
            {
                case 1:
                    if (engine.getPlayer().shoot(engine.getEnemy()))
                        System.out.println("Hit!");
                    else
                        System.out.println((engine.getPlayer().getGun().isEmpty() ? "No ammo!" : "Missed!"));
                    break;
                case 2:
                    if (engine.escapeEnemy())
                        System.out.println("Escape succeeded!");
                    else
                        System.out.println("Escape failed!");
                    break;
            }
            
            if (engine.getEnemy() != null) {
                simulateEnemyAttack();
                engine.checkWeaponEmptiness();
                displayTurn();
            }
        }
    }
    
    /**
    This method manages the {@link Enemy}'s turn for attack.
    */
    public void simulateEnemyAttack()
    {
        if (engine.getEnemy().isAlive())
        {
            System.out.print("\n");
            System.out.print("Enemy turn result:  ");
        
            if (engine.getEnemy().shoot(engine.getPlayer()))
                System.out.println("Enemy shot you!\n");
            else
                System.out.println("Enemy has " + (engine.getEnemy().getGun().isEmpty() ? "No ammo!" : "Missed!"));
        }
    }
    
    /**
    This method gathers user input to decide whether to try to shoot the
    {@link Enemy} or attempt to escape. It then returns the integer representation
    of that choice.
    
    @return     Integer representation of choice by user.
    */
    public int getBattleAction()
    {
        int choice = 0;
        System.out.println("What would you like to do?");
        System.out.println("1. Shoot");
        System.out.println("2. Escape\n");
        
        while (choice < 1 || choice > 2)
        {
            System.out.print("Enter input:  ");
            choice = Integer.parseInt(scan.nextLine());
            
            if (choice < 1 || choice > 2)
                System.out.println("Invalid input! Must be 1 or 2!\n");
        }
        
        System.out.print("\n");
        return choice;
    }
}
