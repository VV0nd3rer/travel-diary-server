package com.kverchi.diary.hateoas.assembler;

import com.kverchi.diary.controller.SightController;
import com.kverchi.diary.hateoas.resource.SightsListResource;
import com.kverchi.diary.model.entity.Sight;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Liudmyla Melnychuk on 2.8.2019.
 */
public class SightsListResourceAssembler extends ResourceAssemblerSupport<Sight, SightsListResource> {

    public SightsListResourceAssembler() {
        super(SightController.class, SightsListResource.class);
    }
    @Override
    protected SightsListResource instantiateResource(Sight sight) {
        return new SightsListResource(sight);
    }
    @Override
    public SightsListResource toResource(Sight sight) {
        return createResourceWithId(sight.getSightId(), sight);
    }
}
