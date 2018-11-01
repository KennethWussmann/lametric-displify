package de.ketrwu.lametric.entity.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SpotifyAlbum {

    private String name;
    @JsonProperty("album_type")
    private String type;

}
