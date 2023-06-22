package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;

import java.util.ArrayList;
import java.util.List;

public class MapElementsGeneratorImpl implements MapElementsGenerator {

    private final MapElementBuilder mapElementBuilder;

    public MapElementsGeneratorImpl(MapElementBuilder mapElementBuilder) {
        this.mapElementBuilder = mapElementBuilder;
    }


    @Override
    public List<MapElement> createAll(MapConfiguration mapConfig) {
        List<MapElement> mapElements = new ArrayList<>();
        mapConfig.mapElementConfigurations().forEach(elementType -> {
            String elementName = elementType.name();
            String elementSymbol = elementType.symbol();
            int elementDimensionGrowth = elementType.dimensionGrowth();
            String elementPreferredLocationSymbol = elementType.preferredLocationSymbol();
            elementType.elementToSizes().forEach(element -> {
                for (int i = 0; i < element.elementCount(); i++) {
                    mapElements.add(mapElementBuilder.build(element.size(), elementSymbol, elementName, elementDimensionGrowth, elementPreferredLocationSymbol));
                }
            });
        });
        return mapElements;
    }
}