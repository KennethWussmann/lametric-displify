package de.ketrwu.lametric.entity.spotify;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import lombok.Data;

@Data
public class SpotifyRequest {

    private SpotifyError error;
    @JsonIgnore
    private LaMetricRequest laMetricRequest;

}
