package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;

import java.util.Set;

public class MainValidator implements MapConfigurationValidator{

    private final Set<Validator> validators;

    public MainValidator(Set<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean validate(MapConfiguration mapConfig) {
        return validators.stream().allMatch(validator -> validator.isValid(mapConfig));
    }

}
