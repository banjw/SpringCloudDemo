package com.learn.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("learn_route_1", fn -> fn.path("/game").uri("http://news.baidu.com/game"))
                .route("learn_route_2", fn -> fn.path("/internet").uri("http://news.baidu.com/internet"));
        return routes.build();
    }
}
