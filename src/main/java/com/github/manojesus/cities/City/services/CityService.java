package com.github.manojesus.cities.City.services;

import com.github.manojesus.cities.City.entities.City;
import com.github.manojesus.cities.City.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CityService {

    CityRepository cityRepository;
    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Page<City> selectAll(Pageable country){
        return cityRepository.findAll(country);
    }

    public void insert(City city) {
        Optional<City> optionalCountry = cityRepository.findCityByName(city.getName());
        if(optionalCountry.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Country already exists");
        }
        cityRepository.save(city);
    }

    @Transactional
    public void updateCountry(Long id,
                              String name,
                              String uf,
                              Integer ibge,
                              Point geoLocation){
        City city =  cityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.CONFLICT, "City already exists"));
        if(name != null &&
              !name.equals(city.getName())&&
              name.length()>2) {
                Optional<City> optionalCity = cityRepository.findCityByName(name);
                if(optionalCity.isPresent()){
                    throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "City already exists");}
                city.setName(name) ;
                city.setUf(uf);
        }
        if(geoLocation != null &&
                !geoLocation.equals(city.getGeoLocation()) ) {
            city.setGeoLocation(geoLocation);
        }
        if(ibge != null &&
                ibge != (int) city.getIbge()&&
                ibge > 2) {
            city.setIbge(ibge);
        }
    }

    public void deleteCity(Long cityId) {
        boolean exists = cityRepository.existsById(cityId);
        if(!exists){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "City not found");
        }
        System.out.println(cityId);
        cityRepository.deleteById(cityId);
    }
}
