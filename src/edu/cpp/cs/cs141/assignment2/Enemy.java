/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs.cs141.assignment2;

import java.util.Random;

/**
 *
 * @author jmb
 */
public class Enemy extends ActiveAgent {
    
    private Item item;
    private Random rand;
    
    public Enemy()
    {
        super(5);
        rand = new Random();
    }
}
