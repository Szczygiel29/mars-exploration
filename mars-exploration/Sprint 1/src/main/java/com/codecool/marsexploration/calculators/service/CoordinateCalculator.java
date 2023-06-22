package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.List;

public interface CoordinateCalculator {
    Coordinate getRandomCoordinate(int dimension);
    List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension);
    List<Coordinate> getAdjacentCoordinates(List<Coordinate> coordinates, int dimension);
}
