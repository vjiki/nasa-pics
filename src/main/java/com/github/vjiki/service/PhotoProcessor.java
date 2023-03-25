package com.github.vjiki.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vjiki.model.request.PhotosRequest;
import com.github.vjiki.model.response.Photo;
import com.github.vjiki.model.response.PhotosResponse;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//{
// "2016-4-5": [],
// "2016-4-4": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513062102EDR_S0540000NCAM00546M_.JPG",
// "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513062029EDR_S0540000NCAM00546M_.JPG",
// "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513061956EDR_S0540000NCAM00546M_.JPG"]
// }

public class PhotoProcessor {

    public void process(String day, HashMap<String, List<String>> output,
                        PhotosRequest photosRequest, PhotosResponse photosResponse) {
        if (photosResponse.getPhotos().isEmpty()) {
            output.put(day, Collections.emptyList());
        } else {
            List<String> images = photosResponse
                            .getPhotos()
                            .stream()
                            .map(Photo::getImg_src)
                            .limit(photosRequest.getImagesPerDay())
                            .toList();

            photosResponse.getPhotos()
                    .stream()
                    .findFirst()
                    .ifPresent(value -> output.put(value.getEarth_date(), images));
        }
    }

    public Map<String, List<String>> getSortedOrderedReportMapByDate(HashMap<String, List<String>> photosReport) {
        Map<String, List<String>> sortedPhotosReportMapByDate = new LinkedHashMap<>();

        photosReport.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(entry ->
                        sortedPhotosReportMapByDate.put(entry.getKey(), entry.getValue()));

        return sortedPhotosReportMapByDate;
    }

    public void printJsonReport(ObjectMapper objectMapper, Map<String, List<String>> sortedMap) throws JsonProcessingException {
        String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sortedMap);
        System.out.println(jsonOutput);
    }

}
