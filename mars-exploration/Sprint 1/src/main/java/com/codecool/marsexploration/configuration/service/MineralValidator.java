package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;
import java.util.Objects;

import static com.codecool.marsexploration.mapelements.model.Symbol.*;

public class MineralValidator implements Validator{

    @Override
    public boolean isValid(MapConfiguration mapConfig) {
        List<MapElementConfiguration> mapElementConfigurations = mapConfig.mapElementConfigurations();
        MapElementConfiguration mineral = mapElementConfigurations.stream().filter(element -> element.name().equals("mineral")).findAny().orElse(null);

        assert mineral != null;
        return mineral.dimensionGrowth() == 0 && Objects.equals(mineral.preferredLocationSymbol(), MOUNTAIN.getSymbol());
    }
}
