package com.github.vjiki;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vjiki.client.NasaHttpClient;
import com.github.vjiki.cache.CacheEhcacheHelper;
import com.github.vjiki.model.response.PhotosResponse;
import com.github.vjiki.service.DateGenerator;
import com.github.vjiki.service.PhotoProcessor;
import com.github.vjiki.service.ReportService;
import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public final static String STORAGE_PATH = "/Users/nikolai/workspace/nasa-pics/cache/";
    public final static String PHOTOS_API_CONFIG = "src/main/resources/photosApi.json";
    private static final Logger LOGGER =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        NasaHttpClient nasaHttpClient = new NasaHttpClient();
        PhotoProcessor photoProcessor = new PhotoProcessor();
        DateGenerator dateGenerator = new DateGenerator();
        ObjectMapper objectMapper = new ObjectMapper();
        CacheEhcacheHelper cacheEhcacheHelper = new CacheEhcacheHelper(STORAGE_PATH);
        Cache<String, PhotosResponse> myCache = cacheEhcacheHelper.getPhotoResponseCacheFromCacheManager();

        ReportService reportService = new ReportService(
                nasaHttpClient, photoProcessor, dateGenerator, objectMapper, myCache, PHOTOS_API_CONFIG);

        Instant start = Instant.now();

        try {
            reportService.preparePhotosReport();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
        }

        Instant finish = Instant.now();
        cacheEhcacheHelper.getCacheManager().close();

        LOGGER.info("time passed to prepare response: " + Duration.between(start, finish).toMillis() + " ms");
    }
}