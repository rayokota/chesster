package com.yammer.chesster.service;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.yammer.dropwizard.config.Configuration;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ChessterConfiguration extends Configuration implements AssetsBundleConfiguration {

    @NotNull
    @JsonProperty
    private int computerMoveTimeMs;

    @NotNull
    @JsonProperty
    private AssetsConfiguration assets;

    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    public int getComputerMoveTimeMs() {
        return computerMoveTimeMs;
    }

    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return database;
    }
}
