package io.swagger.swaggerizer;

import io.swagger.api.model.TagEntity;
import io.swagger.model.Pet;
import io.swagger.model.Tag;

/**
 * Created by atrestman on 2/8/17.
 */
public class TagSwaggerizer {
    public static Tag swaggerize(TagEntity tagEntity) {
        return new Tag()
                .name(tagEntity.getName())
                .id(tagEntity.getId());
    }

    public static TagEntity swaggerize(Tag tag, Pet pet) {
        TagEntity tagEntity = new TagEntity();

        tagEntity.setName(tag.getName());
        tagEntity.setId(tag.getId());
        tagEntity.setPetId(pet.getId());

        return tagEntity;
    }
}
