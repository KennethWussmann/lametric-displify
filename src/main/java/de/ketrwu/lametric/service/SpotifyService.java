package de.ketrwu.lametric.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import de.ketrwu.lametric.JacksonUtils;
import de.ketrwu.lametric.Logger;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.entity.spotify.SpotifyCurrentlyPlaying;

import java.io.IOException;

public class SpotifyService {

    private static final Logger LOG = new Logger(SpotifyService.class);
    private static final String API_URL_BASE = "https://api.spotify.com/v1";
    private static final String SPOTIFY_CURRENTLY_PLAYING = "/me/player/currently-playing";

    static {
        Unirest.setTimeouts(2000, 2000);
    }

    public static SpotifyCurrentlyPlaying getCurrentlyPlaying(LaMetricRequest request) throws UnirestException, IOException {
        Unirest.setDefaultHeader("Authorization", request.getAuthorization());
        HttpResponse<String> response = Unirest.get(API_URL_BASE + SPOTIFY_CURRENTLY_PLAYING).asString();
        boolean emptyBody = response.getBody() == null || response.getBody().isEmpty();

        if (response.getHeaders().get("Retry-After") != null) {
            String rt = response.getHeaders().get("Retry-After").get(0);
            LOG.error(request, "Spotify rate-limit", "Retry after " + rt + " seconds");
            return new SpotifyCurrentlyPlaying();
        }

        if (emptyBody) {
            LOG.info(request, "Spotify no response", response.getStatus());
            return new SpotifyCurrentlyPlaying();
        }

        SpotifyCurrentlyPlaying currentlyPlaying = JacksonUtils.getObjectMapper().readValue(response.getBody(), SpotifyCurrentlyPlaying.class);

        if (response.getStatus() >= 200 && response.getStatus() < 300) {
            LOG.info(request, "Spotify ok", currentlyPlaying);
        } else {
            LOG.warn(request, "Spotify not ok", response);
        }
        return currentlyPlaying;
    }

}
