package com.github.vjiki.model;

import java.util.List;

//{ "2016-4-6": [], "2016-4-5": [], "2016-4-4": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513062102EDR_S0540000NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513062029EDR_S0540000NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513061956EDR_S0540000NCAM00546M_.JPG"], "2016-4-3": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01301/opgs/edr/ncam/NLB_512995594EDR_F0540000NCAM07753M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01301/opgs/edr/ncam/NLB_512995563EDR_F0540000NCAM07753M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01301/opgs/edr/ncam/NLB_512995472EDR_F0540000NCAM07753M_.JPG"], "2016-4-2": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01300/opgs/edr/ncam/NLB_512914365EDR_F0532980NCAM00320M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01300/opgs/edr/ncam/NLB_512913929EDR_F0532980NCAM00207M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01300/opgs/edr/ncam/NLB_512912740EDR_F0532980NCAM00207M_.JPG"], "2016-4-1": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01299/opgs/edr/ncam/NLB_512813334EDR_S0532980NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01299/opgs/edr/ncam/NLB_512813261EDR_S0532980NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01299/opgs/edr/ncam/NLB_512813188EDR_S0532980NCAM00546M_.JPG"], "2016-3-31": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01298/opgs/edr/ncam/NLB_512725460EDR_F0532944NCAM00385M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01298/opgs/edr/ncam/NLB_512725436EDR_F0532944NCAM00385M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01298/opgs/edr/ncam/NRB_512730078EDR_F0532980NCAM00353M_.JPG"], "2016-3-30": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01297/opgs/edr/ncam/NLB_512637743EDR_S0532644NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01297/opgs/edr/ncam/NLB_512637670EDR_S0532644NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01297/opgs/edr/ncam/NLB_512637597EDR_S0532644NCAM00546M_.JPG"], "2016-3-29": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01296/opgs/edr/ncam/NLB_512555701EDR_F0532644NCAM00354M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01296/opgs/edr/ncam/NLB_512555670EDR_F0532644NCAM00354M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01296/opgs/edr/ncam/NLB_512555645EDR_F0532644NCAM00354M_.JPG"], "2016-3-28": [] }

public class RoverReport {
    private String date;
    private List<String> images;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "RoverReport{" +
                "date='" + date + '\'' +
                ", images=" + images +
                '}';
    }
}
