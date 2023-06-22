package com.codecool.marsexploration.mapelements.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MapTest {

    @Test
    void createStringRepresentation() {
        String[][] testMapArray = {{"x", "o", "x"}, {"o", "x", "o"}, {"x", "o", "x"}};
        Map testMap = new Map(testMapArray);

        String expected = "xox\noxo\nxox\n";

        String actual = testMap.toString();

        assertEquals(expected, actual);
    }
}