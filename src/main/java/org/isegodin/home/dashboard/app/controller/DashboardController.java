package org.isegodin.home.dashboard.app.controller;

import lombok.RequiredArgsConstructor;
import org.isegodin.home.dashboard.app.data.FullSensorData;
import org.isegodin.home.dashboard.app.sensor.SensorFormatterRepository;
import org.isegodin.home.dashboard.backend.api.i2c.sensor.SensorRestApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

/**
 * @author isegodin
 */
@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final SensorRestApi sensorRestApi;

    private final SensorFormatterRepository formatterRepository;

    @RequestMapping("/")
    public String index(final Model model) {
        Flux<FullSensorData> fullSensorDataFlux = sensorRestApi.listSensors()
                .flatMap(sensorInfo ->
                        sensorRestApi.getSensorValues(sensorInfo.getName())
                                .map(values -> FullSensorData.builder()
                                        .name(sensorInfo.getName())
                                        .description(sensorInfo.getDescription())
                                        .values(
                                                formatterRepository.getFormatter(sensorInfo.getName()).format(values)
                                        )
                                        .build()
                                )
                );

        model.addAttribute("sensors", new ReactiveDataDriverContextVariable(fullSensorDataFlux));

        return "index";
    }

}
