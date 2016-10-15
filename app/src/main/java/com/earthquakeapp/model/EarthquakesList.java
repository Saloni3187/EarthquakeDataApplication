package com.earthquakeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EarthquakesList{

    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("eqid")
    @Expose
    private String eqid;
    @SerializedName("timedate")
    @Expose
    private String timedate;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("magnitude")
    @Expose
    private String magnitude;
    @SerializedName("depth")
    @Expose
    private String depth;
    @SerializedName("region")
    @Expose
    private String region;

    /**
     *
     * @return
     * The src
     */
    public String getSrc() {
        return src;
    }

    /**
     *
     * @param src
     * The src
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     *
     * @return
     * The eqid
     */
    public String getEqid() {
        return eqid;
    }

    /**
     *
     * @param eqid
     * The eqid
     */
    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    /**
     *
     * @return
     * The timedate
     */
    public String getTimedate() {
        return timedate;
    }

    /**
     *
     * @param timedate
     * The timedate
     */
    public void setTimedate(String timedate) {
        this.timedate = timedate;
    }

    /**
     *
     * @return
     * The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public String getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The magnitude
     */
    public String getMagnitude() {
        return magnitude;
    }

    /**
     *
     * @param magnitude
     * The magnitude
     */
    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    /**
     *
     * @return
     * The depth
     */
    public String getDepth() {
        return depth;
    }

    /**
     *
     * @param depth
     * The depth
     */
    public void setDepth(String depth) {
        this.depth = depth;
    }

    /**
     *
     * @return
     * The region
     */
    public String getRegion() {
        return region;
    }

    /**
     *
     * @param region
     * The region
     */
    public void setRegion(String region) {
        this.region = region;
    }

}
