package de.ketrwu.lametric.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import de.ketrwu.lametric.JacksonUtils;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.entity.spotify.SpotifyCurrentlyPlaying;

import java.io.IOException;

public class SpotifyClient {

    private static final String API_URL_BASE = "https://api.spotify.com/v1";
    private static final String SPOTIFY_CURRENTLY_PLAYING = "/me/player/currently-playing";

    static {
        Unirest.setTimeouts(2000, 2000);
    }

    public static SpotifyCurrentlyPlaying getCurrentlyPlaying(LaMetricRequest laMetricRequest) throws UnirestException, IOException {
        Unirest.setDefaultHeader("Authorization", laMetricRequest.getAuthorization());
        HttpResponse<String> response = Unirest.get(API_URL_BASE + SPOTIFY_CURRENTLY_PLAYING).asString();
        return JacksonUtils.getObjectMapper().readValue(response.getBody(), SpotifyCurrentlyPlaying.class);
    }

}
