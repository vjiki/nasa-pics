package com.github.vjiki.model.response;

//        {
//            "id": 551329,
//            "sol": 1300,
//            "camera": {
//                "id": 26,
//                "name": "NAVCAM",
//                "rover_id": 5,
//                "full_name": "Navigation Camera"
//            },
//            "img_src": "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01300/opgs/edr/ncam/NLB_512914365EDR_F0532980NCAM00320M_.JPG",
//            "earth_date": "2016-04-02",
//            "rover": {
//                "id": 5,
//                "name": "Curiosity",
//                "landing_date": "2012-08-06",
//                "launch_date": "2011-11-26",
//                "status": "active"
//            }
//        }

import java.io.Serializable;

//@Data
public class Photo implements Serializable {
    private int id;
    private int sol;
    private Camera camera;
    private String img_src;
    private String earth_date;
    private Rover rover;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getEarth_date() {
        return earth_date;
    }

    public void setEarth_date(String earth_date) {
        this.earth_date = earth_date;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", sol=" + sol +
                ", camera=" + camera +
                ", img_src='" + img_src + '\'' +
                ", earth_date='" + earth_date + '\'' +
                ", rover=" + rover +
                '}';
    }
}
