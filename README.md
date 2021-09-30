# `flux-video`

A reactive video delivery microservice written in `Java` using `Spring` and `reactive`.

## Workings

### Service (Business layer)

The streaming service is provided by `StreamingService` class annotated with `@Service` to indicate business logic.

The video itself is loaded using Spring's `ResourceLoader`.

Video being a single resource, the service method `getVideo` returns a `Mono`

``` java
return Mono.fromSupplier(() -> resourceLoader.getResource(...));
```

### Controller

`FluxVideoApplication` class represents the [controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) annotated with `@RestController`.

The `StreamingService` is `@Autowired` and mapped to `video/{title}`

``` java
@GetMapping(value = "video/{title}", produces = "video/mp4")
public Mono<Resource> getVideos(@PathVariable final String title,
                                @RequestHeader("Range") final String range) {
		return streamingService.getVideo(title);
	}
```

### View

The video contents are called via `video` tag using a simple `index.html` in `resources/static`.

``` html
<video src="video/curious" width="720px" height="480px" controls preload="none"></video>
```
