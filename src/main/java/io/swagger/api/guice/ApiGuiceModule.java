package io.swagger.api.guice;

import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import io.swagger.common.BaseGuiceModule;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by atrestman on 2/2/17.
 */
public class ApiGuiceModule extends BaseGuiceModule {

    private final Logger logger = Logger.getLogger(ApiGuiceModule.class.getName());

    @Override
    protected void configureServlets() {

        // Load properties and configure endpoints
        super.loadProperties(
                new String[]{
                        "/service.properties",
                        "/secret.properties"
                },
                new String[]{
                        "/tmp/local.properties"
                });

        // Bind the validator, to validate all resources.
        //this.bindListener(
        //        Matchers.any(),
        //        (ProvisionListener) new ValidationListener(this.provideValidator()));

        this.install(new StoreApiModule());
        this.install(new PetApiModule());
        this.install(new UserApiModule());
        this.install(new DaoModule());

        // Set up the Jersey/Guice integration
        ResourceConfig resourceConfig = new PackagesResourceConfig(
                "io.swagger.jaxrs.listing",
                "io.swagger.sample.resource",
                "io.swagger.api");
        for (Class<?> resource : resourceConfig.getClasses()) {
            this.bind(resource).in(Scopes.SINGLETON);
            logger.log(Level.INFO, "Registered Jersey Resource: {0}", resource.getName());
        }
        logger.log(Level.INFO, "Registered {0} Jersey Resources.", resourceConfig.getClasses().size());

        // Serve /mix through the GuiceContainer
        this.serve("/v2/*").with(GuiceContainer.class);
    }

    @Provides
    @Singleton
    public Validator provideValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
