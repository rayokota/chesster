package com.yammer.chesster.service;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;

import com.yammer.chesster.service.resources.GameResource;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import com.yammer.dropwizard.Service;








import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChessterService extends Service<ChessterConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(Service.class);

    

    public static void main(String[] args) throws Exception {
        new ChessterService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ChessterConfiguration> bootstrap) {
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/assets/"));
        bootstrap.addBundle(new ViewBundle());
    }

    
    @Override
    public void run(final ChessterConfiguration config, Environment environment) throws Exception {
        //environment.addHealthCheck(new ExampleHealthCheck());
        environment.addResource(new GameResource());
        
    }
    

    
}
