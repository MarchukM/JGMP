package com.example.demo.service;

import com.example.demo.model.Sport;
import com.example.demo.repo.SportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Objects;

@Service
public class SportService {

    private final SportRepo sportRepo;

    @Autowired
    public SportService(SportRepo sportRepo) {
        this.sportRepo = sportRepo;
    }

    public Mono<Sport> findByName(String name) {
        return sportRepo.findByName(name);
    }

    public Mono<Sport> save(Sport sport) {
        return sportRepo.findByName(sport.getName())
                .flatMap(existingSport -> Mono.<Sport>error(
                        new IllegalStateException("Sport with name " + sport.getName() + " already exists")))
                .switchIfEmpty(Mono.defer(() -> sportRepo.save(sport)));
    }

    public Flux<Sport> saveAll(Collection<Sport> sports) {
        return sportRepo.saveAll(sports);
    }
}
