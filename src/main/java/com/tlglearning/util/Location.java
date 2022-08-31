package com.tlglearning.util;

public class Location {
    private String name;
    private String description;
    private String exit1;
    private String exit1To;
    private String exit2;
    private String exit2To;
    private String exit3;
    private String exit3To;
    private String exit4;
    private String exit4To;

    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExit1() {
        return exit1;
    }

    public void setExit1(String exit1) {
        this.exit1 = exit1;
    }

    public String getExit1To() {
        return exit1To;
    }

    public void setExit1To(String exit1To) {
        this.exit1To = exit1To;
    }

    public String getExit2() {
        return exit2;
    }

    public void setExit2(String exit2) {
        this.exit2 = exit2;
    }

    public String getExit2To() {
        return exit2To;
    }

    public void setExit2To(String exit2To) {
        this.exit2To = exit2To;
    }

    public String getExit3() {
        return exit3;
    }

    public void setExit3(String exit3) {
        this.exit3 = exit3;
    }

    public String getExit3To() {
        return exit3To;
    }

    public void setExit3To(String exit3To) {
        this.exit3To = exit3To;
    }

    public String getExit4() {
        return exit4;
    }

    public void setExit4(String exit4) {
        this.exit4 = exit4;
    }

    public String getExit4To() {
        return exit4To;
    }

    public void setExit4To(String exit4To) {
        this.exit4To = exit4To;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exit1='" + exit1 + '\'' +
                ", exit1To='" + exit1To + '\'' +
                ", exit2='" + exit2 + '\'' +
                ", exit2To='" + exit2To + '\'' +
                ", exit3='" + exit3 + '\'' +
                ", exit3To='" + exit3To + '\'' +
                ", exit4='" + exit4 + '\'' +
                ", exit4To='" + exit4To + '\'' +
                '}';
    }
}
