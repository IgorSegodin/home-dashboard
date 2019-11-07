package org.isegodin.home.dashboard.backend.api.i2c.sensor.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author isegodin
 */
//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SensorInfo {

    private String name;
    private String description;
}
