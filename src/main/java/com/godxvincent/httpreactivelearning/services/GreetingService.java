package com.godxvincent.httpreactivelearning.services;

import com.godxvincent.httpreactivelearning.models.GreetingRequest;
import com.godxvincent.httpreactivelearning.models.GreetingResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Service
public class GreetingService {

    private GreetingResponse greetingResponse(String name) {
        return new GreetingResponse("Hello " + name + " @ " + Instant.now());
    }

    public Mono<GreetingResponse> greetOnce(GreetingRequest greetingRequest) {
        return Mono.just(this.greetingResponse(greetingRequest.getName()));
    }

    public Flux<GreetingResponse> greetMany(GreetingRequest greetingRequest) {
        return Flux.fromStream(
                Stream.generate(
                        () -> this.greetingResponse(greetingRequest.getName())
                )
        ).delayElements(Duration.ofSeconds(1));
    }
}
