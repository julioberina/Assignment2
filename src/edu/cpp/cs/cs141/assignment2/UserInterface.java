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
        while (engine.playerAlive() && engine.stillInDungeon())
            displayTurn();
    }
    
    public void displayTurn()
    {
        for (int i = 0; i < 70; i++) {
            if (i == 69)
                System.out.println("-");
            else
                System.out.print("-");
        }
        
        System.out.print("\n");
        
        for (int i = 0; i < 70; i++) {
            if (i == 69)
                System.out.println("-");
            else
                System.out.print("-");
        }
    }
}
