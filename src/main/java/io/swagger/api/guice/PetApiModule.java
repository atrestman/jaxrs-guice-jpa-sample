package io.swagger.api.guice;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.swagger.api.PetApiService;
import io.swagger.api.impl.PetApiServiceImpl;
import io.swagger.common.BaseGuiceModule;

/**
 * Created by atrestman on 2/2/17.
 */
public class PetApiModule extends BaseGuiceModule {

    @Provides
    @Singleton
    public PetApiService providePetApiService () {
        return new PetApiServiceImpl();
    }

}
