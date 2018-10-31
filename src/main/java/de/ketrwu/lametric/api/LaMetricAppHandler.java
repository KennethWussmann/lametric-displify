package de.ketrwu.lametric.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.ketrwu.lametric.JacksonUtils;
import de.ketrwu.lametric.entity.ApiGatewayResponse;
import de.ketrwu.lametric.entity.lametric.LaMetricFrame;
import de.ketrwu.lametric.entity.lametric.LaMetricIcon;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.entity.lametric.LaMetricResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

public abstract class LaMetricAppHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(LaMetricAppHandler.class);

    abstract LaMetricResponse handleRequest(LaMetricRequest request, Map<String, String> queryParams) throws Exception;
    abstract boolean requireAuthorization();

    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LaMetricRequest request = getLaMetricRequest(input);
        LaMetricResponse response;

        if (requireAuthorization() && (request.getAuthorization() == null || request.getAuthorization().isEmpty())) {
            response = new LaMetricResponse(HttpStatus.SC_FORBIDDEN).frame(
                    new LaMetricFrame.Builder().text("No auth present!").icon(LaMetricIcon.ATTENTION).build()
            );
        } else {
            try {
                response = handleRequest(request, getQueryParameters(input));
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                return new ApiGatewayResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

        try {
            return new ApiGatewayResponse(response.getStatusCode(), JacksonUtils.getObjectMapper().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ApiGatewayResponse(HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getOriginalMessage());
        }
    }

    private LaMetricRequest getLaMetricRequest(Map<String, Object> input) {
        Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        headers.putAll((Map<? extends String, ? extends String>) input.get("headers"));

        String auth = headers.get("authorization");
        if (auth != null && !auth.toLowerCase().startsWith("bearer ")) {
            auth = "Bearer " + auth;
        }
        return new LaMetricRequest(
                headers.get("x-lametric-device-id"),
                headers.get("x-lametric-app-instance"),
                auth
        );
    }

    private Map<String, String> getQueryParameters(Map<String, Object> input) {
        return (Map<String, String>) input.get("queryStringParameters");
    }

}
