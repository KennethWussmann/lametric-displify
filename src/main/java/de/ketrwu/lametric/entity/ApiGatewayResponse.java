package de.ketrwu.lametric.entity;

import com.google.common.collect.ImmutableMap;
import lombok.Data;

import java.util.Map;

@Data
public class ApiGatewayResponse {

    private int statusCode;
    private String body;
    private Map<String, String> headers = ImmutableMap.of("Access-Control-Allow-Origin", "*");
    private boolean isBase64Encoded = false;

    public ApiGatewayResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}
