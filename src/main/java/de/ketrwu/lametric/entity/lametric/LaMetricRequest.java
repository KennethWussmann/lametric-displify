package de.ketrwu.lametric.entity.lametric;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaMetricRequest {

    private String deviceId;
    private String appInstance;
    private String authorization;

}
