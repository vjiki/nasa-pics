package com.github.vjiki.model.request;

import java.util.List;

//   "roverIds": [100],
//  "cameraIds": [100],
//  "endpoints": ["https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"],
//  "imagesPerDay": 3,
//  "days": 10

public class PhotosRequest {
    List<Integer> roverIds;
    List<Integer> cameraIds;
    List<String> endpoints;
    int imagesPerDay;
    int days;

    public List<Integer> getRoverIds() {
        return roverIds;
    }

    public void setRoverIds(List<Integer> roverIds) {
        this.roverIds = roverIds;
    }

    public List<Integer> getCameraIds() {
        return cameraIds;
    }

    public void setCameraIds(List<Integer> cameraIds) {
        this.cameraIds = cameraIds;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    public int getImagesPerDay() {
        return imagesPerDay;
    }

    public void setImagesPerDay(int imagesPerDay) {
        this.imagesPerDay = imagesPerDay;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "PhotosRequest{" +
                "roverIds=" + roverIds +
                ", cameraIds=" + cameraIds +
                ", endpoints=" + endpoints +
                ", imagesPerDay=" + imagesPerDay +
                ", days=" + days +
                '}';
    }
}
