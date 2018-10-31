package de.ketrwu.lametric.entity.lametric;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LaMetricResponse {

    @JsonIgnore
    private int statusCode;
    private List<LaMetricFrame> frames = new ArrayList<>();

    public LaMetricResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public LaMetricResponse frame(LaMetricFrame frame) {
        frames.add(frame);
        return this;
    }
}
