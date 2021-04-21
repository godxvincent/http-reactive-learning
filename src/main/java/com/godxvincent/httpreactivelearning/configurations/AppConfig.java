package com.godxvincent.httpreactivelearning.configurations;

import com.godxvincent.httpreactivelearning.models.GreetingRequest;
import com.godxvincent.httpreactivelearning.models.GreetingResponse;
import com.godxvincent.httpreactivelearning.services.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class AppConfig {

    // Functional Reactive Style --> Otra forma de exponer endpoints.
    @Bean
    public RouterFunction<ServerResponse> routes(GreetingService greetingService) {
        /*return route().GET("/greeting/{name}", new HandlerFunction<ServerResponse>() {
            public Mono<ServerResponse> handle(ServerRequest serverRequest) {
                GreetingRequest greetingRequest = new GreetingRequest(serverRequest.pathVariable("name"));
                Mono<GreetingResponse> greet = greetingService.greeting(greetingRequest);
                return ServerResponse.ok().body(greet, GreetingResponse.class);
            }
        }).build();*/

        return route().GET("/greeting/{name}", request ->
                ok().body(
                            greetingService.greeting(
                                    new GreetingRequest(request.pathVariable("name"))),
                            GreetingResponse.class
                    )
        ).build();
    }
}
