package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;
import java.util.Objects;

import static com.codecool.marsexploration.mapelements.model.Symbol.NOSYMBOL;

public class MountainValidator implements Validator{
    @Override
    public boolean isValid(MapConfiguration mapConfig) {
        List<MapElementConfiguration> mapElementConfigurations = mapConfig.mapElementConfigurations();
        MapElementConfiguration mountain = mapElementConfigurations.stream().filter(element -> element.name().equals("mountain")).findAny().orElse(null);

        assert mountain != null;
        return mountain.dimensionGrowth() == 3 && Objects.equals(mountain.preferredLocationSymbol(), NOSYMBOL.getSymbol());
    }
}
