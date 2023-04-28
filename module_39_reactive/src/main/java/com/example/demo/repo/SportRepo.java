package com.example.demo.repo;

import com.example.demo.model.Sport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SportRepo extends ReactiveCrudRepository<Sport, Long> {
    Mono<Sport> findByName(String name);
}
