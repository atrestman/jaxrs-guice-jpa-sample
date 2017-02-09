package io.swagger.api.guice;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.swagger.api.UserApiService;
import io.swagger.api.dao.UserEntityDao;
import io.swagger.api.impl.UserApiServiceImpl;
import io.swagger.common.BaseGuiceModule;

/**
 * Created by atrestman on 2/3/17.
 */
public class UserApiModule extends BaseGuiceModule {

    @Provides
    @Singleton
    public UserApiService providePetApiService(UserEntityDao userEntityDao) {
        return new UserApiServiceImpl(userEntityDao);
    }
}
