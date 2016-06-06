package com.epam.me.simplesolution;

public class Region {

    private int regionId;

    private String regionName;

    private String gspId;

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(final int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(final String regionName) {
        this.regionName = regionName;
    }

    public String getGspId() {
        return gspId;
    }

    public void setGspId(final String gspId) {
        this.gspId = gspId;
    }

    @Override
    public String toString() {
        return String.format("Region [regionId=%s, regionName=%s, gspId=%s]", regionId, regionName, gspId);
    }

}
