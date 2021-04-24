package com.godxvincent.httpreactivelearning.configurations;

import com.godxvincent.httpreactivelearning.models.GreetingRequest;
import com.godxvincent.httpreactivelearning.models.GreetingResponse;
import com.godxvincent.httpreactivelearning.services.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class AppConfig {


    private final GreetingService greetingService2;

    // creando una función handler para simplificar la definición del bean
    private Mono<ServerResponse> greetManyHandler(ServerRequest request) {
        return ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(greetingService2.greetMany(new GreetingRequest(request.pathVariable("name"))), GreetingResponse.class);
    }

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
        // La función get recibe una función manejadora que recibe un serverRequest de un tipo más concreto y debe retornar un server response de algun tipo concreto
        return route()
                .GET("/greeting/{name}", request -> ok().body(greetingService.greetOnce(new GreetingRequest(request.pathVariable("name"))), GreetingResponse.class))
                .GET("/greetings/{name}", request -> ok().contentType(MediaType.TEXT_EVENT_STREAM).body(greetingService.greetMany(new GreetingRequest(request.pathVariable("name"))), GreetingResponse.class))
                .GET("/greetingsHandler/{name}", this::greetManyHandler)
        .build();
    }

}
