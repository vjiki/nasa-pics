package com.github.vjiki;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vjiki.client.NasaHttpClient;
import com.github.vjiki.cache.CacheEhcacheManager;
import com.github.vjiki.model.response.PhotosResponse;
import com.github.vjiki.service.DateGenerator;
import com.github.vjiki.service.PhotoProcessor;
import com.github.vjiki.service.ReportService;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        NasaHttpClient nasaHttpClient = new NasaHttpClient();
        PhotoProcessor photoProcessor = new PhotoProcessor();
        DateGenerator dateGenerator = new DateGenerator();
        ObjectMapper objectMapper = new ObjectMapper();
        CacheEhcacheManager cacheEhcacheManager = new CacheEhcacheManager();

        CacheManager cacheManager = cacheEhcacheManager.getCacheManager();
        Cache<String, PhotosResponse> myCache = cacheManager.getCache("persistentCache", String.class, PhotosResponse.class);

        ReportService reportService = new ReportService(
                nasaHttpClient, photoProcessor, dateGenerator, objectMapper, myCache);

        Instant start = Instant.now();

        try {
            reportService.preparePhotosReport();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
        }

        Instant finish = Instant.now();
        cacheManager.close();

        LOGGER.info("time passed to prepare response: " + Duration.between(start, finish).toMillis() + " ms");
    }
}