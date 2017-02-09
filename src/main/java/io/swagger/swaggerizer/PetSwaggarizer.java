package io.swagger.swaggerizer;

import io.swagger.api.model.CategoryEntity;
import io.swagger.api.model.PetEntity;
import io.swagger.api.model.TagEntity;
import io.swagger.model.Category;
import io.swagger.model.Pet;
import io.swagger.model.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atrestman on 2/8/17.
 */
public class PetSwaggarizer {

    public static Pet swaggarize(PetEntity petEntity, CategoryEntity categoryEntity, TagEntity tagEntity) {

        Category category = CategorySwaggerizer.swaggerize(categoryEntity);

        Tag tag = TagSwaggerizer.swaggerize(tagEntity);

        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        return new Pet()
                .id(petEntity.getId())
                .category(category)
                .name(petEntity.getName())
                .tags(tags);
    }

    public static PetEntity swaggarize(Pet pet) {

        PetEntity petEntity = new PetEntity();
        petEntity.setId(pet.getId());
        petEntity.setName(pet.getName());
        petEntity.setCategoryId(pet.getCategory().getId());

        return petEntity;
    }
}
