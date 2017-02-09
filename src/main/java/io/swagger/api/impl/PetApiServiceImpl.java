package io.swagger.api.impl;

import com.google.inject.Inject;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.PetApiService;
import io.swagger.api.dao.CategoryEntityDao;
import io.swagger.api.dao.PetEntityDao;
import io.swagger.api.dao.TagEntityDao;
import io.swagger.api.model.CategoryEntity;
import io.swagger.api.model.PetEntity;
import io.swagger.api.model.TagEntity;
import io.swagger.model.Pet;
import io.swagger.swaggerizer.CategorySwaggerizer;
import io.swagger.swaggerizer.PetSwaggarizer;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;


public class PetApiServiceImpl extends PetApiService {

    @Inject
    PetEntityDao petEntityDao;

    @Inject
    TagEntityDao tagEntityDao;

    @Inject
    CategoryEntityDao categoryEntityDao;

    @Override
    public Response addPet(Pet body, SecurityContext securityContext) throws NotFoundException {

        PetEntity petEntity = PetSwaggarizer.swaggarize(body);
        petEntityDao.create(petEntity);

        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(body.getName());
        tagEntity.setPetId(body.getId());
        tagEntityDao.create(tagEntity);

        CategoryEntity categoryEntity = CategorySwaggerizer.swaggerize(body.getCategory());
        if (categoryEntityDao.getByName(categoryEntity.getName()) == null) {
            categoryEntityDao.create(categoryEntity);
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deletePet(Long petId, String apiKey, SecurityContext securityContext) throws NotFoundException {
        PetEntity petEntity = petEntityDao.find(petId);
        TagEntity tagEntity = tagEntityDao.getByPetId(petId);
        if (petEntity != null) {
            petEntityDao.remove(petId);
        }
        if (tagEntity != null) {
            tagEntityDao.remove(tagEntity.getId());
        }

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response findPetsByStatus(List<String> status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response findPetsByTags(List<String> tags, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getPetById(Long petId, SecurityContext securityContext) throws NotFoundException {

        Pet pet = new Pet();
        PetEntity petEntity = petEntityDao.find(petId);
        TagEntity tagEntity = tagEntityDao.getByPetId(petId);
        CategoryEntity categoryEntity = categoryEntityDao.find(petEntity.getCategoryId());
        if (petEntity != null && tagEntity != null && categoryEntity != null) {
            pet = PetSwaggarizer.swaggarize(petEntity, categoryEntity, tagEntity);
        }
        return Response.ok().entity(pet).build();
    }
    @Override
    public Response updatePet(Pet body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updatePetWithForm(Long petId, String name, String status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
