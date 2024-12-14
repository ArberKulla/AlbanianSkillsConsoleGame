package utils;

import java.awt.Color;

public class Location {
    private String description;
    private Color color;

    public Location(String description, Color color) {
        this.description = description;
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return description + " (Color: " + color.toString() + ")";
    }
}