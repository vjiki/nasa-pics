package com.github.vjiki.service;

import com.github.vjiki.model.RoverReport;
import com.github.vjiki.model.request.PhotosRequest;
import com.github.vjiki.model.response.Photo;
import com.github.vjiki.model.response.PhotosResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PhotoProcessor {

    public void process(String day, HashMap<String, List<String>> output, PhotosRequest photosRequest, PhotosResponse photosResponse) {
        if (photosResponse.getPhotos().isEmpty()) {
            output.put(day, Collections.emptyList());
        } else {
            List<String> images =
                    photosResponse
                            .getPhotos()
                            .stream()
                            .map(Photo::getImg_src)
                            .limit(photosRequest.getImagesPerDay())
                            .toList();

            photosResponse.getPhotos().stream()
                    .findFirst().ifPresent(value -> output.put(value.getEarth_date(), images));
        }


//        for (PhotosResponse: photosResponseList) {
//
//            //JsonGenerator
////        JSONObject obj = Json.createObjectBuilder()
////                .add("data", Json.createObjectBuilder()
////                        .add("Name", "Alex")
////                        .add("id", "12345")
////                        .add("attributes", Json.createObjectBuilder()
////                                .add("Class", "12A")))
////                .build();
////
////            if (roverReport == null || roverReport.getDate() == null) {
////                return;
////            }
//
//
//
//            //RoverReport roverReport = new RoverReport();
//
//            // избавиться от репорта можно
//
//
//
//            //roverReport.setImages(images);
//
//            //processReport(output, roverReport);
//        }
//
    }

    public RoverReport processResponse(PhotosResponse photosResponse) {

        RoverReport roverReport = new RoverReport();

        photosResponse.getPhotos().stream()
                .findFirst().ifPresent(value -> roverReport.setDate(value.getEarth_date()));
        List<String> images = photosResponse.getPhotos().stream().map(Photo::getImg_src).limit(3).toList();
        roverReport.setImages(images);

        return roverReport;
    }

    public void processReport(LinkedHashMap<String, List<String>> output, PhotosRequest photosRequest, PhotosResponse photosResponse) {

        //JsonGenerator
//        JSONObject obj = Json.createObjectBuilder()
//                .add("data", Json.createObjectBuilder()
//                        .add("Name", "Alex")
//                        .add("id", "12345")
//                        .add("attributes", Json.createObjectBuilder()
//                                .add("Class", "12A")))
//                .build();

//        if (roverReport == null || roverReport.getDate() == null) {
//            return;
//        }
//
//        output.put(roverReport.getDate(), roverReport.getImages());

        List<String> images =
                photosResponse
                        .getPhotos()
                        .stream()
                        .map(Photo::getImg_src)
                        .limit(photosRequest.getImagesPerDay())
                        .toList();
        photosResponse.getPhotos().stream()
                .findFirst().ifPresent(value -> output.put(value.getEarth_date(), images));

    }

    public void processEmptyResponse(LinkedHashMap<String, List<String>> output, String day) {
        output.put(day, Collections.emptyList());
    }
}
