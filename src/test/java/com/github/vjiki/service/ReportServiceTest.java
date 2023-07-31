package com.github.vjiki.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vjiki.cache.CacheEhcacheHelper;
import com.github.vjiki.client.NasaHttpClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceTest.class);
    public final static String STORAGE_PATH = "/Users/nikolai/workspace/nasa-pics/src/test/cache/";
    public final static String PHOTOS_API_CONFIG = "src/test/resources/photosApi.json";
    private static ReportService reportService;
    private static CacheEhcacheHelper cacheEhcacheHelper;
    private static final NasaHttpClient nasaHttpClient = new NasaHttpClient();
    private static final PhotoProcessor photoProcessor = new PhotoProcessor();
    private static final DateGenerator dateGenerator = new DateGenerator();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void setup() {
        cacheEhcacheHelper = new CacheEhcacheHelper(STORAGE_PATH);
        cacheEhcacheHelper.getPhotoResponseCacheFromCacheManager().clear();
        reportService =
                new ReportService(
                        nasaHttpClient,
                        photoProcessor,
                        dateGenerator,
                        objectMapper,
                        cacheEhcacheHelper.getPhotoResponseCacheFromCacheManager(),
                        PHOTOS_API_CONFIG);
    }

    @Test
    public void whenReportServiceCalled_thenCacheHasValue() throws IOException, URISyntaxException, InterruptedException {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = d.format(LocalDate.now());
        assertFalse(cacheEhcacheHelper.getPhotoResponseCacheFromCacheManager().containsKey(todayDate));
        reportService.preparePhotosReport();
        assertTrue(cacheEhcacheHelper.getPhotoResponseCacheFromCacheManager().containsKey(todayDate));
    }
}