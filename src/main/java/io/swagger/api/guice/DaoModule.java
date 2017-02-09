package io.swagger.api.guice;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.swagger.api.dao.*;
import io.swagger.common.BaseGuiceModule;

/**
 * Created by atrestman on 2/6/17.
 */
public class DaoModule extends BaseGuiceModule {

    @Provides
    @Singleton
    public UserEntityDao provideUserEntityDao(EntityManagerContainer entityManagerContainer) {
        return new UserEntityDao(entityManagerContainer);
    }

    @Provides
    @Singleton
    public PetEntityDao providePetEntityDao(EntityManagerContainer entityManagerContainer) {
        return new PetEntityDao(entityManagerContainer);
    }

    @Provides
    @Singleton
    public TagEntityDao provideTagEntityDao(EntityManagerContainer entityManagerContainer) {
        return new TagEntityDao(entityManagerContainer);
    }

    @Provides
    @Singleton
    public OrderEntityDao provideOrderEntityDao(EntityManagerContainer entityManagerContainer) {
        return new OrderEntityDao(entityManagerContainer);
    }

    @Provides
    @Singleton
    public CategoryEntityDao provideCategoryEntityDao(EntityManagerContainer entityManagerContainer) {
        return new CategoryEntityDao(entityManagerContainer);
    }
}
