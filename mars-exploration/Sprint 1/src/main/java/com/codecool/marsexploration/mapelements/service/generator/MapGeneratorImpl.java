package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;

import java.util.Arrays;
import java.util.List;

import static com.codecool.marsexploration.mapelements.model.Symbol.*;

public class MapGeneratorImpl implements MapGenerator {

    private final MapElementsGenerator mapElementsGenerator;
    private final CoordinateCalculator coordinateCalculator;
    private final MapElementPlacer mapElementPlacer;

    public MapGeneratorImpl(MapElementsGenerator mapElementsGenerator, CoordinateCalculator coordinateCalculator, MapElementPlacer mapElementPlacer) {
        this.mapElementsGenerator = mapElementsGenerator;
        this.coordinateCalculator = coordinateCalculator;
        this.mapElementPlacer = mapElementPlacer;
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        String[][] representation = new String[mapConfig.mapSize()][mapConfig.mapSize()];

        fillMapWithSpaces(mapConfig, representation);

        List<MapElement> mapElements = mapElementsGenerator.createAll(mapConfig);
        List<MapElement> largeElements = getLargeMapElements(mapElements);
        List<MapElement> smallElements = getSmallMapElements(mapElements);

        placeLargeElements(mapConfig, representation, largeElements);
        placeSmallElements(mapConfig, representation, smallElements);

        return new Map(representation);
    }
  
    private static void fillMapWithSpaces(MapConfiguration mapConfig, String[][] representation) {
        for (int i = 0; i < mapConfig.mapSize(); i++) {
            Arrays.fill(representation[i], " ");
        }
    }
  
    private static List<MapElement> getLargeMapElements(List<MapElement> mapElements) {
        return mapElements.stream()
                .filter(mapElement -> mapElement.getPreferredLocationSymbol().equals(""))
                .toList();
    }
  
    private static List<MapElement> getSmallMapElements(List<MapElement> mapElements) {
        return mapElements.stream()
                .filter(mapElement -> !mapElement.getPreferredLocationSymbol().equals(""))
                .toList();
    }

    private void placeLargeElements(MapConfiguration mapConfig, String[][] representation, List<MapElement> largeElements) {
        for (MapElement largeElement : largeElements) {
            boolean toBePlaced = true;

            while (toBePlaced) {
                Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(mapConfig.mapSize());
                if (mapElementPlacer.canPlaceElement(largeElement, representation, randomCoordinate)) {
                    mapElementPlacer.placeElement(largeElement, representation, randomCoordinate);
                    toBePlaced = false;
                }
            }
        }
    }

    private void placeSmallElements(MapConfiguration mapConfig, String[][] representation, List<MapElement> smallElements) {
        for (MapElement smallElement : smallElements) {
            boolean toBePlaced = true;

            while (toBePlaced) {
                Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(mapConfig.mapSize());
                List<String> adjacentSymbols = getAdjacentSymbols(mapConfig, representation, randomCoordinate);

                if (mapElementPlacer.canPlaceElement(smallElement, representation, randomCoordinate)
                        && (adjacentSymbols.stream().anyMatch(symbol -> symbol.equals(smallElement.getPreferredLocationSymbol())))) {
                    mapElementPlacer.placeElement(smallElement, representation, randomCoordinate);
                    toBePlaced = false;
                }
            }
        }
    }

    private List<String> getAdjacentSymbols(MapConfiguration mapConfig, String[][] representation, Coordinate randomCoordinate) {
        List<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(randomCoordinate, mapConfig.mapSize());
        return adjacentCoordinates.stream()
                .map(coordinate -> representation[coordinate.x()][coordinate.y()])
                .toList();
    }
}

