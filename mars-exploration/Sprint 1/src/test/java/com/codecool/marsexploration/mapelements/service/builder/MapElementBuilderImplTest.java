package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorService;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapElementBuilderImplTest {
    DimensionCalculator dimensionCalculator = new DimensionCalculatorService();
    private final MapElementBuilder mapElementBuilder= new MapElementBuilderImpl(dimensionCalculator);
    @Test
    void buildAssertSymbol() {
        String expectedSymbol = "x";

        MapElement actual = mapElementBuilder.build(5, "x", "testMapElement", 0, "");

        String actualSymbol = "";
        for (String[] row : actual.getRepresentation()) {
            for (String cell : row) {
                if (!cell.equals(" ")) {
                    actualSymbol = cell;
                }
            }
        }

        assertEquals(expectedSymbol, actualSymbol);
    }

    @Test
    void buildAssertName() {
        String expectedName = "testMapElement";

        MapElement actual = mapElementBuilder.build(5, "x", "testMapElement", 0, "");
        String actualName = actual.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void buildAssertDimensionSize5dimensionGrowth0() {
        int expectedDimension = 3;

        MapElement actual = mapElementBuilder.build(5, "x", "testMapElement", 0, "");
        int actualDimension = actual.getDimension();

        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    void buildAssertDimensionSize20dimensionGrowth3() {
        int expectedDimension = 8;

        MapElement actual = mapElementBuilder.build(20, "x", "testMapElement", 3, "");
        int actualDimension = actual.getDimension();

        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    void builtAssertPreferredLocationSymbol () {
        String expectedPreferredLocationSymbol = "&";

        MapElement actual = mapElementBuilder.build(20, "x", "testMapElement", 3, "&");
        String actualPreferredLocationSymbol = actual.getPreferredLocationSymbol();

        assertEquals(expectedPreferredLocationSymbol, actualPreferredLocationSymbol);
    }
}