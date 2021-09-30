package com.curiouscorrelation.fluxvideo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.curiouscorrelation.fluxvideo.service.StreamingService;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class FluxVideoApplication {

	@Autowired
	private StreamingService streamingService;

	/**
	 * Returns reactive `Mono` publisher containing video returned by {@link StreamingService}
	 *
	 * @param title Video title provided by the caller
	 * @param range Range header provided by the browser.
	 * The required range should be specified by the browser.
	 */
	@GetMapping(value = "video/{title}", produces = "video/mp4")
	public Mono<Resource> getVideos(@PathVariable final String title,
									@RequestHeader("Range") final String range) {
		return streamingService.getVideo(title);
	}

	public static void main(final String[] args) {
		SpringApplication.run(FluxVideoApplication.class, args);
	}
}
