package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorService;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapElementsGeneratorImplTest {
    DimensionCalculator dimensionCalculator = new DimensionCalculatorService();
    MapElementBuilder mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator);
    MapElementsGeneratorImpl mapElementsGenerator = new MapElementsGeneratorImpl(mapElementBuilder);

    @Test
    public void mapElementsGeneratorTest() {
        List<MapElementConfiguration> mapElementConfigurations = new ArrayList<MapElementConfiguration>();

        mapElementConfigurations.add(new MapElementConfiguration("#", "mountains",
                List.of(new ElementToSize(10,16), new ElementToSize(2,4)),
                3, "none"));
        mapElementConfigurations.add(new MapElementConfiguration("&", "pits",
                List.of(new ElementToSize(5,9), new ElementToSize(12,16)),
                10, "none"));
        mapElementConfigurations.add(new MapElementConfiguration("%", "minerals",
                List.of(new ElementToSize(20,25), new ElementToSize(3,4)),
                0, "#"));
        mapElementConfigurations.add(new MapElementConfiguration("*", "water",
                List.of(new ElementToSize(50,64), new ElementToSize(1,1)),
                0, "&"));
        MapConfiguration mapConfig = new MapConfiguration(40, 0.8, mapElementConfigurations);

        List<MapElement> mapElements = mapElementsGenerator.createAll(mapConfig);
        assertThat(mapElements)
                .hasSize(103);
        //testing dimension size of returned elements
        assertThat(mapElements.get(0).getRepresentation())
                .hasDimensions(7,7);
        assertThat(mapElements.get(12).getRepresentation())
                .hasDimensions(13,13);
        assertThat(mapElements.get(29).getRepresentation())
                .hasDimensions(5,5);
        assertThat(mapElements.get(52).getRepresentation())
                .hasDimensions(8,8);
        //testing content of returned elements
        assertTrue(Arrays.stream(mapElements.get(0).getRepresentation()).flatMap(Arrays::stream).allMatch(s -> s.equals(" ") || s.equals("#")));
        assertTrue(Arrays.stream(mapElements.get(12).getRepresentation()).flatMap(Arrays::stream).allMatch(s -> s.equals(" ") || s.equals("&")));
        assertTrue(Arrays.stream(mapElements.get(29).getRepresentation()).flatMap(Arrays::stream).allMatch(s -> s.equals(" ") || s.equals("%")));
        assertTrue(Arrays.stream(mapElements.get(52).getRepresentation()).flatMap(Arrays::stream).allMatch(s -> s.equals(" ") || s.equals("*")));
    }
}
