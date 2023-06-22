package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorService;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorService;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DimensionCalculatorServiceTest {
    DimensionCalculatorService dimensionCalculatorService = new DimensionCalculatorService();
    @Test
    public void TestDimensionCalculator() {
        assertEquals(dimensionCalculatorService.calculateDimension(4,5), 7);
        assertEquals(dimensionCalculatorService.calculateDimension(3,0), 2);
        assertEquals(dimensionCalculatorService.calculateDimension(17,10), 15);
        assertEquals(dimensionCalculatorService.calculateDimension(0,0), 0);
        assertEquals(dimensionCalculatorService.calculateDimension(20,5), 10);
    }
}



