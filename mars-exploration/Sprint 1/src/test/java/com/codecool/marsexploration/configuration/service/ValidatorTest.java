package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ValidatorTest {
    final String mountainSymbol = "#";
    final String pitSymbol = "&";
    final String mineralSymbol = "%";
    final String waterSymbol = "*";
    @Test
    public void checkMountains() {
        MapElementConfiguration mountainsConfiguration1 = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );
        MapConfiguration mapConfiguration1 = new MapConfiguration(140, 0.5, List.of(mountainsConfiguration1));
        assertTrue(new MountainValidator().isValid(mapConfiguration1));
        MapElementConfiguration mountainsConfiguration2 = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                4,
                ""
        );
        MapConfiguration mapConfiguration2 = new MapConfiguration(140, 0.5, List.of(mountainsConfiguration2));
        assertFalse(new MountainValidator().isValid(mapConfiguration2));
    }

    @Test
    public void checkPits() {
        MapElementConfiguration pitsConfiguration1 = new MapElementConfiguration(
                pitSymbol,
                "pit",
                List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                ""
        );
        MapConfiguration mapConfiguration1 = new MapConfiguration(400, 0.5, List.of(pitsConfiguration1));
        assertTrue(new PitValidator().isValid(mapConfiguration1));
        MapElementConfiguration pitsConfiguration2 = new MapElementConfiguration(
                pitSymbol,
                "pit",
                List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                8,
                ""
        );
        MapConfiguration mapConfiguration2 = new MapConfiguration(400, 0.5, List.of(pitsConfiguration2));
        assertFalse(new PitValidator().isValid(mapConfiguration2));
    }
    @Test
    public void checkMinerals() {
        MapElementConfiguration mineralsConfiguration1 = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                mountainSymbol
        );
        MapConfiguration mapConfiguration1 = new MapConfiguration(400, 0.5, List.of(mineralsConfiguration1));
        assertTrue(new MineralValidator().isValid(mapConfiguration1));
        MapElementConfiguration mineralsConfiguration2 = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                4,
                mountainSymbol
        );
        MapConfiguration mapConfiguration2 = new MapConfiguration(400, 0.5, List.of(mineralsConfiguration2));
        assertFalse(new MineralValidator().isValid(mapConfiguration2));
    }
    @Test
    public void checkWater() {
        MapElementConfiguration waterConfiguration1 = new MapElementConfiguration(
                waterSymbol,
                "pocket of water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                pitSymbol
        );
        MapConfiguration mapConfiguration1 = new MapConfiguration(400, 0.5, List.of(waterConfiguration1));
        assertTrue(new WaterValidator().isValid(mapConfiguration1));
        MapElementConfiguration waterConfiguration2 = new MapElementConfiguration(
                waterSymbol,
                "pocket of water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                3,
                pitSymbol
        );
        MapConfiguration mapConfiguration2 = new MapConfiguration(400, 0.5, List.of(waterConfiguration2));
        assertFalse(new WaterValidator().isValid(mapConfiguration2));
    }

    @Test
    public void checkElementsToSpaceRatio() {
        double maxElementToSpaceRatio = 0.5;
        int mapSize = 10;
        MapElementConfiguration waterConfiguration1 = new MapElementConfiguration(
                waterSymbol,
                "pocket of water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                pitSymbol
        );

        MapElementConfiguration mountainsConfiguration1 = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                List.of(
                        new ElementToSize(2, 20)
                ),
                3,
                ""
        );
        MapConfiguration mapConfig1 = new MapConfiguration(mapSize, maxElementToSpaceRatio, List.of(mountainsConfiguration1, waterConfiguration1));
        assertTrue(new ElementsCountValidator(maxElementToSpaceRatio).isValid(mapConfig1));
        MapElementConfiguration waterConfiguration2 = new MapElementConfiguration(
                waterSymbol,
                "pocket of water",
                List.of(
                        new ElementToSize(21, 1)
                ),
                0,
                pitSymbol
        );

        MapElementConfiguration mountainsConfiguration2 = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                List.of(
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );
        MapConfiguration mapConfig2 = new MapConfiguration(mapSize, maxElementToSpaceRatio, List.of(mountainsConfiguration2, waterConfiguration2));
        assertFalse(new ElementsCountValidator(maxElementToSpaceRatio).isValid(mapConfig2));
    }
}
