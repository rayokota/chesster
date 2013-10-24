package com.yammer.chesster.service;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;

import com.yammer.chesster.service.model.Game;
import com.yammer.chesster.service.resources.GameResource;
import com.yammer.chesster.service.store.GameStore;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.views.ViewBundle;
import com.yammer.dropwizard.Service;


import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChessterService extends Service<ChessterConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(Service.class);

    private final HibernateBundle<ChessterConfiguration> hibernateBundle = new HibernateBundle<ChessterConfiguration>(
            Game.class) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(ChessterConfiguration configuration) {
            return configuration.getDatabaseConfiguration();
        }
    };


    public static void main(String[] args) throws Exception {
        new ChessterService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ChessterConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/assets/"));
        bootstrap.addBundle(new ViewBundle());
    }

    
    @Override
    public void run(final ChessterConfiguration config, Environment environment) throws Exception {
        //environment.addHealthCheck(new ExampleHealthCheck());
        final SessionFactory sessionFactory = hibernateBundle.getSessionFactory();
        environment.addResource(new GameResource(new GameStore(sessionFactory, true), config.getComputerMoveTimeMs()));
        
    }
    

    
}
