package com.codecool.marsexploration.mapelements.model;

public class Map {
    private String[][] representation;

    private boolean successfullyGenerated;


    public Map(String[][] representation) {
        this.representation = representation;
    }

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder mapAsString = new StringBuilder();
        for (String[] stringsArray : arr) {
            for (String string : stringsArray) {
                mapAsString.append(string);
            }
            mapAsString.append("\n");
        }
        return String.valueOf(mapAsString);
    }

    public boolean isSuccessfullyGenerated() {
        return successfullyGenerated;
    }

    public void setSuccessfullyGenerated(boolean successfullyGenerated) {
        this.successfullyGenerated = successfullyGenerated;
    }

    public String[][] getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}

