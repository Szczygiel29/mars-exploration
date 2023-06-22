package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.service.CoordinateCalculatorService;

import java.util.*;

public class Rover {
    private final List<Coordinate> previousPositions;
    private final String id;
    private final java.util.Map<String, Set<Coordinate>> objectsPoints;
    private final Map map;
    private final int sightRange;
    private final Set<Coordinate> scannedCoordinates;
    private Coordinate position;


    public Rover(String id, Coordinate position, int sightRange, Map map) {
        this.id = id;
        this.position = position;
        this.sightRange = sightRange;
        this.objectsPoints = new HashMap<>();
        this.map = map;
        previousPositions = new ArrayList<>();
        scannedCoordinates = new HashSet<>();
    }

    public List<Coordinate> getPreviousPositions() {
        return previousPositions;
    }

    public Set<Coordinate> getScannedCoordinates() {
        return scannedCoordinates;
    }

    public java.util.Map<String, Set<Coordinate>> getObjectsPoints() {
        return objectsPoints;
    }

    public void addScannedCoordinates() {
        List<Coordinate> coordinatesToAdd = CoordinateCalculatorService.getCoordinatesAround(position, sightRange, map.getDimension());
        scannedCoordinates.addAll(coordinatesToAdd);
    }

    public void checkForObjectsAround(String resource) {
        List<Coordinate> coordinatesToCheck = CoordinateCalculatorService.getCoordinatesAround(position, sightRange, map.getDimension());
        scannedCoordinates.addAll(coordinatesToCheck);
        coordinatesToCheck.forEach(coordinate -> {
            if (map.getByCoordinate(coordinate).equals(resource)) {
                saveObjectPoint(coordinate, resource);
            }
        });
    }

    public void saveObjectPoint(Coordinate coordinate, String resource) {
        Set<Coordinate> coordinateList;
        if (objectsPoints.containsKey(resource)) {
            coordinateList = objectsPoints.get(resource);
        } else {
            coordinateList = new HashSet<>() {
            };
        }
        coordinateList.add(coordinate);
        objectsPoints.put(resource, coordinateList);
    }

    public void addToPreviousPositionsList(Coordinate coordinate) {
        previousPositions.add(coordinate);
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }
}
