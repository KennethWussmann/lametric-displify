package de.ketrwu.lametric.api;

import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.entity.lametric.LaMetricResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DisplifyHandlerTest {

    @Test
    @Disabled
    void handleRequest() throws Exception {
        DisplifyHandler handler = new DisplifyHandler();
        LaMetricRequest request = new LaMetricRequest(
                "device-0001",
                "ce3b80ec-73ab-4d40-9578-d6afa391ff1b",
                "Bearer e49e9b0b-3ce7-4b16-8536-6e165d94c191"
        );
        LaMetricResponse response = handler.handleRequest(request, Collections.emptyMap());
        assertNotNull(response);
    }
}