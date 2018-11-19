package de.ketrwu.lametric.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpotifyTrackRequest extends SpotifyRequest {

    @JsonProperty("item")
    private SpotifyTrack track;

}
