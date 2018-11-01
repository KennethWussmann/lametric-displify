package de.ketrwu.lametric.entity.spotify;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SpotifyArtist {

    private String id;
    private String name;

}
