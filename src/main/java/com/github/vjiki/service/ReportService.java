package com.github.vjiki.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vjiki.client.NasaHttpClient;
import com.github.vjiki.constants.NasaConstants;
import com.github.vjiki.model.request.PhotosRequest;
import com.github.vjiki.model.response.PhotosResponse;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.vjiki.constants.NasaConstants.TARGET_URL_API_KEY;
import static com.github.vjiki.constants.NasaConstants.TARGET_URL_CAMERA_NAME;

public class ReportService {

    private final NasaHttpClient nasaHttpClient;
    private final PhotoProcessor photoProcessor;
    private final DateGenerator dateGenerator;
    private final ObjectMapper objectMapper;

    public ReportService(NasaHttpClient nasaHttpClient, PhotoProcessor photoProcessor, DateGenerator dateGenerator, ObjectMapper objectMapper) {
        this.nasaHttpClient = nasaHttpClient;
        this.photoProcessor = photoProcessor;
        this.dateGenerator = dateGenerator;
        this.objectMapper = objectMapper;
    }

    public void preparePhotosReport() throws IOException, InterruptedException, URISyntaxException {
        HashMap<String, List<String>> photosReport = new HashMap<>();
        List<String> daysList = new ArrayList<>();


        PhotosRequest photosRequest = objectMapper.readValue(
                new File("src/main/resources/photosApi.json"),
                PhotosRequest.class);

        dateGenerator.getLastDaysFromNow(daysList,photosRequest.getDays()-1);

        for (String day: daysList) {
            //System.out.println(day);
            String targetUrlKey  = String.format(
                    "photos?earth_date=%s&camera=%s&api_key=%s",
                    day,TARGET_URL_CAMERA_NAME,TARGET_URL_API_KEY);

            HttpResponse<String> response = nasaHttpClient.fetchPhotos(
                    NasaConstants.TARGET_URL + targetUrlKey);

            // Reading response
            PhotosResponse photosResponse = objectMapper
                    .readValue(response.body(), PhotosResponse.class);

            photoProcessor.process(day, photosReport, photosRequest, photosResponse);
        }

        Map<String, List<String>> sortedPhotosReportByDate =
                photoProcessor.getSortedOrderedReportMapByDate(photosReport);
        photoProcessor.printJsonReport(objectMapper, sortedPhotosReportByDate);
    }
}
