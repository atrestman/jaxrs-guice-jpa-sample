package io.swagger.api.guice;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.swagger.api.StoreApiService;
import io.swagger.api.impl.StoreApiServiceImpl;
import io.swagger.common.BaseGuiceModule;

/**
 * Created by atrestman on 2/2/17.
 */
public class StoreApiModule extends BaseGuiceModule {

    @Provides
    @Singleton
    public StoreApiService provideStoreApiService () {
        return new StoreApiServiceImpl();
    }

}
