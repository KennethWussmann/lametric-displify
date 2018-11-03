package de.ketrwu.lametric.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.ketrwu.lametric.JacksonUtils;
import de.ketrwu.lametric.Logger;
import de.ketrwu.lametric.entity.ApiGatewayResponse;
import de.ketrwu.lametric.entity.lametric.LaMetricFrame;
import de.ketrwu.lametric.entity.lametric.LaMetricIcon;
import de.ketrwu.lametric.entity.lametric.LaMetricRequest;
import de.ketrwu.lametric.entity.lametric.LaMetricResponse;
import org.apache.http.HttpStatus;

import java.util.Map;
import java.util.TreeMap;

public abstract class LaMetricAppHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = new Logger(LaMetricAppHandler.class);

    abstract LaMetricResponse handleRequest(LaMetricRequest request, Map<String, String> queryParams) throws Exception;

    abstract boolean requireAuthorization();

    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LaMetricRequest request = getLaMetricRequest(input, context);
        LaMetricResponse response;

        LOG.info(request, "Request", request);
        

        if (requireAuthorization() && (request.getAuthorization() == null || request.getAuthorization().isEmpty())) {
            response = new LaMetricResponse(HttpStatus.SC_FORBIDDEN).frame(
                    new LaMetricFrame.Builder().text("No auth present!").icon(LaMetricIcon.ATTENTION).build()
            );
            LOG.warn(request, "Unauthenticated", request.getDeviceId());
        } else {
            try {
                response = handleRequest(request, getQueryParameters(input));
            } catch (Exception e) {
                LOG.error(request, e.getMessage(), e);
                return new ApiGatewayResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

        try {
            LOG.info(request, "Response", response);
            return new ApiGatewayResponse(response.getStatusCode(), JacksonUtils.getObjectMapper().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            LOG.error(request, e.getMessage(), e);
            return new ApiGatewayResponse(HttpStatus.SC_UNPROCESSABLE_ENTITY, e.getOriginalMessage());
        }
    }

    private LaMetricRequest getLaMetricRequest(Map<String, Object> input, Context context) {
        Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        headers.putAll((Map<? extends String, ? extends String>) input.get("headers"));

        String auth = headers.get("authorization");
        if (auth != null && !auth.toLowerCase().startsWith("bearer ")) {
            auth = "Bearer " + auth;
        }
        return new LaMetricRequest(
                headers.get("x-lametric-device-id"),
                headers.get("x-lametric-app-instance"),
                context.getAwsRequestId(),
                auth
        );
    }

    private Map<String, String> getQueryParameters(Map<String, Object> input) {
        return (Map<String, String>) input.get("queryStringParameters");
    }

}
