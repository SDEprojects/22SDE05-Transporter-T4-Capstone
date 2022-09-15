package com.tlglearning.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private final ArrayList<String> backpack = new ArrayList<>();

    @Test
    void addItemShouldAddItemToBackPack() {
        Inventory inventory = new Inventory();
        assertTrue(backpack, inventory.addItem("Key");
    }
}