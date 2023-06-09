package com.thanhle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thanhle.domain.Building;
import com.thanhle.service.IBuildingService;

@RestController
public class BuildingController {

    @Autowired
    private IBuildingService buildingService;

    @RequestMapping(value = "/saveBuilding", method = RequestMethod.POST)
    public Building saveBuilding(@RequestBody Building building) {
        return buildingService.saveOrUpdateBuilding(building);
    }
    
    @RequestMapping(value = "/getBuilding/{roomType}", method = RequestMethod.GET)
    public List<Building> getBuilding(@PathVariable String roomType) {
        return (List<Building>) buildingService.getBuildingByName(roomType);
    }
    


}

