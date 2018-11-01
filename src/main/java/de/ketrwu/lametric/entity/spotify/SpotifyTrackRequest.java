package de.ketrwu.lametric.entity.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SpotifyTrackRequest extends SpotifyRequest {

    @JsonProperty("item")
    private SpotifyTrack track;

}
