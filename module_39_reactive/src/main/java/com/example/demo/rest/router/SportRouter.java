package com.example.demo.rest.router;

import com.example.demo.rest.handler.SportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration(proxyBeanMethods = false)
public class SportRouter {
    @Bean
    public RouterFunction<ServerResponse> route(SportHandler sportHandler) {
        return RouterFunctions
                .route(GET("/api/v1/sport"), sportHandler::getSportByName)
                .andRoute(POST("/api/v1/sport/{sportName}"),sportHandler::createSport);
    }
}
