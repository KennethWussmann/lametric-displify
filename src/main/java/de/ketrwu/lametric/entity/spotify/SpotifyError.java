package de.ketrwu.lametric.entity.spotify;

import lombok.Data;

@Data
public class SpotifyError {

    private int status;
    private String message;

}
