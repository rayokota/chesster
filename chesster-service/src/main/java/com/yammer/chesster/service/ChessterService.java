package com.yammer.chesster.service;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.google.common.base.Optional;

import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import com.yammer.dropwizard.Service;








import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessterService extends Service<ChessterConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(Service.class);

    

    public static void main(String[] args) throws Exception {
        new ChessterService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ChessterConfiguration> bootstrap) {
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/pgn4web/"));
        bootstrap.addBundle(new ViewBundle());
    }

    
    @Override
    public void run(final ChessterConfiguration config, Environment environment) throws Exception {
        //environment.addHealthCheck(new ExampleHealthCheck());
        //environment.addResource(new ExampleResource(config.getSampleProperty()));
        
    }
    

    
}
