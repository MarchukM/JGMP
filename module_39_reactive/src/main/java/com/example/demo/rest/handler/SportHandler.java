package com.example.demo.rest.handler;

import com.example.demo.model.Sport;
import com.example.demo.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class SportHandler {

    private final SportService sportService;

    @Autowired
    public SportHandler(SportService sportService) {
        this.sportService = sportService;
    }

    public Mono<ServerResponse> getSportByName(ServerRequest request) {
        String sportName = request.queryParam("q").orElse(null);
        return sportService.findByName(sportName)
                .flatMap(sport -> ServerResponse.ok().body(Mono.just(sport), Sport.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createSport(ServerRequest request) {
        String sportName = request.pathVariable("sportName");
        return sportService.save(new Sport(sportName))
                .flatMap(sport -> ServerResponse.ok().body(Mono.just(sport), Sport.class))
                .onErrorResume(e -> Mono.just("Error " + e.getMessage())
                        .flatMap(s -> ServerResponse.badRequest()
                                .contentType(MediaType.TEXT_PLAIN)
                                .bodyValue(s)));
    }
}
