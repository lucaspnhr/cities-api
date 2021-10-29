package com.github.manojesus.cities.Country.repository;

import com.github.manojesus.cities.Country.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    @Query("SELECT c FROM Country c WHERE c.name = ?1")
    Optional<Country> findCountryByName(String name);


}
