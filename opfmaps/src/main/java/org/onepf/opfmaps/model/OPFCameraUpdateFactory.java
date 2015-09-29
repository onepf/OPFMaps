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

package org.onepf.opfmaps.model;

import android.graphics.Point;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.OPFMapHelper;

/**
 * A class containing methods for creating CameraUpdate objects that change a map's camera.
 *
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OPFCameraUpdateFactory {

    private OPFCameraUpdateFactory() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a camera update that moves the camera to a specified camera position.
     * In effect, this creates a transformation from the {@link OPFCameraPosition} object's latitude, longitude,
     * zoom level, bearing and tilt.
     *
     * @param cameraPosition The {@link OPFCameraPosition} instance.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate newCameraPosition(@NonNull final OPFCameraPosition cameraPosition) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory()
                .newCameraPosition(cameraPosition);
    }

    /**
     * Returns a camera update that moves the center of the screen to a latitude and longitude specified by a {@link OPFLatLng} object.
     *
     * @param latLng The {@link OPFLatLng} object containing the desired latitude and longitude.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate newLatLng(@NonNull final OPFLatLng latLng) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().newLatLng(latLng);
    }

    /**
     * Returns a camera update that transforms the camera such that the specified latitude/longitude bounds are centered
     * on screen at the greatest possible zoom level. You can specify padding, in order to inset the bounding box from the map view's edges.
     * The returned {@link OPFCameraUpdate} has a bearing of 0 and a tilt of 0.
     * <p/>
     * Do not change the camera with this camera update until the map has undergone layout
     * (in order for this method to correctly determine the appropriate bounding box and zoom level, the map must have a size).
     * Otherwise an {@link IllegalStateException} will be thrown. It is NOT sufficient for the map to be available
     * The view containing the map must have also undergone layout such that its dimensions have been determined.
     * If you cannot be sure that this has occurred, use {@link #newLatLngBounds(OPFLatLngBounds, int, int, int)}
     * instead and provide the dimensions of the map manually.
     *
     * @param bounds  The region to fit on screen.
     * @param padding Space (in px) to leave between the bounding box edges and the view edges.
     *                This value is applied to all four sides of the bounding box.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds, final int padding) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory()
                .newLatLngBounds(bounds, padding);
    }

    /**
     * Returns a camera update that transforms the camera such that the specified latitude/longitude bounds are centered
     * on screen within a bounding box of specified dimensions at the greatest possible zoom level.
     * You can specify additional padding, to further restrict the size of the bounding box.
     * The returned {@link OPFCameraUpdate} has a bearing of 0 and a tilt of 0.
     * <p/>
     * Unlike {@link #newLatLngBounds(OPFLatLngBounds, int)}, you can use the {@link OPFCameraUpdate} returned by this method
     * to change the camera prior to the map's the layout phase, because the arguments specify the desired size of the bounding box.
     *
     * @param bounds  The region to fit in the bounding box.
     * @param width   The bounding box width in pixels (px).
     * @param height  The bounding box height in pixels (px).
     * @param padding The additional size restriction (in px) of the bounding box.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds,
                                                  final int width,
                                                  final int height,
                                                  final int padding) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory()
                .newLatLngBounds(bounds, width, height, padding);
    }

    /**
     * Returns a camera update that moves the center of the screen to a latitude and longitude specified by a {@link OPFLatLng} object,
     * and moves to the given zoom level.
     *
     * @param latLng The {@link OPFLatLng} object containing the desired latitude and longitude.
     * @param zoom   The desired zoom level.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate newLatLngZoom(@NonNull final OPFLatLng latLng, final float zoom) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().newLatLngZoom(latLng, zoom);
    }

    /**
     * Returns a camera update that scrolls the camera over the map, shifting the center of view by the specified number of pixels
     * in the x and y directions.
     *
     * @param xPixel The number of pixels to scroll horizontally. A positive value moves the camera to the right,
     *               with respect to its current orientation. A negative value moves the camera to the left,
     *               with respect to its current orientation.
     * @param yPixel The number of pixels to scroll vertically. A positive value moves the camera downwards,
     *               with respect to its current orientation. A negative value moves the camera upwards,
     *               with respect to its current orientation.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate scrollBy(final float xPixel, final float yPixel) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().scrollBy(xPixel, yPixel);
    }

    /**
     * Returns a camera update that shifts the zoom level of the current camera viewpoint.
     * <p/>
     * A point specified by focus will remain fixed (i.e., it corresponds to the same lat/long both before and after the zoom process).
     *
     * @param amount The amount to change the zoom level. Positive values indicate zooming closer to the surface of the Earth
     *               while negative values indicate zooming away from the surface of the Earth.
     * @param focus  The pixel location on the screen that is to remain fixed after the zooming process.
     *               The lat/lng that was at that pixel location before the camera move will remain the same after the camera has moved.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate zoomBy(final float amount, @NonNull final Point focus) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomBy(amount, focus);
    }

    /**
     * Returns a camera update that shifts the zoom level of the current camera viewpoint.
     *
     * @param amount The amount to change the zoom level. Positive values indicate zooming closer to the surface of the Earth
     *               while negative values indicate zooming away from the surface of the Earth.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate zoomBy(final float amount) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomBy(amount);
    }

    /**
     * Returns a camera update that zooms in on the map by moving the viewpoint's height closer to the Earth's surface.
     * The zoom increment is 1.0.
     *
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate zoomIn() {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomIn();
    }

    /**
     * Returns a camera update that zooms out on the map by moving the viewpoint's height farther away from the Earth's surface.
     * The zoom increment is -1.0.
     *
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate zoomOut() {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomOut();
    }

    /**
     * Returns a camera update that moves the camera viewpoint to a particular zoom level.
     *
     * @param zoom The desired zoom level.
     * @return Created {@link OPFCameraUpdate} object.
     */
    @NonNull
    public static OPFCameraUpdate zoomTo(final float zoom) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomTo(zoom);
    }
}
