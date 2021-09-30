package com.curiouscorrelation.fluxvideo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

@SpringBootTest
class StreamingServiceTest {

    @Autowired
    private StreamingService streamingService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void getVideo() {
        var resource =
                resourceLoader.getResource("classpath:video/curious.mp4");

        StepVerifier
                .create(streamingService.getVideo("curious"))
                .expectNext(resource)
                .verifyComplete();
    }
}