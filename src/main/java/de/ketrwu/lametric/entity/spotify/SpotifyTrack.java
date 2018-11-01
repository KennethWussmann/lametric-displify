package de.ketrwu.lametric.entity.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class SpotifyTrack {

    private String name;
    private List<SpotifyArtist> artists = new ArrayList<>();
    @JsonProperty("duration_ms")
    private long duration;
    private SpotifyAlbum album;

    public String getArtistSongString() {
        return artists.get(0).getName() + " - " + name;
    }

}
