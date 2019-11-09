package org.isegodin.home.dashboard.app.sensor;

import java.util.Map;

/**
 * @author isegodin
 */
public interface SensorValuesFormatter {

    String getSupportedSensor();

    Map<String, Object> format(Map<String, Object> values);
}
