package com.github.vjiki.model.response;

//            "camera": {
//                "id": 26,
//                "name": "NAVCAM",
//                "rover_id": 5,
//                "full_name": "Navigation Camera"
//            },

import lombok.Data;

//@Data
public class Camera {
    private int id;
    private String name;
    private int rover_id;
    private String full_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRover_id() {
        return rover_id;
    }

    public void setRover_id(int rover_id) {
        this.rover_id = rover_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rover_id=" + rover_id +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
