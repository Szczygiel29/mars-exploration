package com.codecool.marsexploration.mapexplorer.exploration;


import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;

import java.util.List;

public class Simulation {
    private final int stepsToTimeout;
    private final Rover rover;
    private final Coordinate spaceshipCoordinate;
    private final Map map;
    private final List<String> resourcesToMonitor;
    private int numberOfSteps;
    private ExplorationOutcome explorationOutcome;

    public Simulation(int stepsToTimeout,
                      Rover rover,
                      Coordinate spaceshipCoordinate,
                      Map map,
                      List<String> resourcesToMonitor) {
        this.stepsToTimeout = stepsToTimeout;
        this.rover = rover;
        this.spaceshipCoordinate = spaceshipCoordinate;
        this.map = map;
        this.resourcesToMonitor = resourcesToMonitor;
        this.numberOfSteps = 0;
        this.explorationOutcome = null;
    }

    public int numberOfSteps() {
        return numberOfSteps;
    }

    public int stepsToTimeout() {
        return stepsToTimeout;
    }

    public Rover rover() {
        return rover;
    }

    public Coordinate getSpaceshipCoordinate() {
        return spaceshipCoordinate;
    }

    public Map map() {
        return map;
    }

    public List<String> resourcesToMonitor() {
        return resourcesToMonitor;
    }

    public ExplorationOutcome explorationOutcome() {
        return explorationOutcome;
    }

    @Override
    public String toString() {
        return "Simulation[\n" +
                "numberOfSteps=" + numberOfSteps + ",\n " +
                "stepsToTimeout=" + stepsToTimeout + ",\n " +
                "rover=" + rover + ", " +
                "spaceshipCoordinate=" + spaceshipCoordinate + ",\n " +
                "map=" + map + ",\n " +
                "resourcesToMonitor=" + resourcesToMonitor + ",\n " +
                "explorationOutcome=" + explorationOutcome + ']';
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public void setExplorationOutcome(ExplorationOutcome explorationOutcome) {
        this.explorationOutcome = explorationOutcome;
    }
}
