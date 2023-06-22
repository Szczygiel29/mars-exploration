package com.codecool.marsexploration.mapexplorer.exploration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.service.MapToEmojiTranslator;

import java.util.Arrays;
import java.util.Set;

import static com.codecool.marsexploration.mapexplorer.maploader.model.Symbol.*;

public class ExplorationResultDisplay {
    private final int mapDimension;
    private final MapToEmojiTranslator mapToEmojiTranslator;

    public ExplorationResultDisplay(int mapDimension) {
        this.mapDimension = mapDimension;
        mapToEmojiTranslator = new MapToEmojiTranslator();
    }

    public void displayExploredMap(Rover rover) {
        String[][] representation = new String[mapDimension][mapDimension];

        fillWithX(representation);

        revealScannedCoordinates(rover, representation);

        markDiscoveredObjects(rover, representation);

        markRover(rover, representation);

        Map map = new Map(representation, true);

        Map emojiMap = mapToEmojiTranslator.translateMapToEmoji(map);

        System.out.println(emojiMap);
    }

    private void fillWithX(String[][] representation) {
        for (int i = 0; i < mapDimension; i++) {
            Arrays.fill(representation[i], HIDDEN.getSymbol());
        }
    }

    private void revealScannedCoordinates(Rover rover, String[][] representation) {
        Set<Coordinate> scannedCoordinates = rover.getScannedCoordinates();
        for (Coordinate coordinate : scannedCoordinates) {
            representation[coordinate.X()][coordinate.Y()] = SPACE.getSymbol();
        }
    }

    private void markDiscoveredObjects(Rover rover, String[][] representation) {
        java.util.Map<String, Set<Coordinate>> objectsPoints = rover.getObjectsPoints();
        for (String objectString : objectsPoints.keySet()) {
            Set<Coordinate> objectCoordinates = objectsPoints.get(objectString);
            for (Coordinate coordinate : objectCoordinates) {
                representation[coordinate.X()][coordinate.Y()] = objectString;
            }
        }
    }

    private void markRover(Rover rover, String[][] representation) {
        Coordinate roverPosition = rover.getPosition();
        representation[roverPosition.X()][roverPosition.Y()] = ROVER.getSymbol();
    }
}
