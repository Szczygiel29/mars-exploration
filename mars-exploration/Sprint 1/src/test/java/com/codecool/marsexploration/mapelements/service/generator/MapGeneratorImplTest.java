package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorService;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorService;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MapGeneratorImplTest {
    @Test
    public void generateMap_ValidMapConfiguration_ReturnsMap() {

        DimensionCalculator dimensionCalculator = new DimensionCalculatorService();
        MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementFactory);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorService();
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl();
        MapGenerator mapGenerator = new MapGeneratorImpl(mapElementsGenerator, coordinateCalculator, mapElementPlacer);

        List<MapElementConfiguration> elementConfigurations = List.of(
                new MapElementConfiguration("#", "mountain", List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ), 3, ""),
                new MapElementConfiguration("&", "pit", List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ), 10, ""),
                new MapElementConfiguration("%", "mineral", List.of(
                        new ElementToSize(10, 1)
                ), 0, "#"),
                new MapElementConfiguration("*", "pocket of water", List.of(
                        new ElementToSize(10, 1)
                ), 0, "&")
        );

        MapConfiguration mapConfig = new MapConfiguration(100, 0.5, elementConfigurations);

        Map result = mapGenerator.generate(mapConfig);

        assertThat(result).isNotNull();

        int ADD_NUMBER_OF_NEW_LINE_SYMBOL = 100;
        assertThat((double)result.toString().length())
                .isEqualTo(Math.pow(100, 2) + ADD_NUMBER_OF_NEW_LINE_SYMBOL);

        int countMountain = result.toString().split("#").length - 1;
        assertThat(countMountain).isEqualTo(70);

        int countPit = result.toString().split("&").length -1;
        assertThat(countPit).isEqualTo(40);

        int countMineral = result.toString().split("%").length -1;
        assertThat(countMineral).isEqualTo(10);

        int countWater = result.toString().split("\\*").length -1;
        assertThat(countWater).isEqualTo(10);
    }
}