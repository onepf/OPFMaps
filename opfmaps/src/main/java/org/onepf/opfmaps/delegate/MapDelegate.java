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

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.listener.OPFCancelableCallback;
import org.onepf.opfmaps.listener.OPFOnCameraChangeListener;
import org.onepf.opfmaps.listener.OPFOnIndoorStateChangeListener;
import org.onepf.opfmaps.listener.OPFOnInfoWindowClickListener;
import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.listener.OPFOnMapLoadedCallback;
import org.onepf.opfmaps.listener.OPFOnMapLongClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerDragListener;
import org.onepf.opfmaps.listener.OPFOnMyLocationButtonClickListener;
import org.onepf.opfmaps.listener.OPFSnapshotReadyCallback;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCameraUpdate;
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFGroundOverlay;
import org.onepf.opfmaps.model.OPFGroundOverlayOptions;
import org.onepf.opfmaps.model.OPFIndoorBuilding;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLocationSource;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFPolylineOptions;
import org.onepf.opfmaps.model.OPFProjection;
import org.onepf.opfmaps.model.OPFTileOverlay;
import org.onepf.opfmaps.model.OPFTileOverlayOptions;
import org.onepf.opfmaps.model.OPFUiSettings;

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

    void animateCamera(@NonNull final OPFCameraUpdate update,
                       final int durationMs,
                       @Nullable final OPFCancelableCallback callback);

    void animateCamera(@NonNull final OPFCameraUpdate update, @Nullable final OPFCancelableCallback callback);

    void animateCamera(@NonNull final OPFCameraUpdate update);

    void clear();

    @NonNull
    OPFCameraPosition getCameraPosition();

    @Nullable
    OPFIndoorBuilding getFocusedBuilding();

    @NonNull
    OPFMapType getMapType();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    @NonNull
    OPFProjection getProjection();

    @NonNull
    OPFUiSettings getUiSettings();

    boolean isBuildingsEnabled();

    boolean isIndoorEnabled();

    boolean isMyLocationEnabled();

    boolean isTrafficEnabled();

    void moveCamera(@NonNull final OPFCameraUpdate update);

    void setBuildingsEnabled(final boolean enabled);

    void setContentDescription(@NonNull final String description);

    boolean setIndoorEnabled(final boolean enabled);

    void setInfoWindowAdapter(@NonNull OPFInfoWindowAdapter adapter);

    void setLocationSource(@NonNull final OPFLocationSource source);

    void setMapType(@NonNull final OPFMapType type);

    void setMyLocationEnabled(final boolean enabled);

    void setOnCameraChangeListener(@Nullable final OPFOnCameraChangeListener listener);

    void setOnIndoorStateChangeListener(@Nullable final OPFOnIndoorStateChangeListener listener);

    void setOnInfoWindowClickListener(@Nullable final OPFOnInfoWindowClickListener listener);

    void setOnMapClickListener(@Nullable final OPFOnMapClickListener listener);

    void setOnMapLoadedCallback(@Nullable final OPFOnMapLoadedCallback callback);

    void setOnMapLongClickListener(@Nullable final OPFOnMapLongClickListener listener);

    void setOnMarkerClickListener(@Nullable final OPFOnMarkerClickListener listener);

    void setOnMarkerDragListener(@Nullable final OPFOnMarkerDragListener listener);

    void setOnMyLocationButtonClickListener(@Nullable final OPFOnMyLocationButtonClickListener listener);

    void setPadding(final int left, final int top, final int right, final int bottom);

    void setTrafficEnabled(final boolean enabled);

    void snapshot(@NonNull final OPFSnapshotReadyCallback callback, @NonNull final Bitmap bitmap);

    void snapshot(@NonNull final OPFSnapshotReadyCallback callback);

    void stopAnimation();
}
