package com.godxvincent.httpreactivelearning.controllers;

import com.godxvincent.httpreactivelearning.models.GreetingRequest;
import com.godxvincent.httpreactivelearning.models.GreetingResponse;
import com.godxvincent.httpreactivelearning.services.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GreetingRestController {

    private final GreetingService greetingService;

    @GetMapping("/greetings/{name}")
    Mono<GreetingResponse> greet(@PathVariable String name){
        return this.greetingService.greeting( new GreetingRequest(name));
    }
}
