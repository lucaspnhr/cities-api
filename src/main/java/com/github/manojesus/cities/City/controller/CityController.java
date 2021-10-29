package com.github.manojesus.cities.City.controller;

import com.github.manojesus.cities.City.entities.City;
import com.github.manojesus.cities.City.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Cities")
public class CityController {
    CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Page<City> selectCountry(Pageable country){
        return cityService.selectAll(country);
    }

    @PostMapping
    public void insertCountry(@RequestBody City city){
        cityService.insert(city);
    }

    @PutMapping(path="{countryId}")
    public void updateCountry(@PathVariable("countryId") final Long id,
                                                                   @RequestParam (required = false)final String name,
                                                                   @RequestParam (required = false)final String uf,
                                                                    @RequestParam (required = false)final Integer ibge,
                                                                    @RequestParam (required = false)final Point geoLocation ){
        cityService.updateCountry(id,name,uf,ibge,geoLocation);
    }
    @DeleteMapping(path="{countryId}")
    public void deleteCountry(@PathVariable("countryId")final Long countryId){
        cityService.deleteCity(countryId);
    }
}
