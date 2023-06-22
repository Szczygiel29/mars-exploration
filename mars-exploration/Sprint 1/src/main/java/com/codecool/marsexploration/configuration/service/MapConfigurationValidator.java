package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;

import java.util.Set;

public interface MapConfigurationValidator {
    boolean validate(MapConfiguration mapConfig);
}
