package com.codecool.marsexploration;

import com.codecool.marsexploration.UI.Displayer;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorService;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorService;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.configuration.service.*;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import com.codecool.marsexploration.mapelements.service.generator.MapElementsGenerator;
import com.codecool.marsexploration.mapelements.service.generator.MapElementsGeneratorImpl;
import com.codecool.marsexploration.mapelements.service.generator.MapGenerator;
import com.codecool.marsexploration.mapelements.service.generator.MapGeneratorImpl;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;
import com.codecool.marsexploration.mapelements.service.translator.MapToEmojiTranslator;
import com.codecool.marsexploration.output.service.MapFileWriter;
import com.codecool.marsexploration.output.service.MapFileWriterImpl;

import java.util.List;
import java.util.Set;

import static com.codecool.marsexploration.mapelements.model.Symbol.*;

public class Application {
    // You can change this to any directory you like
    private static final String WorkDir = "src/main/resources/";

    public static void main(String[] args) {
        Displayer displayer = new Displayer();
        displayer.displayStartText();
        MapConfiguration mapConfig = getConfiguration();

        Set<Validator> validators = Set.of(new MineralValidator(), new MountainValidator(), new WaterValidator(), new PitValidator(), new ElementsCountValidator(0.5));
        MapConfigurationValidator mapConfigurationValidator = new MainValidator(validators);

        DimensionCalculator dimensionCalculator = new DimensionCalculatorService();
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorService();

        MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementFactory);

        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl();

        MapGenerator mapGenerator = new MapGeneratorImpl(mapElementsGenerator, coordinateCalculator, mapElementPlacer);

        MapFileWriter mapFileWriter = new MapFileWriterImpl();

        MapToEmojiTranslator mapToEmojiTranslator = new MapToEmojiTranslator();

        int numberOfMapsToGenerate = 3;
        createAndWriteMaps(numberOfMapsToGenerate, mapGenerator, mapConfig, mapConfigurationValidator, mapFileWriter, displayer, mapToEmojiTranslator);
    }

    private static void createAndWriteMaps(int numberOfMapsToGenerate,
                                           MapGenerator mapGenerator,
                                           MapConfiguration mapConfig,
                                           MapConfigurationValidator mapConfigurationValidator,
                                           MapFileWriter mapFileWriter,
                                           Displayer displayer,
                                           MapToEmojiTranslator mapToEmojiTranslator) {
        if (mapConfigurationValidator.validate(mapConfig)) {
            for (int i = 0; i < numberOfMapsToGenerate; i++) {
                generateTranslateAndWriteMap(mapGenerator, mapConfig, mapFileWriter, mapToEmojiTranslator, i);
            }
            displayer.displaySuccessText();
        } else {
            displayer.displayErrorText();
        }
    }

    private static void generateTranslateAndWriteMap(MapGenerator mapGenerator,
                                                     MapConfiguration mapConfig,
                                                     MapFileWriter mapFileWriter,
                                                     MapToEmojiTranslator mapToEmojiTranslator,
                                                     int i) {
        Map map = mapGenerator.generate(mapConfig);
        Map emojiMap = mapToEmojiTranslator.translateMapToEmoji(map);
        String file = WorkDir + "map" + i + ".map";
        mapFileWriter.writeMapFile(emojiMap, file);
    }

    private static MapConfiguration getConfiguration() {

        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                MOUNTAIN.getSymbol(),
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                NOSYMBOL.getSymbol()
        );

        MapElementConfiguration pitsCfg = new MapElementConfiguration(
                PIT.getSymbol(),
                "pit",
                List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                NOSYMBOL.getSymbol()
        );

        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                MINERAL.getSymbol(),
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                MOUNTAIN.getSymbol()
        );

        MapElementConfiguration waterPocketsCfg = new MapElementConfiguration(
                WATER.getSymbol(),
                "pocket of water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                PIT.getSymbol()
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, waterPocketsCfg);

        return new MapConfiguration(50, 0.5, elementsCfg);
    }
}

