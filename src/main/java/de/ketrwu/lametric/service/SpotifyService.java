package de.ketrwu.lametric.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import de.ketrwu.lametric.JacksonUtils;
import de.ketrwu.lametric.Logger;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.entity.spotify.SpotifyCurrentlyPlaying;
import de.ketrwu.lametric.entity.spotify.SpotifyEndpoint;

import java.io.IOException;

public class SpotifyService {

    private static final Logger LOG = new Logger(SpotifyService.class);
    private static final String API_URL_BASE = "https://api.spotify.com/v1";

    private static <T> T sendRequest(LaMetricRequest request, Class<? extends T> clazz) throws UnirestException, IOException {
        Unirest.setDefaultHeader("Authorization", request.getAuthorization());

        if (clazz.isAnnotationPresent(SpotifyEndpoint.class)) {
            SpotifyEndpoint endpoint = clazz.getAnnotation(SpotifyEndpoint.class);
            HttpResponse<String> response =  Unirest.get(API_URL_BASE + endpoint.value()).asString();

            if (response.getHeaders().get("Retry-After") != null) {
                String rt = response.getHeaders().get("Retry-After").get(0);
                LOG.error(request, "Spotify rate-limit", "Retry after " + rt + " seconds");
                return null;
            }

            if (response.getBody() == null || response.getBody().isEmpty()) {
                return null;
            }

            T body = JacksonUtils.getObjectMapper().readValue(response.getBody(), clazz);

            if (response.getStatus() >= 200 && response.getStatus() < 300) {
                LOG.info(request, "Spotify ok", body);
            } else {
                LOG.warn(request, "Spotify not ok", body);
            }

            return body;
        }
        return null;
    }

    public static SpotifyCurrentlyPlaying getCurrentlyPlaying(LaMetricRequest request) throws UnirestException, IOException {
        return sendRequest(request, SpotifyCurrentlyPlaying.class);
    }

}
