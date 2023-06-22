package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CoordinateCalculatorService implements CoordinateCalculator {

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        return new Coordinate((int) Math.floor(Math.random() * dimension), (int) Math.floor(Math.random() * dimension));
    }

    @Override
    public List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> coordinates = new ArrayList<>();

        if (coordinate.x() != dimension - 1) {
            coordinates.add(new Coordinate(coordinate.x() + 1, coordinate.y()));
        }
        if (coordinate.y() != dimension - 1) {
            coordinates.add(new Coordinate(coordinate.x(), coordinate.y() + 1));
        }

        if (coordinate.x() != 0) {
            coordinates.add(new Coordinate(coordinate.x() - 1, coordinate.y()));
        }
        if (coordinate.y() != 0) {
            coordinates.add(new Coordinate(coordinate.x(), coordinate.y() - 1));
        }
        return coordinates;
    }

    @Override
    public List<Coordinate> getAdjacentCoordinates(List<Coordinate> coordinates, int dimension) {
        List<Coordinate> coordinatesToReturn = new ArrayList<>();
        coordinates.forEach(coordinate -> coordinates.addAll(getAdjacentCoordinates(coordinate, dimension)));
        return coordinatesToReturn;
    }
}
