package com.github.manojesus.cities.Country.services;

import com.github.manojesus.cities.Country.entities.Country;
import com.github.manojesus.cities.Country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CountryService {

    CountryRepository countryRepository;
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Page<Country> selectAll(Pageable country){
        return countryRepository.findAll(country);
    }

    public void insert(Country country) {
        Optional<Country> optionalCountry = countryRepository.findCountryByName(country.getName());
        if(optionalCountry.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Country already exists");
        }
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(Long id,
                                                                  String name,
                                                                  String portugueseName,
                                                                  String uf,
                                                                  Integer bacen){
        Country country =  countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.CONFLICT, "Country already exists"));
        if(name != null &&
              !name.equals(country.getName())&&
              name.length()>2) {
                Optional<Country> optionalCountry = countryRepository.findCountryByName(name);
                if(optionalCountry.isPresent()){
                    throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Country already exists");}
                country.setName(name) ;
                country.setUf(uf);
        }
        if(portugueseName != null &&
                !portugueseName.equals(country.getPortugueseName())&&
                portugueseName.length()>2) {
            country.setPortugueseName(portugueseName);
        }
        if(bacen != null &&
              bacen != (int) country.getBacen()&&
              bacen > 2) {
            country.setName(name);
        }
    }

    public void deleteCountry(Long countryId) {
        boolean exists = countryRepository.existsById(countryId);
        if(!exists){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Country not found");
        }
        System.out.println(countryId);
        countryRepository.deleteById(countryId);
    }
}
