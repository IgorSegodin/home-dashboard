package org.isegodin.home.dashboard.app.controller;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
//import org.isegodin.home.dashboard.backend.api.i2c.sensor.SensorRestApi;
import org.isegodin.home.dashboard.HomeDashboardApplication;
import org.isegodin.home.dashboard.backend.api.i2c.sensor.SensorRestApi;
import org.isegodin.home.dashboard.backend.api.i2c.sensor.data.SensorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author isegodin
 */
@Controller
//@RequiredArgsConstructor
public class DashboardController {

//    private final HomeDashboardApplication.SensorRestApi sensorRestApi;
    @Autowired
    private SensorRestApi sensorRestApi;

//    @Autowired
//    private HttpMessageConverters httpMessageConverters;

    @RequestMapping("/")
    public String index(final Model model) {

//        Flux<String> dataFlux = Flux.just("Test1", "Test2");
//        Flux<Integer> dataFlux = Flux.fromStream(Stream.iterate(0, i -> i + 2)).delayElements(Duration.ofSeconds(2));
//
//        IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(dataFlux, 1);
//
//        model.addAttribute("testData", reactiveDataDrivenMode);

//        Flux<SensorInfo> sensorInfoFlux = sensorRestApi.listSensors();

        // TODO use streams without blocking

        //        model.addAttribute("sensors",
//                new ReactiveDataDriverContextVariable(
//                        sensorInfoFlux
//                                .transform(sensorInfoFlux -> )
//                        .flatMap(info -> {
//                            sensorRestApi.getSensorValues()
//                        })
//                        , 1
//                )
//        );


//        List<FullSensorData> data = sensorRestApi.listSensors().stream()
//                .map(info -> {
//                    Map<String, Object> sensorData = sensorRestApi.getSensorValues(info.getName());
//                    return new FullSensorData(info.getName(), info.getDescription(), sensorData);
//                })
//                .collect(Collectors.toList());

//        model.addAttribute("sensorsOld", data);
        model.addAttribute("sensors", new ReactiveDataDriverContextVariable(sensorRestApi.listSensors()));

        return "index";
    }

    @Data
    @Builder
    public static class FullSensorData {

        private String name;
        private String description;
        private Map<String, Object> values;

    }
}
