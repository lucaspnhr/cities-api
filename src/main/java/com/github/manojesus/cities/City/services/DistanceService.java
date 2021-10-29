package com.github.manojesus.cities.City.services;

import com.github.manojesus.cities.City.entities.City;
import com.github.manojesus.cities.City.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DistanceService {
    CityRepository cityRepository;
    @Autowired
    public DistanceService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Double  distanceByPoint(Long idCity1, Long idCity2){
        return  cityRepository.distanceByPoint(idCity1, idCity2);
    }

    public Double distanceByCube(Long idCity1, Long idCity2){
        List<City> cities = cityRepository.findAllById(Arrays.asList(idCity1, idCity2));
        Point cityPoint1 = cities.get(0).getGeoLocation();
        Point cityPoint2 = cities.get(1).getGeoLocation();
        return cityRepository.distanceByCube(cityPoint1.getX(), cityPoint1.getY(), cityPoint2.getX(), cityPoint2.getY());
    }
}
