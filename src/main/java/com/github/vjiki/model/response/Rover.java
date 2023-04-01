package com.github.vjiki.model.response;

//            "rover": {
//                "id": 5,
//                "name": "Curiosity",
//                "landing_date": "2012-08-06",
//                "launch_date": "2011-11-26",
//                "status": "active"
//            }


import java.io.Serializable;

public class Rover implements Serializable {
    private int id;
    private String name;
    private String landing_date;
    private String launch_date;
    private String status;

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

    public String getLanding_date() {
        return landing_date;
    }

    public void setLanding_date(String landing_date) {
        this.landing_date = landing_date;
    }

    public String getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(String launch_date) {
        this.launch_date = launch_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Rover{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", landing_date='" + landing_date + '\'' +
                ", launch_date='" + launch_date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
