package de.ketrwu.lametric.api;

import de.ketrwu.lametric.entity.SpotifyArtist;
import de.ketrwu.lametric.entity.SpotifyCurrentlyPlaying;
import de.ketrwu.lametric.entity.SpotifyTrack;
import de.ketrwu.lametric.lambda.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.lambda.entity.lametric.LaMetricResponse;
import de.ketrwu.lametric.service.SpotifyService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(SpotifyService.class)
class DisplifyHandlerTest {

    private static LaMetricRequest LAMETRIC_REQUEST = new LaMetricRequest(
            "9caafabe",
            "31542cf1-a151-438a-87d9-6d740ec7faab",
            "64db7bd1-bb23-41de-9c55-6e94b8eb6ef9",
            "Bearer b4d12ed2-975d-4af0-9240-b8a9f1d84604"
    );

    private static SpotifyCurrentlyPlaying SPOTIFY_CURRENTLY_PLAYING = new SpotifyCurrentlyPlaying();

    public DisplifyHandlerTest() {
    }

    @BeforeClass
    public static void setup() {
        SpotifyTrack track = new SpotifyTrack();
        SpotifyArtist artist = new SpotifyArtist();

        artist.setId("ec4c48fa-d359-4489-8a9e-bbd66c392dc4");
        artist.setName("Someone");

        track.getArtists().add(artist);
        track.setName("Something");

        SPOTIFY_CURRENTLY_PLAYING.setPlaying(true);
        SPOTIFY_CURRENTLY_PLAYING.setTrack(track);
    }

    @Test
    public void handleRequest_Playing() throws Exception {
        mockStatic(SpotifyService.class);
        when(SpotifyService.getCurrentlyPlaying(LAMETRIC_REQUEST)).thenReturn(SPOTIFY_CURRENTLY_PLAYING);

        DisplifyHandler handler = new DisplifyHandler();
        LaMetricResponse response = handler.handleRequest(LAMETRIC_REQUEST, Collections.emptyMap());
        assertThat(response.getFrames(), iterableWithSize(1));
        assertThat(response.getFrames().get(0).getText(), equalTo("Someone - Something"));
        assertThat(response.isListening(), is(true));
    }

    @Test
    public void handleRequest_NotPlaying() throws Exception {
        mockStatic(SpotifyService.class);
        when(SpotifyService.getCurrentlyPlaying(LAMETRIC_REQUEST)).thenReturn(null);

        DisplifyHandler handler = new DisplifyHandler();
        LaMetricResponse response = handler.handleRequest(LAMETRIC_REQUEST, Collections.emptyMap());
        assertThat(response.getFrames(), iterableWithSize(1));
        assertThat(response.getFrames().get(0).getText(), equalTo("Spotify"));
        assertThat(response.isListening(), is(false));
    }
}