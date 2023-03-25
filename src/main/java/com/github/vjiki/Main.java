package com.github.vjiki;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vjiki.client.NasaHttpClient;
import com.github.vjiki.service.DateGenerator;
import com.github.vjiki.service.PhotoProcessor;
import com.github.vjiki.service.ReportService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        NasaHttpClient nasaHttpClient = new NasaHttpClient();
        PhotoProcessor photoProcessor = new PhotoProcessor();
        DateGenerator dateGenerator = new DateGenerator();
        ObjectMapper objectMapper = new ObjectMapper();
        ReportService reportService = new ReportService(
                nasaHttpClient, photoProcessor, dateGenerator, objectMapper);

        Instant start = Instant.now();

        try {
            reportService.preparePhotosReport();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        Instant finish = Instant.now();

        System.out.println("\n time passed to prepare response: " + Duration.between(start, finish).toMillis() + " ms");
    }
}