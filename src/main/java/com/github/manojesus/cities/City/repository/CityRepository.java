package com.github.manojesus.cities.City.repository;

import com.github.manojesus.cities.City.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query("SELECT c FROM City c WHERE c.name = ?1")
    Optional<City> findCityByName(String name);

    @Query(value = "SELECT ((SELECT  lat_lon FROM cidade WHERE id = ?1) <@> (SELECT lat_lon FROM cidade WHERE id = ?2)) as distance", nativeQuery = true)
    Double distanceByPoint(final Long idCityFrom, final Long idCityTo);

    @Query(value = "SELECT earth_distance(ll_to_earth(?1,?2), ll_to_earth(?3,?4)) as distance", nativeQuery = true)
    Double distanceByCube(final Double lat1, final Double lon1, final Double lat2, final Double lon2);
}
