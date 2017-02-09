package io.swagger.api.guice;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atrestman on 2/3/17.
 */
public class PersistenceModule extends ServletModule {

    private final Logger logger = Logger.getLogger(PersistenceModule.class.getName());

    @Override
    protected void configureServlets() {

        logger.log(Level.INFO, "Installing persistence");
        this.install(new JpaPersistModule("petStore"));

        logger.log(Level.INFO, "filtering persistence through...");
        filter("/v2/*").through(PersistFilter.class);

        // bind(PetStoreInitializer.class).in(Scopes.SINGLETON);
        logger.log(Level.INFO, "Done configuring persistence");

    }
}
