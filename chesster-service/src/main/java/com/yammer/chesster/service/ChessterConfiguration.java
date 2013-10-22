package com.yammer.chesster.service;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.yammer.dropwizard.config.Configuration;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class ChessterConfiguration extends Configuration implements AssetsBundleConfiguration {

    @NotNull
    @JsonProperty
    private String sampleProperty;

    @NotNull
    @JsonProperty
    private AssetsConfiguration assets;

    public String getSampleProperty() {
        return sampleProperty;
    }

    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }

}
