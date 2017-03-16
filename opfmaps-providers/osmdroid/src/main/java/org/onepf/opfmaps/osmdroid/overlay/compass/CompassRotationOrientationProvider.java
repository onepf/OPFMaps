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

package org.onepf.opfmaps.osmdroid.overlay.compass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.WindowManager;
import org.onepf.opfmaps.osmdroid.overlay.listener.RotationObserver;
import org.osmdroid.views.overlay.compass.IOrientationConsumer;
import org.osmdroid.views.overlay.compass.IOrientationProvider;

/**
 * @author Roman Savin
 * @since 18.08.2015
 */
public final class CompassRotationOrientationProvider implements IOrientationProvider, RotationObserver {

    private static final float CIRCLE_DEGREES = 360.0F;
    private static final float ROTATION_COEFFICIENT = 270.0F;

    @Nullable
    private IOrientationConsumer orientationConsumer;

    private final int screenRotation;

    private float orientation;

    public CompassRotationOrientationProvider(@NonNull final Context context) {
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        this.screenRotation = display.getRotation();
    }

    @Override
    public boolean startOrientationProvider(@NonNull final IOrientationConsumer orientationConsumer) {
        this.orientationConsumer = orientationConsumer;
        return true;
    }

    @Override
    public void stopOrientationProvider() {
        orientationConsumer = null;
    }

    @Override
    public float getLastKnownOrientation() {
        return orientation;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onRotate(final float degrees) {
        orientation = degrees % CIRCLE_DEGREES + ROTATION_COEFFICIENT * screenRotation;
        if (orientationConsumer != null) {
            orientationConsumer.onOrientationChanged(orientation, this);
        }
    }
}
