package com.github.vjiki.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class NasaHttpClient {
    public HttpResponse<String> fetchPhotos(String targetUrl) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(targetUrl))
                .GET()
                .timeout(Duration.ofSeconds(10))
                //     .header(URLConstants.API_KEY_NAME, URLConstants.API_KEY_VALUE)
                .header("Content-Type",
                        "application/json")
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        //   client.sendAsync(request, BodyHandlers.ofString())
        //    .thenApply(HttpResponse::body)
        //    .thenAccept(System.out::println)
        //    .join();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
