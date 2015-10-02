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

package org.onepf.opfmaps.osmdroid.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.IOverlayMenuProvider;
import org.osmdroid.views.overlay.Overlay;

/**
 * @author Roman Savin
 * @since 18.08.2015
 */
public class RotationGestureOverlay extends Overlay implements IOverlayMenuProvider {

    private static final int MENU_ENABLED = getSafeMenuId();
    private static final int MENU_ROTATE_CCW = getSafeMenuId();
    private static final int MENU_ROTATE_CW = getSafeMenuId();

    @NonNull
    private final RotationGestureDetector rotationDetector;

    @NonNull
    private final MapView mapView;

    private boolean optionsMenuEnabled;

    public RotationGestureOverlay(@NonNull final Context context, @NonNull final MapView mapView) {
        super(context);
        this.mapView = mapView;
        rotationDetector = new RotationGestureDetector();
    }

    @Override
    protected void draw(final Canvas c, final MapView osmv, final boolean shadow) {
        // No drawing necessary
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event, final MapView mapView) {
        if (this.isEnabled()) {
            rotationDetector.onTouch(event);
        }
        return super.onTouchEvent(event, mapView);
    }

    @Override
    public boolean isOptionsMenuEnabled() {
        return optionsMenuEnabled;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu, final int menuIdOffset, final MapView mapView) {
        menu.add(0, MENU_ENABLED + menuIdOffset, Menu.NONE, "Enable rotation")
                .setIcon(android.R.drawable.ic_menu_info_details);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item, final int menuIdOffset, final MapView mapView) {
        if (item.getItemId() == MENU_ENABLED + menuIdOffset) {
            if (this.isEnabled()) {
                this.mapView.setMapOrientation(0);
                this.setEnabled(false);
            } else {
                this.setEnabled(true);
                return true;
            }
        } else if (item.getItemId() == MENU_ROTATE_CCW + menuIdOffset) {
            this.mapView.setMapOrientation(this.mapView.getMapOrientation() - 10);
        } else if (item.getItemId() == MENU_ROTATE_CW + menuIdOffset) {
            this.mapView.setMapOrientation(this.mapView.getMapOrientation() + 10);
        }

        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu, final int menuIdOffset, final MapView mapView) {
        menu.findItem(MENU_ENABLED + menuIdOffset).setTitle(
                this.isEnabled() ? "Disable rotation" : "Enable rotation");
        return false;
    }

    @Override
    public void setOptionsMenuEnabled(final boolean enabled) {
        optionsMenuEnabled = enabled;
    }

    private class RotationGestureDetector {

        private static final int START_ROTATION_DEGREES = 2;

        private boolean isRotationStarted;

        private float rotation;

        private float initialRotation;

        public void onTouch(final MotionEvent event) {
            if (event.getPointerCount() != 2) {
                return;
            }

            if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
                rotation = rotation(event);
                initialRotation = rotation;
            } else if (event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
                isRotationStarted = false;
                return;
            }

            float currentRotation = rotation(event);
            float delta = currentRotation - rotation;
            rotation += delta / 100;

            if (!isRotationStarted && Math.abs(initialRotation - rotation) > START_ROTATION_DEGREES) {
                isRotationStarted = true;
                rotation = currentRotation;
                return;
            }

            if (isRotationStarted) {
                isRotationStarted = true;
                mapView.setMapOrientation(mapView.getMapOrientation() + delta);
            }
        }

        private float rotation(final MotionEvent event) {
            double deltaX = event.getX(0) - event.getX(1);
            double deltaY = event.getY(0) - event.getY(1);
            double radians = Math.atan2(deltaY, deltaX);
            return (float) Math.toDegrees(radians);
        }
    }
}
