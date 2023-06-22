package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CoordinateCalculatorServiceTest {
    CoordinateCalculatorService coordinateCalculatorService = new CoordinateCalculatorService();

    @Test
    public void shouldReturnCoordinatesOnlyWithinDimensionBounds() {
        Coordinate corner = new Coordinate(0, 0);
        List<Coordinate> adjacentCoordinates = coordinateCalculatorService.getAdjacentCoordinates(corner, 20);

        assertThat(adjacentCoordinates)
                .hasSize(2)
                .containsExactlyInAnyOrderElementsOf(List.of(
                        new Coordinate(1, 0),
                        new Coordinate(0, 1)
                ));
    }

    @Test
    public void coordinateNotAtCornerTest() {
        Coordinate coordinate = new Coordinate(2, 5);
        List<Coordinate> adjacentCoordinates = coordinateCalculatorService.getAdjacentCoordinates(coordinate, 20);

        assertThat(adjacentCoordinates)
                .hasSize(4)
                .containsExactlyInAnyOrderElementsOf(List.of(
                        new Coordinate(1, 5),
                        new Coordinate(3, 5),
                        new Coordinate(2, 4),
                        new Coordinate(2, 6)
                ));
    }
}
