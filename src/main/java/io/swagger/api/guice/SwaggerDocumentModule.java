package io.swagger.api.guice;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import io.swagger.api.SwaggerBootstrap;

/**
 * Created by atrestman on 2/3/17.
 */
public class SwaggerDocumentModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(SwaggerBootstrap.class).in(Scopes.SINGLETON);

        serve("/*").with(SwaggerBootstrap.class);
    }
}
