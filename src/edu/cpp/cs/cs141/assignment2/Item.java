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
public class Item {
    private String type;
    
    public Item(int item)
    {
        if (item >= 0 && item <= 2)
           type = "health";
        else
           type = "maxammo";
    }
    
    public String getType()
    {
        return type;
    }
    
    public void reassignTo(int item)
    {
        if (item >= 0 && item <= 2)
            type = "health";
        else if (item == -1)
            type = "none";
        else
            type = "maxammo";
    }
}
