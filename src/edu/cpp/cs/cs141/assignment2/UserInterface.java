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
    }
}
