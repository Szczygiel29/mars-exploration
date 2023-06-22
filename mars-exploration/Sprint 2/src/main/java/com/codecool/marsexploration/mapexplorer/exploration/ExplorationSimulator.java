package com.codecool.marsexploration.mapexplorer.exploration;

import com.codecool.marsexploration.mapexplorer.analizer.AllOutcomeAnalyzer;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationParameters;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.repository.ExplorationsDto;
import com.codecool.marsexploration.mapexplorer.repository.ExplorationsRepository;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;

import java.util.Set;

public class ExplorationSimulator {

    private final ExplorationResultDisplay explorationResultDisplay;
    private final MapLoader mapLoader;
    private final AllOutcomeAnalyzer allOutcomeAnalyzer;
    private final MovementService movementService;
    private final Logger logger;
    private final ExplorationsRepository explorationsRepository;

    public ExplorationSimulator(ExplorationResultDisplay explorationResultDisplay,
                                MapLoader mapLoader,
                                MovementService movementService,
                                AllOutcomeAnalyzer allOutcomeAnalyzer,
                                Logger logger,
                                ExplorationsRepository explorationsRepository) {
        this.explorationResultDisplay = explorationResultDisplay;
        this.mapLoader = mapLoader;
        this.movementService = movementService;
        this.allOutcomeAnalyzer = allOutcomeAnalyzer;
        this.logger = logger;
        this.explorationsRepository = explorationsRepository;
    }

    public void runSimulation(ConfigurationParameters configurationParameters, Rover rover) {
        Simulation simulation = new Simulation(
                configurationParameters.maxSteps(),
                rover,
                configurationParameters.spaceshipLandingPoint(),
                mapLoader.load(configurationParameters.mapPath()),
                configurationParameters.symbols()
        );

        SimulationStepsLogging simulationStepsLogging = new SimulationStepsLogging(simulation, logger, allOutcomeAnalyzer);

        while (simulation.explorationOutcome() == null) {
            movementService.move();

            configurationParameters.symbols().forEach(rover::checkForObjectsAround);
            rover.addScannedCoordinates();

            ExplorationOutcome explorationOutcome = allOutcomeAnalyzer.analyze(simulation);

            simulationStepsLogging.logSteps();

            if (explorationOutcome != null) {
                int numberOfResources = rover.getObjectsPoints().values().stream().mapToInt(Set::size).sum();
                ExplorationsDto explorationsDto = new ExplorationsDto(simulation.numberOfSteps(), numberOfResources, explorationOutcome);
                explorationsRepository.saveInDatabase(explorationsDto);
                simulation.setExplorationOutcome(explorationOutcome);
            }

            simulation.setNumberOfSteps(simulation.numberOfSteps() + 1);
        }
        explorationResultDisplay.displayExploredMap(rover);
    }


}
