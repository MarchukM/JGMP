package com.example.demo.setup;

import com.example.demo.model.Sport;
import com.example.demo.repo.SportRepo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Component
public class Setup implements CommandLineRunner {

    public static final String HTTPS_SPORTS_API_DECATHLON_COM_SPORTS = "https://sports.api.decathlon.com/sports";
    private final SportRepo sportRepo;
    private WebClient client;

    @Autowired
    public Setup(SportRepo sportRepo) {
        this.sportRepo = sportRepo;
    }


    @Override
    public void run(String... args) {
        client = WebClient.builder()
                .codecs(config -> config
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build();

        sportRepo.count()
                .doOnSuccess(this::parseIfDbEmpty)
                .subscribe();

    }

    private void parseIfDbEmpty(Long count) {
        if (count == 0) {
            Flux<Sport> sportFlux = client
                    .get()
                    .uri(HTTPS_SPORTS_API_DECATHLON_COM_SPORTS)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(jsonNode -> jsonNode.path("data"))
                    .flatMapMany(Flux::fromIterable)
                    .map(Setup::toSport);

            sportRepo.saveAll(sportFlux
                            .doOnRequest(e -> System.out.println("Take next 20"))
                            .limitRate(20)) // backpressure
                    .subscribe();
        }
    }

    private static Sport toSport(JsonNode jsonNode) {
        var sport = new Sport();
        sport.setName(jsonNode.path("attributes").path("name").asText());
        return sport;
    }
}
