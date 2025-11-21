package com.alura.conversor.service;

import com.alura.conversor.exception.HttpErrorException;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientService {
    private final java.net.http.HttpClient httpClient;

    public HttpClientService() {
        this.httpClient = java.net.http.HttpClient.newBuilder()
                .build();
    }

    public String get(String url) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new HttpErrorException("Error HTTP: " + response.statusCode());
            }

            return response.body();
        } catch (Exception e) {
            throw new HttpErrorException("Error al realizar la solicitud: " + e.getMessage());
        }
    }
}
