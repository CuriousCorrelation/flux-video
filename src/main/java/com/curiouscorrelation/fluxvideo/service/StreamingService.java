package com.curiouscorrelation.fluxvideo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Streaming service to provide video resource in `Mono`
 */
@Service
public class StreamingService {

    /**
     * Set the video location to `resource`.
     */
    private static final String FORMAT = "classpath:video/%s.mp4";

    /**
     * Autowire `ResourceLoader` to supply video file.
     */
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * Provides video matching on `title` arg.
     * @param title Video title to load
     * @return {@code Mono} of resource
     */
    public Mono<Resource> getVideo(final String title) {
        /*
          Video resource will be a `Mono` of the resource.
         */
        return Mono
                /*
                  Supply resource from `resourceLoader` and provide it to `Mono`
                 */
                .fromSupplier
                /*
                  Resource provided by `fromSupplier`,
                  where supplier is a lambda calling `getResource`.
                 */
                        (() -> resourceLoader.getResource(String.format(FORMAT, title)));
    }
}
