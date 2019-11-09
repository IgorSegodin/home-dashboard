package org.isegodin.home.dashboard.app.sensor.bmp180;

import org.isegodin.home.dashboard.app.sensor.SensorValuesFormatter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

/**
 * @author isegodin
 */
@Component
public class BMP180SensorValuesFormatter implements SensorValuesFormatter {

    @Override
    public String getSupportedSensor() {
        return "BMP180";
    }

    @Override
    public Map<String, Object> format(Map<String, Object> values) {
        String temperature = Optional.ofNullable(values.get("temperature"))
                .map(v -> (Double) v)
                .map(t -> new BigDecimal(t).setScale(2, RoundingMode.HALF_UP).toString() + " C")
                .orElse("Unknown");

        return Map.of("Temperature", temperature);
    }
}