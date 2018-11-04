package de.ketrwu.lametric.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@SpotifyEndpoint("/me/player/currently-playing")
public class SpotifyCurrentlyPlaying extends SpotifyTrackRequest {

    @JsonProperty("is_playing")
    private boolean playing;
    private long timestamp;
    @JsonProperty("progress_ms")
    private long progress;

}
