package com.github.vjiki.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vjiki.client.NasaHttpClient;
import com.github.vjiki.constants.NasaConstants;
import com.github.vjiki.model.request.PhotosRequest;
import com.github.vjiki.model.response.PhotosResponse;
import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.vjiki.constants.NasaConstants.TARGET_URL_API_KEY;
import static com.github.vjiki.constants.NasaConstants.TARGET_URL_CAMERA_NAME;

public class ReportService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final NasaHttpClient nasaHttpClient;
    private final PhotoProcessor photoProcessor;
    private final DateGenerator dateGenerator;
    private final ObjectMapper objectMapper;
    private final Cache<String, PhotosResponse> cache;

    public ReportService(NasaHttpClient nasaHttpClient, PhotoProcessor photoProcessor, DateGenerator dateGenerator, ObjectMapper objectMapper, Cache<String, PhotosResponse> cache) {
        this.nasaHttpClient = nasaHttpClient;
        this.photoProcessor = photoProcessor;
        this.dateGenerator = dateGenerator;
        this.objectMapper = objectMapper;
        this.cache = cache;
    }

    public void preparePhotosReport() throws IOException, InterruptedException, URISyntaxException {
        HashMap<String, List<String>> photosReport = new HashMap<>();
        List<String> daysList = new ArrayList<>();

        PhotosRequest photosRequest = objectMapper.readValue(
                new File("src/main/resources/photosApi.json"),
                PhotosRequest.class);

        dateGenerator.getLastDaysFromNow(daysList,photosRequest.getDays()-1);

        for (String day: daysList) {
            PhotosResponse photosResponse;
            //LOGGER.info(day);
            String targetUrlKey  = String.format(
                    "photos?earth_date=%s&camera=%s&api_key=%s",
                    day,TARGET_URL_CAMERA_NAME,TARGET_URL_API_KEY);

            photosResponse = cache.get(day);
            if (photosResponse == null) {
                LOGGER.debug("fetching value from remote server for the day" + day);
                // Reading response
                HttpResponse<String> response = nasaHttpClient.fetchPhotos(
                        NasaConstants.TARGET_URL + targetUrlKey);
                photosResponse = objectMapper
                        .readValue(response.body(), PhotosResponse.class);
                cache.put(day, photosResponse);
            } else {
                LOGGER.debug("fetched value from cache" + photosResponse);
            }

            photoProcessor.process(day, photosReport, photosRequest, photosResponse);
        }

        Map<String, List<String>> sortedPhotosReportByDate =
                photoProcessor.getSortedOrderedReportMapByDate(photosReport);
        photoProcessor.printJsonReport(objectMapper, sortedPhotosReportByDate);
    }
}
