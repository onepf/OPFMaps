package org.onepf.opfmaps.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by akarimova on 15.06.15.
 */
public abstract class OPFMutiPointsShape extends OPFShape {
    private List<OPFLatLng> points;
    private boolean geodesic;

    public OPFMutiPointsShape() {
        points = new ArrayList<>();
    }

    public void add(OPFLatLng point) {
        points.add(point);
    }

    public void add(OPFLatLng... points) {
        Collections.addAll(this.points, points);
    }

    public void addAll(Iterable<OPFLatLng> points) {
        for (Object point : points) {
            this.points.add((OPFLatLng) point);
        }
    }

    public List<OPFLatLng> getPoints() {
        return points;
    }


    public boolean isGeodesic() {
        return geodesic;
    }

    public void geodesic(boolean geodesic) {
        this.geodesic = geodesic;
    }
}
