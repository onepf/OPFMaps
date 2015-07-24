/*
 * Copyright 2012-2015 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    void setMapType(OPFMap.MAP_TYPE mapType);

    OPFMap.MAP_TYPE getMapType();

    Location getMyLocation();

    void addGroundOverlay(OPFGroundOverlay opfGroundOverlay);

    void addTileOverlay(OPFTileOverlay opfTileOverlay);

    void addPadding(int left, int top, int right, int bottom);

    void setIndoorEnabled(boolean enabled);

    void setBuildingsEnabled(boolean enabled);

    float getMaxZoomLevel();

    float getMinZoomLevel();

    void load(OPFOnMapLoadListener mapLoadedListener);

    void setOnMarkerDragListener(OPFOnMarkerDragListener onMarkerDragListener);

    void setOnMarkerClickListener(OPFOnMarkerClickListener onMarkerClickListener);

    void setOnMapClickListener(OPFOnMapClickListener onMapClickListener);
}
