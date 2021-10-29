package com.github.manojesus.cities.State.services;

import com.github.manojesus.cities.Country.entities.Country;
import com.github.manojesus.cities.State.entities.State;
import com.github.manojesus.cities.State.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public Page<State> selectAll(Pageable country) {
        return stateRepository.findAll(country);
    }

    public void insert(State state) {
        Optional<State> optionalCountry = stateRepository.findStateByName(state.getName());
        if (optionalCountry.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "State already exists");
        }
        stateRepository.save(state);
    }

    @Transactional
    public void updateState(Long id,
                            String name,
                            String uf,
                            Integer ibge,
                            Country country,
                            List<Integer> ddd) {
        State state = stateRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.CONFLICT, "State already exists"));
        if (name != null &&
                !name.equals(state.getName()) &&
                name.length() > 2) {
            Optional<State> optionalCountry = stateRepository.findStateByName(name);
            if (optionalCountry.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "State already exists");
            }
            state.setName(name);
            state.setUf(uf);
        }
        if (ddd != null &&
                !ddd.equals(state.getDdd()) &&
                ddd.size() > 0) {
            state.setDdd(ddd);
        }
        if (ibge != null &&
                (int) ibge != state.getIbge() &&
                ibge > 0) {
            state.setIbge(ibge);
        }
        if (country != null &&
                country.equals(state.getCountry())) {
            state.setCountry(country);
        }
    }

    public void deleteState(Long stateId) {
        boolean exists = stateRepository.existsById(stateId);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "State not found");
        }
        System.out.println(stateId);
        stateRepository.deleteById(stateId);
    }

}
