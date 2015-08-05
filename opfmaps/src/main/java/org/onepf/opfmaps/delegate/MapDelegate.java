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

package org.onepf.opfmaps.delegate;

import android.support.annotation.NonNull;
import org.onepf.opfmaps.listener.OPFOnCameraChangeListener;
import org.onepf.opfmaps.listener.OPFOnIndoorStateChangeListener;
import org.onepf.opfmaps.listener.OPFOnInfoWindowClickListener;
import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.listener.OPFOnMapLoadedCallback;
import org.onepf.opfmaps.listener.OPFOnMapLongClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerDragListener;
import org.onepf.opfmaps.listener.OPFOnMyLocationButtonClickListener;
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFGroundOverlay;
import org.onepf.opfmaps.model.OPFGroundOverlayOptions;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFPolylineOptions;
import org.onepf.opfmaps.model.OPFTileOverlay;
import org.onepf.opfmaps.model.OPFTileOverlayOptions;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public interface MapDelegate {

    @NonNull
    OPFCircle addCircle(@NonNull final OPFCircleOptions options);

    @NonNull
    OPFGroundOverlay addGroundOverlay(@NonNull final OPFGroundOverlayOptions options);

    @NonNull
    OPFMarker addMarker(@NonNull final OPFMarkerOptions options);

    @NonNull
    OPFPolygon addPolygon(@NonNull final OPFPolygonOptions options);

    @NonNull
    OPFPolyline addPolyline(@NonNull final OPFPolylineOptions options);

    @NonNull
    OPFTileOverlay addTileOverlay(@NonNull final OPFTileOverlayOptions options);

    //todo void animateCamera(CameraUpdate update, int durationMs, GoogleMap.CancelableCallback callback)
    //todo void animateCamera(CameraUpdate update, GoogleMap.CancelableCallback callback)
    //todo void animateCamera(CameraUpdate update)

    void clear();

    //todo CameraPosition getCameraPosition()

    //todo IndoorBuilding getFocusedBuilding()

    OPFMapType getMapType();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    //todo  Projection getProjection()

    //todo UiSettings getUiSettings()

    boolean isBuildingsEnabled();

    boolean isIndoorEnabled();

    boolean isMyLocationEnabled();

    boolean isTrafficEnabled();

    //todo  void moveCamera(CameraUpdate update)

    void setBuildingsEnabled(final boolean enabled);

    void setContentDescription(@NonNull final String description);

    boolean setIndoorEnabled(final boolean enabled);

    void setInfoWindowAdapter(@NonNull OPFInfoWindowAdapter adapter);

    //todo void setLocationSource(LocationSource source)

    void setMapType(@NonNull final OPFMapType type);

    void setMyLocationEnabled(final boolean enabled);

    void setOnCameraChangeListener(@NonNull final OPFOnCameraChangeListener listener);

    void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener);

    void setOnInfoWindowClickListener(@NonNull final OPFOnInfoWindowClickListener listener);

    void setOnMapClickListener(@NonNull final OPFOnMapClickListener listener);

    void setOnMapLoadedCallback(@NonNull final OPFOnMapLoadedCallback callback);

    void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener listener);

    void setOnMarkerClickListener(@NonNull final OPFOnMarkerClickListener listener);

    void setOnMarkerDragListener(@NonNull final OPFOnMarkerDragListener listener);

    void setOnMyLocationButtonClickListener(@NonNull final OPFOnMyLocationButtonClickListener listener);

    void setPadding(final int left, final int top, final int right, final int bottom);

    void setTrafficEnabled(final boolean enabled);

    //todo void snapshot(GoogleMap.SnapshotReadyCallback callback, Bitmap bitmap)

    //todo void snapshot(GoogleMap.SnapshotReadyCallback callback)

    void stopAnimation();
}
