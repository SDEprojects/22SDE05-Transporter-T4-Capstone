package com.tlglearning.util;

import java.util.ArrayList;
import java.util.List;

public class ScenarioGenerator {

    private String officeLocation;
    private String pickupLocation1;
    private String pickupLocation2;

    private String deliveryLocation1;
    private String deliveryLocation1b;
    private String deliveryLocation2;
    private String deliveryLocation2b;

    private List<String> itemsNeeded;
    //ctor
    public ScenarioGenerator(){

    }
    public ScenarioGenerator(ScenarioGenerator scenario){
        this.officeLocation = scenario.getOfficeLocation();
        this.pickupLocation1 = scenario.getPickupLocation1();
        this.pickupLocation2 = scenario.getPickupLocation2();
        this.deliveryLocation1 = scenario.getDeliveryLocation1();
        this.deliveryLocation1b = scenario.getDeliveryLocation1b();
        this.deliveryLocation2 = scenario.getDeliveryLocation2();
        this.deliveryLocation2b = scenario.getDeliveryLocation2b();
        this.itemsNeeded = scenario.getItemsNeeded();
    }
    public ScenarioGenerator(String officeLocation, String pickupLocation1, String pickupLocation2, String deliveryLocation1, String deliveryLocation1b, String deliveryLocation2, String deliveryLocation2b, List<String> itemsNeeded) {
        this.officeLocation = officeLocation;
        this.pickupLocation1 = pickupLocation1;
        this.pickupLocation2 = pickupLocation2;
        this.deliveryLocation1 = deliveryLocation1;
        this.deliveryLocation1b = deliveryLocation1b;
        this.deliveryLocation2 = deliveryLocation2;
        this.deliveryLocation2b = deliveryLocation2b;
        this.itemsNeeded = itemsNeeded;
    }

    //setters and getters to access ScenarioGenerator objects private fields
    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getPickupLocation1() {
        return pickupLocation1;
    }

    public void setPickupLocation1(String pickupLocation1) {
        this.pickupLocation1 = pickupLocation1;
    }

    public String getPickupLocation2() {
        return pickupLocation2;
    }

    public void setPickupLocation2(String pickupLocation2) {
        this.pickupLocation2 = pickupLocation2;
    }

    public String getDeliveryLocation1() {
        return deliveryLocation1;
    }

    public void setDeliveryLocation1(String deliveryLocation1) {
        this.deliveryLocation1 = deliveryLocation1;
    }

    public String getDeliveryLocation1b() {
        return deliveryLocation1b;
    }

    public void setDeliveryLocation1b(String deliveryLocation1b) {
        this.deliveryLocation1b = deliveryLocation1b;
    }

    public String getDeliveryLocation2() {
        return deliveryLocation2;
    }

    public void setDeliveryLocation2(String deliveryLocation2) {
        this.deliveryLocation2 = deliveryLocation2;
    }

    public String getDeliveryLocation2b() {
        return deliveryLocation2b;
    }

    public void setDeliveryLocation2b(String deliveryLocation2b) {
        this.deliveryLocation2b = deliveryLocation2b;
    }

    public List<String> getItemsNeeded() {
        return itemsNeeded;
    }

    public void setItemsNeeded(List<String> itemsNeeded) {
        this.itemsNeeded = itemsNeeded;
    }

    @Override
    public String toString() {
        return "ScenarioGenerator{" +
                "officeLocation='" + officeLocation + '\'' +
                ", pickupLocation1='" + pickupLocation1 + '\'' +
                ", pickupLocation2='" + pickupLocation2 + '\'' +
                ", deliveryLocation1='" + deliveryLocation1 + '\'' +
                ", deliveryLocation1b='" + deliveryLocation1b + '\'' +
                ", deliveryLocation2='" + deliveryLocation2 + '\'' +
                ", deliveryLocation2b='" + deliveryLocation2b + '\'' +
                ", itemsNeeded=" + itemsNeeded +
                '}';
    }
}
