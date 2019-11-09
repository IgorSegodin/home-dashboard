package org.isegodin.home.dashboard.app.data;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author isegodin
 */
@Data
@Builder
public class FullSensorData {

    private String name;
    private String description;
    private Map<String, Object> values;

}
