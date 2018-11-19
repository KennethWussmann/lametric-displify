package de.ketrwu.lametric.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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
