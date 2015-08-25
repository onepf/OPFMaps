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

package org.onepf.maps.osmdroid.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.listener.OPFOnMapLongClickListener;
import org.onepf.opfmaps.model.OPFLatLng;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

/**
 * @author Roman Savin
 * @since 25.08.2015
 */
public class ClickListenerOverlay extends Overlay {

    @Nullable
    private OPFOnMapClickListener onMapClickListener;

    @Nullable
    private OPFOnMapLongClickListener onMapLongClickListener;

    public ClickListenerOverlay(@NonNull final Context context) {
        super(context);
    }

    @Override
    public boolean onSingleTapConfirmed(final MotionEvent e, final MapView mapView) {
        if (onMapClickListener == null) {
            return false;
        }

        final IGeoPoint geoPoint = mapView.getProjection().fromPixels((int) e.getX(), (int) e.getY());
        onMapClickListener.onMapClick(new OPFLatLng(geoPoint.getLatitude(), geoPoint.getLongitude()));
        return false;
    }

    @Override
    public boolean onLongPress(final MotionEvent e, final MapView mapView) {
        if (onMapLongClickListener == null) {
            return false;
        }

        final IGeoPoint geoPoint = mapView.getProjection().fromPixels((int) e.getX(), (int) e.getY());
        onMapLongClickListener.onMapLongClick(new OPFLatLng(geoPoint.getLatitude(), geoPoint.getLongitude()));
        return false;
    }

    public void setOnMapClickListener(@NonNull final OPFOnMapClickListener onMapClickListener) {
        this.onMapClickListener = onMapClickListener;
    }

    public void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener onMapLongClickListener) {
        this.onMapLongClickListener = onMapLongClickListener;
    }

    @Override
    protected void draw(final Canvas c, final MapView osmv, final boolean shadow) {
        // No drawing necessary
    }
}
