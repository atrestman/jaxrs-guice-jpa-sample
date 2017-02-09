package io.swagger.swaggerizer;

import io.swagger.api.model.CategoryEntity;
import io.swagger.model.Category;

/**
 * Created by atrestman on 2/8/17.
 */
public class CategorySwaggerizer {

    public static Category swaggerize(CategoryEntity categoryEntity) {
        return new Category()
                .name(categoryEntity.getName())
                .id(categoryEntity.getId());
    }

    public static CategoryEntity swaggerize(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        return categoryEntity;
    }
}
