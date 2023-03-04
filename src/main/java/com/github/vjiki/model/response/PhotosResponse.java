package com.github.vjiki.model.response;


//{
//    "photos": [
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
//    ]
//}

import java.util.List;

//@Data
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
public class PhotosResponse {
    List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "NasaResponse{" +
                "photos=" + photos +
                '}';
    }
}
