package com.tlglearning.util;

import java.util.ArrayList;
import java.util.List;

public class ScenarioGenerator {

    private String officeLocation;
    private String pickupLocation;
    private String deliveryLocation;
    private List<String> itemsNeeded;
    //ctor
    public ScenarioGenerator(String officeLocation, String pickupLocation, String deliveryLocation, ArrayList<String> itemsNeeded) {
        this.officeLocation = officeLocation;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.itemsNeeded = itemsNeeded;
    }
    //setters and getters to access ScenarioGenerator objects private fields
    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public List<String> getItemsNeeded() {
        return itemsNeeded;
    }

    public void setItemsNeeded(List<String> itemsNeeded) {
        this.itemsNeeded = itemsNeeded;
    }
}
