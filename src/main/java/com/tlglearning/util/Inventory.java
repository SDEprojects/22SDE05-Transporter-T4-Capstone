package com.tlglearning.util;

import java.util.ArrayList;

public class Inventory {

    private final ArrayList<String> backpack = new ArrayList<>();
    //ctor

    public Inventory() {
    }

    //allows methods to access current backpack items
    public ArrayList<String> getBackpack() {
        return backpack;
    }

    public void addItem(String item) {
        backpack.add(item);
    }
}
