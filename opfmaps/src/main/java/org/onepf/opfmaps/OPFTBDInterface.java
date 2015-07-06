package org.onepf.opfmaps;


import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolyline;

/**
 * Created by akarimova on 16.06.15.
 */
public interface OPFTBDInterface<PL, PG, C, M, LL> {
    PL polyline(OPFPolyline opfPolyline);

    OPFPolyline opfPolyline(PL polyline);

    PG polygon(OPFPolygon opfPolygon);

    OPFPolygon opfPolygon(PG polygon);

    C circle(OPFCircle opfCircle);

    OPFCircle opfCircle(C circle);

    M marker(OPFMarker opfMarker);

    LL latLng(OPFLatLng opfLatLng);

    OPFLatLng opfLatLng(LL latLng);
}
