package com.yammer.chesster.service;

import com.yammer.dropwizard.config.Configuration;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class ChessterConfiguration extends Configuration {

    @NotNull
    @JsonProperty
    private String sampleProperty;

    public String getSampleProperty() {
        return sampleProperty;
    }

    
}
