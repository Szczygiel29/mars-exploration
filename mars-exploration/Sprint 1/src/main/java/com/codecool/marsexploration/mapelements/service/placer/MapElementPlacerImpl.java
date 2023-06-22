package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;

import static com.codecool.marsexploration.mapelements.model.Symbol.SPACE;

public class MapElementPlacerImpl implements MapElementPlacer{

    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int dimension = element.getDimension();

        if (checkIfElementWillGoOutsideMap(map, coordinate, dimension)) return false;

        return checkIfEmptyUnderElement(map, coordinate, dimension);
    }

    private static boolean checkIfElementWillGoOutsideMap(String[][] map, Coordinate coordinate, int dimension) {
        return coordinate.x() + dimension > map.length || coordinate.y() + dimension > map.length;
    }

    private static boolean checkIfEmptyUnderElement(String[][] map, Coordinate coordinate, int dimension) {
        for (int i = 0; i< dimension; i++) {
            for (int j = 0; j< dimension; j++) {
                if (!map[coordinate.x() + i][coordinate.y() + j].equals(SPACE.getSymbol())) return false;
            }
        }
        return true;
    }

    @Override
    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {
        int dimension = element.getDimension();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                map[coordinate.x() + i][coordinate.y() + j] = element.getRepresentation()[i][j];
            }
        }
    }
}
