package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;

public interface Validator {
    boolean isValid(MapConfiguration mapConfig);
}
