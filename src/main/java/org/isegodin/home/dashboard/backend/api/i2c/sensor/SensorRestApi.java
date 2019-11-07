package org.isegodin.home.dashboard.backend.api.i2c.sensor;

import org.isegodin.home.dashboard.backend.api.i2c.sensor.data.SensorInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author isegodin
 */
@RequestMapping("/sensor")
public interface SensorRestApi {

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<SensorInfo> listSensors();

    @GetMapping(path = "/value/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<Map<String, Object>> getSensorValues(@PathVariable String name);

}
