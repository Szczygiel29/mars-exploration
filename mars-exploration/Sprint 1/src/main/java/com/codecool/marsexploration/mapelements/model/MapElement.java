package com.codecool.marsexploration.mapelements.model;

public class MapElement extends Map {
    private final String[][] representation;
    private final String name;
    private final int dimension;
    private final String preferredLocationSymbol;

    public MapElement(String[][] representation, String name, int dimension) {
        this(representation, name, dimension, null);
    }
    public MapElement(String[][] representation, String name, int dimension, String preferredLocationSymbol) {
        super(representation);
        this.representation = representation;
        this.name = name;
        this.dimension = dimension;
        this.preferredLocationSymbol = preferredLocationSymbol;
    }

    public String[][] getRepresentation() {
        return representation;
    }

    public String getName() {
        return name;
    }

    public int getDimension() {
        return dimension;
    }

    public String getPreferredLocationSymbol() {
        return preferredLocationSymbol;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

