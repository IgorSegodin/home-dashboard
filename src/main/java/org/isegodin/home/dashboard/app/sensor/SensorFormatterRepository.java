package org.isegodin.home.dashboard.app.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author isegodin
 */
@Component
public class SensorFormatterRepository {

    private Map<String, SensorValuesFormatter> formatterMap;

    private final SensorValuesFormatter DEFAULT_EMPTY_FORMATTER = new SensorValuesFormatter() {
        @Override
        public String getSupportedSensor() {
            return null;
        }

        @Override
        public Map<String, Object> format(Map<String, Object> values) {
            return values;
        }
    };

    @Autowired(required = false)
    protected void setFormatters(List<SensorValuesFormatter> formatters) {
        this.formatterMap = Optional.ofNullable(formatters).orElse(Collections.emptyList())
                .stream().collect(Collectors.toMap(SensorValuesFormatter::getSupportedSensor, f -> f));
    }

    public SensorValuesFormatter getFormatter(String sensorName) {
        return Optional.ofNullable(formatterMap.get(sensorName))
                .orElse(DEFAULT_EMPTY_FORMATTER);
    }
}
