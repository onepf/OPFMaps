package org.onepf.opfmaps.model;

/**
 * Created by akarimova on 09.06.15.
 */
public class OPFLatLng {
    private final double latitude;
    private final double longitude;

    public OPFLatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
