package com.github.manojesus.cities.State.repository;

import com.github.manojesus.cities.State.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {

    @Query("SELECT s FROM State s WHERE s.name = ?1")
    Optional<State> findStateByName(String name);


}
