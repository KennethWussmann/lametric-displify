package de.ketrwu.lametric.entity.lametric;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class LaMetricResponse {

    @JsonIgnore
    private int statusCode;
    private List<LaMetricFrame> frames = new ArrayList<>();
    private boolean listening;

    public LaMetricResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public LaMetricResponse(int statusCode, boolean listening) {
        this.statusCode = statusCode;
        this.listening = listening;
    }

    public LaMetricResponse frame(LaMetricFrame frame) {
        frames.add(frame);
        return this;
    }
}
