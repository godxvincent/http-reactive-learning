package com.godxvincent.httpreactivelearning.services;

import com.godxvincent.httpreactivelearning.models.GreetingRequest;
import com.godxvincent.httpreactivelearning.models.GreetingResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class GreetingService {

    private GreetingResponse greetingResponse(String name) {
        return new GreetingResponse("Hello " + name + " @ " + Instant.now());
    }

    public Mono<GreetingResponse> greeting(GreetingRequest greetingRequest) {
        return Mono.just(this.greetingResponse(greetingRequest.getName()));
    }
}
