package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;

public class ElementsCountValidator implements Validator{
    private final double maxElementsToSpaceRatio;

    public ElementsCountValidator(double maxElementsToSpaceRatio) {
        this.maxElementsToSpaceRatio = maxElementsToSpaceRatio;
    }

    @Override
    public boolean isValid(MapConfiguration mapConfig) {
        int elementsCount = 0;
        List<MapElementConfiguration> mapElementConfigurations = mapConfig.mapElementConfigurations();
        for (MapElementConfiguration mapElementConfiguration : mapElementConfigurations) {
            for (ElementToSize elementToSize : mapElementConfiguration.elementToSizes()) {
                elementsCount += elementToSize.elementCount() * elementToSize.size();
            }
        }
        return elementsCount <= Math.pow(mapConfig.mapSize(), 2) * maxElementsToSpaceRatio;
    }
}
