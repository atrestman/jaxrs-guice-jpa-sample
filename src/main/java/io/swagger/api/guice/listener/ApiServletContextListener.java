package io.swagger.api.guice.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import io.swagger.api.guice.ApiGuiceModule;
import io.swagger.api.guice.PersistenceModule;
import io.swagger.api.guice.SwaggerDocumentModule;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atrestman on 2/2/17.
 */
public class ApiServletContextListener extends GuiceServletContextListener {

    private final Logger logger = Logger.getLogger(ApiServletContextListener.class.getName());

    @Override
    protected Injector getInjector() {

        ApiGuiceModule module = new ApiGuiceModule();
        SwaggerDocumentModule swaggerDocumentModule = new SwaggerDocumentModule();
        PersistenceModule persistenceModule = new PersistenceModule();

        Injector injector = Guice.createInjector(module, swaggerDocumentModule, persistenceModule);
        logger.log(Level.INFO, "Injector created.");

        return injector;
    }

}