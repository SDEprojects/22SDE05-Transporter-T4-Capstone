package com.tlglearning.util;

public class Location {
    //default starting location = truck for new game start
    private String locationName = "truck";
    private String description;
    private String north;
    private String south;
    //default east warehouse for new game start
    private String east = "warehouse";
    private String west;
    //ctor
    public Location() {
    }
    //setters and getters to access Location objects private fields
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + locationName + '\'' +
                ", description='" + description + '\'' +
                ", north='" + north + '\'' +
                ", south='" + south + '\'' +
                ", east='" + east + '\'' +
                ", west='" + west + '\'' +
                '}';
    }
}
