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
public class Player extends ActiveAgent {
    
    public Player()
    {
        super(20);
    }
    
    public void restoreHealth()
    {
        restoreHP(20);
    }
}
