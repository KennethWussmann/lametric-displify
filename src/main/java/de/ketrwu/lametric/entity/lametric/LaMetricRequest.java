package de.ketrwu.lametric.entity.lametric;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class LaMetricRequest {

    private String deviceId;
    private String appInstance;
    @ToString.Exclude
    private String authorization;

}
