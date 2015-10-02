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
import android.view.MotionEvent;

import org.osmdroid.ResourceProxy;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

/**
 * @author Roman Savin
 * @since 27.08.2015
 */
public final class ScrollGesturesOverlay extends Overlay {

    private boolean isScrollGesturesEnabled = true;

    public ScrollGesturesOverlay(final Context ctx) {
        super(ctx);
    }

    public ScrollGesturesOverlay(final ResourceProxy pResourceProxy) {
        super(pResourceProxy);
    }

    public void setScrollGesturesEnabled(final boolean enabled) {
        this.isScrollGesturesEnabled = enabled;
    }

    public boolean isScrollGesturesEnabled() {
        return isScrollGesturesEnabled;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event, final MapView mapView) {
        if (event.getPointerCount() == 1 && event.getAction() == MotionEvent.ACTION_MOVE) {
            return !isScrollGesturesEnabled;
        }

        return super.onTouchEvent(event, mapView);
    }

    @Override
    protected void draw(final Canvas c, final MapView osmv, final boolean shadow) {
        //nothing
    }
}
