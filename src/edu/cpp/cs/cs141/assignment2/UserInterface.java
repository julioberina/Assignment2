/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs141.assignment2;

import java.util.Scanner;

/**
 *
 * @author jmb
 */
public class UserInterface {
    
    private GameEngine engine;
    private Scanner scan;
    
    // game loop, instantiate engine and run other methods
    // corresponding to the desired outcome
    public void run()
    {
        scan = new Scanner(System.in);
        engine = new GameEngine();
        System.out.println("Welcome to Escape the Dungeon!\n");

        engine.assignPlayerWeapon(getWeaponChoice());
        startGame();
    }
    
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
            choice = scan.nextInt();
            if (choice < 1 || choice > 3)
                System.out.println("Invalid input!  Must be 1, 2, or 3!\n");
        }
        
        return choice;
    }
    
    public void startGame()
    {
        while (engine.getPlayer().isAlive() && engine.stillInDungeon()) {
            if (engine.encounterOccurred())
                engine.initiateBattle();
            
            displayTurn();
            playTurn();
        }
    }
    
    public void displayStats()
    {
        System.out.print("Player HP: " + engine.getPlayerHP() + "\t\t\t");
        if (engine.getEnemy() != null)
            System.out.println("Enemy HP: " + engine.getEnemyHP());
        
        System.out.print("Player Weapon: " + engine.getPlayerWeapon() + "\t\t\t");
        if (engine.getEnemy() != null)
            System.out.println("Enemy Weapon: " + engine.getEnemyWeapon());
        
        System.out.print("Player Ammo: " + engine.getPlayerAmmo() + "\t\t\t");
        if (engine.getEnemy() != null)
            System.out.println("Enemy Ammo: " + engine.getEnemyAmmo());
        
        System.out.print("\n");
        
        for (String component: engine.getDungeon())
            System.out.print(component);
    }
    
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
    
    public void playTurn()
    {
        if (engine.battleMode())
            manageBattle();
        
        System.out.print("Press Enter to take one step: ");
        scan.nextLine();
        engine.movePlayer();
        System.out.print("\n\n\n");
    }
    
    public void manageBattle()
    {
        engine.spawnEnemy();
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
                        System.out.println("Missed!");
                    break;
                case 2:
                    if (engine.escapeEnemy())
                        System.out.println("Escape succeeded!");
                    else
                        System.out.println("Escape failed!");
                    break;
            }
            
            engine.simulateEnemyAttack();
        }
    }
    
    public int getBattleAction()
    {
        int choice = 0;
        System.out.println("What would you like to do?");
        System.out.println("1. Shoot");
        System.out.println("2. Escape\n");
        
        while (choice < 1 || choice > 2)
        {
            System.out.print("Enter input:  ");
            choice = scan.nextInt();
            
            if (choice < 1 || choice > 2)
                System.out.println("Invalid input! Must be 1 or 2!\n");
        }
        
        System.out.print("\n");
        return choice;
    }
}
