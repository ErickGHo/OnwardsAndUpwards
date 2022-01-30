package com.gemini.api.clients.http;

import com.gemini.general.JsonUtil;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * A wrapper around Java HTTP client to simplify verbose HTTP request methods.
 *
 * @author Erick Ho
 * @version 1.0
 * @since 1.0
 */
public class GeminiHttpClient {

    private HttpClient httpClient;

    private Duration connectionTimeOut;

    public GeminiHttpClient() {
        httpClient = HttpClient.newHttpClient();
    }

    public HttpResponse<String> sendSimpleGet(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpResponse<String> sendSimplePost(String url, Object dataTransferObject) {
        String jsonBody = JsonUtil.toJsonString(dataTransferObject);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
