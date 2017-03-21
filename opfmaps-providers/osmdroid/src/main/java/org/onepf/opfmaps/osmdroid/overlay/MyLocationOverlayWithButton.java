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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import org.onepf.opfmaps.osmdroid.R;
import org.onepf.opfmaps.listener.OPFOnMyLocationButtonClickListener;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

/**
 * @author Roman Savin
 * @since 26.08.2015
 */
public class MyLocationOverlayWithButton extends MyLocationNewOverlay {

    private static final float CENTER_OFFSET_DP = 36F;
    private static final float SIZE = 48F;
    private static final int MY_LOCATION_ZOOM_LEVEL = 15;
    private static final float INACCURACY = 0.5f;
    private static final int PRESSED_FRAME_ALPHA = 30;

    @NonNull
    private final Bitmap goToMyLocationPicture;
    @NonNull
    private final Matrix matrix = new Matrix();
    @NonNull
    private final Paint rectPaint = new Paint();
    @NonNull
    private final Paint smoothPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
    @Nullable
    private OPFOnMyLocationButtonClickListener onMyLocationButtonClickListener;

    private final float frameCenterX;
    private final float frameCenterY;

    private float centerX;
    private float centerY;

    private boolean isPressed;
    private boolean isMyLocationButtonEnabled = true;

    private int frameLeft;
    private int frameTop;
    private int frameRight;
    private int frameBottom;

    public MyLocationOverlayWithButton(final Context context, final MapView mapView) {
        this(context, new GpsMyLocationProvider(context), mapView);
    }

    /*public MyLocationOverlayWithButton(final Context context,
                                       final IMyLocationProvider myLocationProvider,
                                       final MapView mapView) {
        this(context, myLocationProvider, mapView, new DefaultResourceProxyImpl(context));
    }*/

    public MyLocationOverlayWithButton(final Context context,
                                       final IMyLocationProvider myLocationProvider,
                                       final MapView mapView) {
        super(myLocationProvider, mapView);
        this.goToMyLocationPicture = BitmapFactory
                .decodeResource(context.getResources(), R.drawable.ic_my_location);

        frameCenterX = goToMyLocationPicture.getWidth() / 2 + INACCURACY;
        frameCenterY = goToMyLocationPicture.getHeight() / 2 + INACCURACY;
    }

    @Override
    public void draw(final Canvas canvas, final MapView mapView, final boolean shadow) {
        super.draw(canvas, mapView, shadow);
        if (centerX == 0 && centerY == 0) {
            centerX = mapView.getWidth() - CENTER_OFFSET_DP * mScale;
            centerY = CENTER_OFFSET_DP * mScale;
        }

        if (isMyLocationEnabled() && isMyLocationButtonEnabled) {
            matrix.setTranslate(-frameCenterX, -frameCenterY);
            matrix.postTranslate(centerX, centerY);

            canvas.save();
            canvas.concat(mMapView.getProjection().getInvertedScaleRotateCanvasMatrix());
            canvas.concat(matrix);
            canvas.drawBitmap(goToMyLocationPicture, 0, 0, smoothPaint);
            if (isPressed) {
                rectPaint.setColor(Color.argb(PRESSED_FRAME_ALPHA, 0, 0, 0));
                canvas.drawRect(0, 0, goToMyLocationPicture.getWidth(), goToMyLocationPicture.getHeight(), rectPaint);
            }
            canvas.restore();
        }
    }

    @Override
    public void disableMyLocation() {
        super.disableMyLocation();
        invalidateMyLocationFrame();
    }

    @Override
    public boolean enableMyLocation() {
        final boolean isSuccess = super.enableMyLocation();
        if (isSuccess) {
            invalidateMyLocationFrame();
        }
        return isSuccess;
    }

    @Override
    public boolean onSingleTapConfirmed(final MotionEvent event, final MapView mapView) {
        if (isTapOnGoToMyLocationIcon(event) && isMyLocationEnabled() && isMyLocationButtonEnabled) {
            final boolean isHandled = handleClickListener();
            if (!isHandled) {
                moveToLastKnownLocation();
            }
            return true;
        }

        return super.onSingleTapConfirmed(event, mapView);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event, final MapView mapView) {
        if (isMyLocationEnabled() && isMyLocationButtonEnabled) {
            if (isTapOnGoToMyLocationIcon(event) && event.getAction() != MotionEvent.ACTION_UP) {
                isPressed = true;
                invalidateMyLocationFrame();
            } else if (isPressed) {
                isPressed = false;
                invalidateMyLocationFrame();
            }
        }
        return super.onTouchEvent(event, mapView);
    }

    public void enableMyLocationButton() {
        if (!isMyLocationButtonEnabled) {
            isMyLocationButtonEnabled = true;
            invalidateMyLocationFrame();
        }
    }

    public void disableMyLocationButton() {
        if (isMyLocationButtonEnabled) {
            isMyLocationButtonEnabled = false;
            invalidateMyLocationFrame();
        }
    }

    public void setOnMyLocationButtonClickListener(
            @NonNull final OPFOnMyLocationButtonClickListener onMyLocationButtonClickListener
    ) {
        this.onMyLocationButtonClickListener = onMyLocationButtonClickListener;
    }

    public boolean isMyLocationButtonEnabled() {
        return isMyLocationButtonEnabled;
    }

    private void invalidateMyLocationFrame() {
        updateFrameCoordinates();
        mMapView.postInvalidateMapCoordinates(frameLeft, frameTop, frameRight, frameBottom);
    }

    private boolean isTapOnGoToMyLocationIcon(final MotionEvent event) {
        updateFrameCoordinates();

        final float tapX = event.getRawX();
        final float tapY = event.getRawY();
        return tapX >= frameLeft && tapX <= frameRight && tapY >= frameTop && tapY <= frameBottom;
    }

    private void updateFrameCoordinates() {
        final int[] location = new int[2];
        mMapView.getLocationOnScreen(location);
        final int mapViewLeft = location[0];
        final int mapViewTop = location[1];

        final int centerX = (int) ((mMapView.getWidth() - CENTER_OFFSET_DP * mScale) / mScale);
        frameLeft = (int) (mapViewLeft + (centerX - SIZE / 2) * mScale);
        frameTop = (int) (mapViewLeft + (CENTER_OFFSET_DP - SIZE / 2) * mScale);
        frameRight = (int) (mapViewLeft + (centerX + SIZE / 2) * mScale);
        frameBottom = (int) (mapViewTop + (CENTER_OFFSET_DP + SIZE / 2) * mScale);
    }

    private boolean handleClickListener() {
        //noinspection SimplifiableIfStatement
        if (onMyLocationButtonClickListener != null) {
            return onMyLocationButtonClickListener.onMyLocationButtonClick();
        }
        return false;
    }

    private void moveToLastKnownLocation() {
        final GeoPoint lastKnownLocation = getMyLocation();
        if (lastKnownLocation != null) {
            final IMapController controller = mMapView.getController();
            controller.setZoom(MY_LOCATION_ZOOM_LEVEL);
            controller.animateTo(lastKnownLocation);
        }
    }
}
