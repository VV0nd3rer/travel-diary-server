package com.kverchi.diary.controller;

import com.kverchi.diary.service.CountriesSightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kverchi.diary.model.entity.CountriesSight;

import java.util.List;

/**
 * Created by Kverchi on 13.8.2018.
 */
@RestController
@RequestMapping("sights")
public class SightController {
    private final static String MAP = "sight-map";

    @Autowired
    CountriesSightService countriesSightService;

    @RequestMapping("/map")
    public ModelAndView showGmap() {
        ModelAndView mv = new ModelAndView(MAP);
        return mv;
    }
    @RequestMapping("/get-sights")
    public List<CountriesSight> getSights() {
        List<CountriesSight> sights = countriesSightService.findAll();
        return sights;
    }
    @RequestMapping("/is-coord-stored")
    public boolean isCoordStored(float x, float y) {
        boolean res = false;
        CountriesSight sight = countriesSightService.findByMapCoordXAndMapCoordY(x, y);
        if(sight != null) {
            res =  true;
        }
        return res;
    }
    @RequestMapping("/edit/{sight_id}")
    public CountriesSight editSight(@PathVariable("sight_id") int sight_id, Model model) {
        CountriesSight sight = countriesSightService.findBySightId(sight_id);
        return sight;
    }
    //TODO return ServiceResponse
    @RequestMapping("/remove/{sight_id}")
    public String removeSight(@PathVariable("sight_id") int sight_id) {
        countriesSightService.delete(sight_id);
        String res = "OK";
        return res;
    }
    @RequestMapping(value="/add-sight", method = RequestMethod.POST)
    public CountriesSight addSight(@RequestBody CountriesSight sight) {
        CountriesSight addedSight;
        if(sight.getSightId() == 0) {
            addedSight = countriesSightService.addSight(sight);
        }
        else {
            addedSight = countriesSightService.updateSight(sight);
        }
        return addedSight;
    }
}
