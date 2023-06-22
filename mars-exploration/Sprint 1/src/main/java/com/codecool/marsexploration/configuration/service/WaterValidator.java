package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;
import java.util.Objects;

import static com.codecool.marsexploration.mapelements.model.Symbol.PIT;

public class WaterValidator implements Validator{
    @Override
    public boolean isValid(MapConfiguration mapConfig) {
        List<MapElementConfiguration> mapElementConfigurations = mapConfig.mapElementConfigurations();
        MapElementConfiguration water = mapElementConfigurations.stream().filter(element -> element.name().equals("pocket of water")).findAny().orElse(null);

        assert water != null;
        return water.dimensionGrowth() == 0 && Objects.equals(water.preferredLocationSymbol(), PIT.getSymbol());
    }
}
