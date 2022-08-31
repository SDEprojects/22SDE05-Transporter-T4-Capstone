package com.tlglearning.util;

import java.util.List;

public class Inventory {
    private List<String> backpack;

    public Inventory(List<String> backpack) {
        this.backpack = backpack;
    }

    public List<String> getBackpack() {
        return backpack;
    }

    public void setBackpack(List<String> backpack, String item) {
        this.backpack = backpack;
        backpack.add(item);
    }
}
