package com.tlglearning.util;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> backpack;

    public Inventory() {
        backpack = new ArrayList<>();
    }

    public ArrayList<String> getBackpack() {
        return backpack;
    }

    public void setBackpack(String item) {
        backpack.add(item);
    }
}
