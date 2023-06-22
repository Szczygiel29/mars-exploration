package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;
import java.util.Objects;

import static com.codecool.marsexploration.mapelements.model.Symbol.NOSYMBOL;

public class PitValidator implements Validator{
    @Override
    public boolean isValid(MapConfiguration mapConfig) {
        List<MapElementConfiguration> mapElementConfigurations = mapConfig.mapElementConfigurations();
        MapElementConfiguration pit = mapElementConfigurations.stream().filter(element -> element.name().equals("pit")).findAny().orElse(null);

        assert pit != null;
        return pit.dimensionGrowth() == 10 && Objects.equals(pit.preferredLocationSymbol(), NOSYMBOL.getSymbol());
    }
}
