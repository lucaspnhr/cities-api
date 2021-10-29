package com.github.manojesus.cities.City.controller;

import com.github.manojesus.cities.City.services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distances")
public class DistanceController {
    DistanceService distanceService;

    @Autowired
    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping("/by-point")
    public Double getDistanceByPoint(@RequestParam(name = "from") Long idCity1,
                                     @RequestParam(name = "to") Long idCity2) {
        return distanceService.distanceByPoint(idCity1, idCity2);
    }

    @GetMapping("/by-cube")
    public Double getDistanceByCube(@RequestParam(name = "from") Long idCity1,
                                    @RequestParam(name = "to") Long idCity2) {
        return distanceService.distanceByCube(idCity1, idCity2);
    }
    }
