package de.ketrwu.lametric.entity.lametric;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaMetricRequest {

    private String deviceId;
    private String appInstance;
    @JsonIgnore
    private String authorization;

}
