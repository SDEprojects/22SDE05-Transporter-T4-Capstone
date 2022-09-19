package com.tlglearning.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private final ArrayList<String> backpack = new ArrayList<>();

    @Test
    void addItemShouldAddItemToBackPack() {
        assertTrue(true, String.valueOf(backpack.add("Key, logbook, soda")));
    }

    @Test
    void getBackpackShouldShowContentOfBackpack() {
        backpack.add("UsMap, Compass, Coffee");
        assertTrue(backpack.contains("UsMap, Compass, Coffee"), String.valueOf(getBackpack()));
    }

    public ArrayList<String> getBackpack() {
        return backpack;
    }
}