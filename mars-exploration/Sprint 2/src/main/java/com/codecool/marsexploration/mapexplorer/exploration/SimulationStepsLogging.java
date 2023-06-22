package com.codecool.marsexploration.mapexplorer.exploration;

import com.codecool.marsexploration.mapexplorer.analizer.AllOutcomeAnalyzer;
import com.codecool.marsexploration.mapexplorer.logger.Logger;

public class SimulationStepsLogging {
    private final Simulation simulation;
    private final Logger logger;
    private final AllOutcomeAnalyzer allOutcomeAnalyzer;

    public SimulationStepsLogging(Simulation simulation, Logger logger, AllOutcomeAnalyzer allOutcomeAnalyzer) {
        this.simulation = simulation;
        this.logger = logger;
        this.allOutcomeAnalyzer = allOutcomeAnalyzer;
    }

    public void logSteps() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("STEP ").append(simulation.numberOfSteps()).append(";");

        if (allOutcomeAnalyzer.analyze(simulation) == null) {
            stringBuilder.append(" EVENT position;");
        } else {
            stringBuilder.append(" EVENT OUTCOME ").append(allOutcomeAnalyzer.analyze(simulation)).append(";");
        }

        stringBuilder.append(" UNIT " ).append(simulation.rover().getId()).append(";");
        stringBuilder.append(" POSITION ").append(simulation.rover().getPosition()).append(";");
        logger.log(stringBuilder.toString());
    }
}
