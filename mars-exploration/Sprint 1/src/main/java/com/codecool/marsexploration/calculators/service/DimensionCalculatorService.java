package com.codecool.marsexploration.calculators.service;

public class DimensionCalculatorService implements  DimensionCalculator {

    public int calculateDimension(int size, int dimensionGrowth){
        return (int) (Math.ceil(Math.sqrt(size)) + dimensionGrowth);
    };
}
