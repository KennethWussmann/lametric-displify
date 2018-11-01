package de.ketrwu.lametric.entity.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SpotifyCurrentlyPlaying extends SpotifyTrackRequest {

    @JsonProperty("is_playing")
    private boolean playing;
    private long timestamp;
    @JsonProperty("progress_ms")
    private long progress;

}
