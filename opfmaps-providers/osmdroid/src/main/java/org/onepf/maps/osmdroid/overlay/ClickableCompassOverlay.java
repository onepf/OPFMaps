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
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.IOrientationProvider;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;

/**
 * @author Roman Savin
 * @since 18.08.2015
 */
public class ClickableCompassOverlay extends CompassOverlay {

    private static final float COMPASS_CENTER_X = 35.0f;
    private static final float COMPASS_CENTER_Y = 35.0f;
    private static final float COMPASS_RADIUS = 20.0f;

    public ClickableCompassOverlay(@NonNull final Context context, @NonNull final MapView mapView) {
        this(context, new InternalCompassOrientationProvider(context), mapView);
    }

    public ClickableCompassOverlay(@NonNull final Context context,
                                   @NonNull final IOrientationProvider orientationProvider,
                                   @NonNull final MapView mapView) {
        this(context, orientationProvider, mapView, new DefaultResourceProxyImpl(context));
    }

    public ClickableCompassOverlay(@NonNull final Context context,
                                   @NonNull final IOrientationProvider orientationProvider,
                                   @NonNull final MapView mapView,
                                   @NonNull final ResourceProxy resourceProxy) {
        super(context, orientationProvider, mapView, resourceProxy);
        setCompassCenter(COMPASS_CENTER_X, COMPASS_CENTER_Y);

        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        onOrientationChanged(display.getRotation() * 90, orientationProvider);
    }

    @Override
    public boolean onSingleTapConfirmed(final MotionEvent event, final MapView mapView) {
        if (isTapOnCompass(event, mapView)) {
            mapView.setMapOrientation(0);
            return true;
        }

        return super.onSingleTapConfirmed(event, mapView);
    }

    public boolean isTapOnCompass(final MotionEvent event, final MapView mapView) {
        final int[] location = new int[2];
        mapView.getLocationOnScreen(location);
        final int mapViewLeft = location[0];
        final int mapViewTop = location[1];

        final float frameLeft = mapViewLeft + (COMPASS_CENTER_X - COMPASS_RADIUS) * mScale;
        final float frameTop = mapViewTop + (COMPASS_CENTER_Y - COMPASS_RADIUS) * mScale;
        final float frameRight = mapViewLeft + (COMPASS_CENTER_X + COMPASS_RADIUS) * mScale;
        final float frameBottom = mapViewTop + (COMPASS_CENTER_Y + COMPASS_RADIUS) * mScale;

        final float tapX = event.getRawX();
        final float tapY = event.getRawY();
        return tapX >= frameLeft && tapX <= frameRight && tapY >= frameTop && tapY <= frameBottom;
    }
}
