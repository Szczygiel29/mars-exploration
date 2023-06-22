package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Arrays;
import java.util.Random;

import static com.codecool.marsexploration.mapelements.model.Symbol.*;

public class MapElementBuilderImpl implements MapElementBuilder {
    private final DimensionCalculator dimensionCalculator;

    public MapElementBuilderImpl(DimensionCalculator dimensionCalculator) {
        this.dimensionCalculator = dimensionCalculator;
    }

    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        int dimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);

        String[][] representation = new String[dimension][dimension];

        fillWithSpaces(representation);

        fillRandomlyWithSymbol(size, symbol, dimension, representation);

        return new MapElement(representation, name, dimension, preferredLocationSymbol);
    }

    private static void fillWithSpaces(String[][] representation) {
        Arrays.stream(representation).forEach(array -> Arrays.fill(array, SPACE.getSymbol()));
    }

    private static void fillRandomlyWithSymbol(int size, String symbol, int finalDimension, String[][] representation) {
        Random random = new Random();

        int counter = 0;
        while (counter < size) {
            int row = random.nextInt(finalDimension);
            int column = random.nextInt(finalDimension);
            if (representation[row][column].equals(SPACE.getSymbol())) {
                representation[row][column] = symbol;
                counter++;
            }
        }
    }
}
