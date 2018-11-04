package de.ketrwu.lametric.api;

import de.ketrwu.lametric.entity.SpotifyCurrentlyPlaying;
import de.ketrwu.lametric.lambda.entity.lametric.LaMetricFrame;
import de.ketrwu.lametric.lambda.entity.lametric.LaMetricIcon;
import de.ketrwu.lametric.lambda.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.lambda.entity.lametric.LaMetricResponse;
import de.ketrwu.lametric.lambda.handler.LaMetricAppHandler;
import de.ketrwu.lametric.service.SpotifyService;
import org.apache.http.HttpStatus;

import java.util.Map;

public class DisplifyHandler extends LaMetricAppHandler {

    private static final String DISPLIFY_ICON = "i21651";

    @Override
    public LaMetricResponse handleRequest(LaMetricRequest request, Map<String, String> queryParams) throws Exception {
        SpotifyCurrentlyPlaying currentlyPlaying = SpotifyService.getCurrentlyPlaying(request);

        if (currentlyPlaying != null && currentlyPlaying.getError() != null) {
            return new LaMetricResponse(currentlyPlaying.getError().getStatus())
                    .frame(
                            new LaMetricFrame.Builder()
                                    .text("Failed to request Spotify track!")
                                    .icon(LaMetricIcon.ATTENTION.getIcon())
                                    .build()
                    );
        }

        String text = "Spotify";
        if (currentlyPlaying != null && currentlyPlaying.isPlaying() && currentlyPlaying.getTrack() != null) {
            text = currentlyPlaying.getTrack().getArtistSongString();
        }

        return new LaMetricResponse(HttpStatus.SC_OK, currentlyPlaying != null && currentlyPlaying.isPlaying()).frame(
                new LaMetricFrame.Builder()
                        .icon(DISPLIFY_ICON)
                        .text(text)
                        .build()
        );
    }

    @Override
    public boolean requireAuthorization() {
        return true;
    }
}
