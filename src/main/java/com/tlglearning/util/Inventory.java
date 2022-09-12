package com.tlglearning.util;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> backpack;
    //ctor
    public Inventory() {
        backpack = new ArrayList<>();
    }
    public Inventory(Inventory backpack){
        this.backpack = backpack.getBackpack();
    }
    //allows methods to access current backpack items
    public ArrayList<String> getBackpack() {
        return backpack;
    }
    //allows methods to add items to the backpack
    public void setBackpack(String item) {
        backpack.add(item);
    }
}
