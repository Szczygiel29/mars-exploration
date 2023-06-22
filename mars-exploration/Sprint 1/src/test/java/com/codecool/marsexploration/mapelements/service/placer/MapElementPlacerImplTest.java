package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MapElementPlacerImplTest {

    private final MapElementPlacer mapElementPlacer;

    public MapElementPlacerImplTest() {
        this.mapElementPlacer = new MapElementPlacerImpl();
    }

    @Test
    void canPlaceElementInTheMiddleOfEmptyMap() {
        MapElement testMapElement = new MapElement(new String[3][3], "testMapElement", 3);
        String[][] testMap = new String[10][10];
        Coordinate testCoordinate = new Coordinate(5, 5);
        for (int i = 0; i < 10; i++) {
            Arrays.fill(testMap[i], " ");
        }
        assertTrue(mapElementPlacer.canPlaceElement(testMapElement, testMap, testCoordinate));
    }

    @Test
    void canPlaceElementInTheCornerOfEmptyMap() {
        MapElement testMapElement = new MapElement(new String[3][3], "testMapElement", 3);
        String[][] testMap = new String[10][10];
        Coordinate testCoordinate = new Coordinate(0, 0);
        for (int i = 0; i < 10; i++) {
            Arrays.fill(testMap[i], " ");
        }
        assertTrue(mapElementPlacer.canPlaceElement(testMapElement, testMap, testCoordinate));
    }

    @Test
    void canNotPlaceElementInTakenCell() {
        MapElement testMapElement = new MapElement(new String[3][3], "testMapElement", 3);
        String[][] testMap = new String[10][10];
        testMap[5][5] = "X";
        Coordinate testCoordinate = new Coordinate(5, 5);

        assertFalse(mapElementPlacer.canPlaceElement(testMapElement, testMap, testCoordinate));
    }

    @Test
    void canNotPlaceElementNearTakenCell() {
        MapElement testMapElement = new MapElement(new String[3][3], "testMapElement", 3);
        String[][] testMap = new String[10][10];

        Coordinate testCoordinate = new Coordinate(4, 4);
        for (int i = 0; i < 10; i++) {
            Arrays.fill(testMap[i], " ");
        }
        testMap[5][5] = "X";
        assertFalse(mapElementPlacer.canPlaceElement(testMapElement, testMap, testCoordinate));
    }

    @Test
    void canNotPlaceElementOutSideMap() {
        MapElement testMapElement = new MapElement(new String[3][3], "testMapElement", 3);
        String[][] testMap = new String[10][10];
        Coordinate testCoordinate = new Coordinate(8, 8);

        assertFalse(mapElementPlacer.canPlaceElement(testMapElement, testMap, testCoordinate));
    }

    @Test
    void placeElementInCorner() {
        String[][] testMapElementRepresentation = new String[3][3];
        testMapElementRepresentation[0][0] = "X";
        testMapElementRepresentation[0][2] = "X";
        testMapElementRepresentation[2][2] = "X";
        MapElement testMapElement = new MapElement(testMapElementRepresentation, "testMapElement", 3);
        String[][] testMapRepresentation = new String[10][10];
        Coordinate testCoordinate = new Coordinate(0, 0);

        mapElementPlacer.placeElement(testMapElement, testMapRepresentation, testCoordinate);

        assertEquals(testMapRepresentation[0][0], "X");
        assertEquals(testMapRepresentation[0][2], "X");
        assertEquals(testMapRepresentation[2][2], "X");
    }
    @Test
    void placeElementInCenter() {
        String[][] testMapElementRepresentation = new String[3][3];
        testMapElementRepresentation[0][0] = "X";
        testMapElementRepresentation[0][2] = "X";
        testMapElementRepresentation[2][2] = "X";
        MapElement testMapElement = new MapElement(testMapElementRepresentation, "testMapElement", 3);
        String[][] testMapRepresentation = new String[10][10];
        Coordinate testCoordinate = new Coordinate(5, 5);

        mapElementPlacer.placeElement(testMapElement, testMapRepresentation, testCoordinate);

        assertEquals(testMapRepresentation[5][5], "X");
        assertEquals(testMapRepresentation[5][7], "X");
        assertEquals(testMapRepresentation[7][7], "X");
    }
}