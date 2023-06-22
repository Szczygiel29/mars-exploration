package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.List;

public interface MapElementsGenerator {
    List<MapElement> createAll(MapConfiguration mapConfig);
}
