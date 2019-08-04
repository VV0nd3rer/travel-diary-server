package com.kverchi.diary.controller;

import com.kverchi.diary.hateoas.assembler.SightsListResourceAssembler;
import com.kverchi.diary.hateoas.resource.PostsListResource;
import com.kverchi.diary.hateoas.resource.SightsListResource;
import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.service.sight.SightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Liudmyla Melnychuk on 2.8.2019.
 */
@RestController
@RequestMapping("/sights")
public class SightController {
    private static final Logger logger = LoggerFactory.getLogger(SightController.class);
    @Autowired
    SightService sightService;

    @GetMapping
    public ResponseEntity<Resources<SightsListResource>> getSights() {
        List<Sight> sightList = sightService.findAll();
        if(!sightList.isEmpty()) {
            List<SightsListResource> sightResources = new SightsListResourceAssembler().toResources(sightList);
            Resources<SightsListResource> allResources = new Resources<SightsListResource>(sightResources);
            allResources.add(ControllerLinkBuilder.linkTo(
                    ControllerLinkBuilder.methodOn(SightController.class).getSights()
            ).withSelfRel());
            return new ResponseEntity<Resources<SightsListResource>>(allResources, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
