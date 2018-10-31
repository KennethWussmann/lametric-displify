package de.ketrwu.lametric.api;

import de.ketrwu.lametric.service.SpotifyClient;
import de.ketrwu.lametric.entity.lametric.LaMetricFrame;
import de.ketrwu.lametric.entity.lametric.LaMetricIcon;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.entity.lametric.LaMetricResponse;
import de.ketrwu.lametric.entity.spotify.SpotifyCurrentlyPlaying;
import org.apache.http.HttpStatus;

import java.util.Map;

public class DisplifyHandler extends LaMetricAppHandler {

    @Override
    public LaMetricResponse handleRequest(LaMetricRequest request, Map<String, String> queryParams) throws Exception {
        SpotifyCurrentlyPlaying currentlyPlaying = SpotifyClient.getCurrentlyPlaying(request);

        if (currentlyPlaying.getError() != null) {
            return new LaMetricResponse(currentlyPlaying.getError().getStatus())
                    .frame(
                            new LaMetricFrame.Builder()
                                    .text("Failed to request Spotify track!")
                                    .icon(LaMetricIcon.ATTENTION)
                                    .build()
                    );
        }

        return new LaMetricResponse(HttpStatus.SC_OK).frame(
                new LaMetricFrame.Builder()
                        .icon(LaMetricIcon.DISPLIFY)
                        .text(currentlyPlaying.isPlaying() ? currentlyPlaying.getTrack().getArtistSongString() : "Spotify")
                        .build()
        );
    }

    @Override
    boolean requireAuthorization() {
        return true;
    }
}
