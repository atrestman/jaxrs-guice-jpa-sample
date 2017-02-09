package io.swagger.api.guice;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atrestman on 2/6/17.
 */
public class PetStoreInitializer {

    private final Logger logger = Logger.getLogger(PetStoreInitializer.class.getName());

    @Inject
    PetStoreInitializer(PersistService service) {
        logger.log(Level.INFO, "Starting persistence service...");

        service.start();

    }
}
