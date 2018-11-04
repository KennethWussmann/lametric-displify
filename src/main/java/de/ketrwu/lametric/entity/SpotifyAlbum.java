package de.ketrwu.lametric.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpotifyAlbum {

    private String name;
    @JsonProperty("album_type")
    private String type;

}
