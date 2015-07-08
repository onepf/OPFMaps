package org.onepf.opfmaps;

import android.location.Location;

import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFGroundOverlay;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFTileOverlay;


/**
 * Created by akarimova on 09.06.15.
 */
public interface OPFMapDelegate {

    boolean isReady();

    void addMarker(OPFMarker opfMarker);

    void addPolyline(OPFPolyline opfPolyline);

    void addCircle(OPFCircle opfCircle);

    void addPolygon(OPFPolygon opfPolygon);

    void zoom(float zoom);

    void setInfoWindowAdapter(OPFInfoWindowAdapter adapter);

    void setMyLocationEnabled(boolean enabled);

    void setTrafficEnabled(boolean enabled);

    void clear();

    void setMapType(int mapType);

    int getMapType();

    Location getMyLocation();

    void addGroundOverlay(OPFGroundOverlay opfGroundOverlay);

    void addTileOverlay(OPFTileOverlay opfTileOverlay);

    void addPadding(int bottom, int left, int top, int right);

    void setIndoorEnabled(boolean enabled);

    void setBuildingsEnabled(boolean enabled);

    float getMaxZoomLevel();

    float getMinZoomLevel();

    void load(OPFOnMapLoadListener mapLoadedListener);

    void setOnMarkerDragListener(OPFOnMarkerDragListener onMarkerDragListener);

    void setOnMarkerClickListener(OPFOnMarkerClickListener onMarkerClickListener);

    void setOnMapClickListener(OPFOnMapClickListener onMapClickListener);
}
