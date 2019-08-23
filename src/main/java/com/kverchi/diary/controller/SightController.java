package com.kverchi.diary.controller;

import com.kverchi.diary.hateoas.assembler.SightsListResourceAssembler;
import com.kverchi.diary.hateoas.resource.PostsListResource;
import com.kverchi.diary.hateoas.resource.SightsListResource;
import com.kverchi.diary.model.entity.Sight;
import com.kverchi.diary.service.sight.SightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
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

    @GetMapping("/all")
    public ResponseEntity<PagedResources<SightsListResource>> getAllSights() {
        Page<Sight> sightList = sightService.findAll();
        if(!sightList.isEmpty()) {
            List<SightsListResource> sightResources = new SightsListResourceAssembler().toResources(sightList);
            PagedResources.PageMetadata pageMetadata =
                    new PagedResources.PageMetadata(
                            sightList.getSize(), sightList.getNumber(),
                            sightList.getTotalElements(), sightList.getTotalPages());
            PagedResources<SightsListResource> pagedResources =
                    new PagedResources<SightsListResource>(sightResources, pageMetadata);
            pagedResources.add(ControllerLinkBuilder.linkTo(
                    ControllerLinkBuilder.methodOn(SightController.class).getAllSights()
            ).withSelfRel());
            return new ResponseEntity<PagedResources<SightsListResource>>(pagedResources, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
