package de.ketrwu.lametric.api;

import de.ketrwu.lametric.entity.lametric.LaMetricFrame;
import de.ketrwu.lametric.entity.lametric.LaMetricIcon;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.entity.lametric.LaMetricResponse;
import de.ketrwu.lametric.entity.spotify.SpotifyCurrentlyPlaying;
import de.ketrwu.lametric.service.SpotifyService;
import org.apache.http.HttpStatus;

import java.util.Map;

public class DisplifyHandler extends LaMetricAppHandler {

    @Override
    public LaMetricResponse handleRequest(LaMetricRequest request, Map<String, String> queryParams) throws Exception {
        SpotifyCurrentlyPlaying currentlyPlaying = SpotifyService.getCurrentlyPlaying(request);

        if (currentlyPlaying != null && currentlyPlaying.getError() != null) {
            return new LaMetricResponse(currentlyPlaying.getError().getStatus())
                    .frame(
                            new LaMetricFrame.Builder()
                                    .text("Failed to request Spotify track!")
                                    .icon(LaMetricIcon.ATTENTION)
                                    .build()
                    );
        }

        String text = "Spotify";
        if (currentlyPlaying != null && currentlyPlaying.isPlaying() && currentlyPlaying.getTrack() != null) {
            text = currentlyPlaying.getTrack().getArtistSongString();
        }

        return new LaMetricResponse(HttpStatus.SC_OK, currentlyPlaying != null && currentlyPlaying.isPlaying()).frame(
                new LaMetricFrame.Builder()
                        .icon(LaMetricIcon.DISPLIFY)
                        .text(text)
                        .build()
        );
    }

    @Override
    boolean requireAuthorization() {
        return true;
    }
}
