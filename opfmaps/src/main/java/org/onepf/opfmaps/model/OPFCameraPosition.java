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

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.R;
import org.onepf.opfmaps.delegate.model.CameraPositionDelegate;

/**
 * An immutable class that aggregates all camera position parameters.
 *
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OPFCameraPosition implements CameraPositionDelegate {

    public static final Creator<OPFCameraPosition> CREATOR = new Creator<OPFCameraPosition>() {
        @Override
        public OPFCameraPosition createFromParcel(final Parcel source) {
            return new OPFCameraPosition(source);
        }

        @Override
        public OPFCameraPosition[] newArray(final int size) {
            return new OPFCameraPosition[size];
        }
    };

    /**
     * Creates a builder for a camera position.
     *
     * @return The {@link org.onepf.opfmaps.model.OPFCameraPosition.Builder} instance.
     */
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a builder for a camera position, initialized to a given position.
     *
     * @param camera The given position.
     * @return The {@link org.onepf.opfmaps.model.OPFCameraPosition.Builder} instance.
     */
    @NonNull
    public static Builder builder(@NonNull final OPFCameraPosition camera) {
        return new Builder(camera);
    }

    /**
     * Creates a camera position from the attribute set.
     *
     * @param context The {@link Context} instance.
     * @param attrs   The attribute set.
     * @return Created camera position.
     */
    @Nullable
    public static OPFCameraPosition createFromAttributes(@NonNull final Context context,
                                                         @Nullable final AttributeSet attrs) {
        if (attrs == null) {
            return null;
        }

        final TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.OPFMapAttrs);

        float latitude = 0.0F;
        float longitude = 0.0F;

        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_cameraTargetLat)) {
            latitude = typedArray.getFloat(R.styleable.OPFMapAttrs_opf_cameraTargetLat, 0.0F);
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_cameraTargetLng)) {
            longitude = typedArray.getFloat(R.styleable.OPFMapAttrs_opf_cameraTargetLng, 0.0F);
        }

        final OPFLatLng target = new OPFLatLng((double) latitude, (double) longitude);
        final OPFCameraPosition.Builder builder = builder();

        builder.target(target);
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_cameraZoom)) {
            builder.zoom(typedArray.getFloat(R.styleable.OPFMapAttrs_opf_cameraZoom, 0.0F));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_cameraBearing)) {
            builder.bearing(typedArray.getFloat(R.styleable.OPFMapAttrs_opf_cameraBearing, 0.0F));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_cameraTilt)) {
            builder.tilt(typedArray.getFloat(R.styleable.OPFMapAttrs_opf_cameraTilt, 0.0F));
        }

        typedArray.recycle();
        return builder.build();
    }

    /**
     * Constructs a camera position pointed for a particular target and zoom level.
     * The resultant bearing is North, and the viewing angle is perpendicular to the Earth's surface. i.e.,
     * directly facing the Earth's surface, with the top of the screen pointing North.
     *
     * @param target The target location to align with the center of the screen.
     * @param zoom   Zoom level at target.
     * @return Created camera position.
     */
    @NonNull
    public static OPFCameraPosition fromLatLngZoom(@NonNull final OPFLatLng target, final float zoom) {
        return new OPFCameraPosition(OPFMapHelper.getInstance().getDelegatesFactory()
                .createCameraPositionDelegate(target, zoom));
    }

    @NonNull
    private final CameraPositionDelegate delegate;

    /**
     * Constructs a OPFCameraPosition.
     *
     * @param target  The target location to align with the center of the screen.
     * @param zoom    Zoom level at target.
     * @param tilt    The camera angle, in degrees, from the nadir (directly down).
     * @param bearing Direction that the camera is pointing in, in degrees clockwise from north.
     *                This value will be normalized to be within 0 degrees inclusive and 360 degrees exclusive.
     */
    public OPFCameraPosition(@NonNull final OPFLatLng target,
                             final float zoom,
                             final float tilt,
                             final float bearing) {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory()
                .createCameraPositionDelegate(target, zoom, tilt, bearing);
    }

    public OPFCameraPosition(@NonNull final CameraPositionDelegate delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFCameraPosition(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the direction that the camera is pointing in, in degrees clockwise from north.
     *
     * @return The direction that the camera is pointing in, in degrees clockwise from north.
     */
    @Override
    public float getBearing() {
        return delegate.getBearing();
    }

    /**
     * Returns the location that the camera is pointing at.
     *
     * @return The location that the camera is pointing at.
     */
    @Override
    @NonNull
    public OPFLatLng getTarget() {
        return delegate.getTarget();
    }

    /**
     * Returns the angle, in degrees, of the camera angle from the nadir (directly facing the Earth).
     *
     * @return The angle, in degrees, of the camera angle from the nadir (directly facing the Earth).
     */
    @Override
    public float getTilt() {
        return delegate.getTilt();
    }

    /**
     * Returns zoom level near the center of the screen.
     *
     * @return Zoom level near the center of the screen.
     */
    @Override
    public float getZoom() {
        return delegate.getZoom();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFCameraPosition
                && delegate.equals(((OPFCameraPosition) other).delegate));
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(delegate.getClass().getCanonicalName());
        dest.writeParcelable(delegate, flags);
    }

    @Override
    public int describeContents() {
        return delegate.describeContents();
    }

    public static class Builder implements CameraPositionDelegate.Builder {

        @NonNull
        private final CameraPositionDelegate.Builder delegate;

        public Builder() {
            this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createCameraPositionBuilderDelegate();
        }

        public Builder(@NonNull final OPFCameraPosition camera) {
            this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createCameraPositionBuilderDelegate(camera);
        }

        @NonNull
        @Override
        public Builder bearing(final float bearing) {
            delegate.bearing(bearing);
            return this;
        }

        @NonNull
        @Override
        public Builder target(@NonNull final OPFLatLng target) {
            delegate.target(target);
            return this;
        }

        @NonNull
        @Override
        public Builder tilt(final float tilt) {
            delegate.tilt(tilt);
            return this;
        }

        @NonNull
        @Override
        public Builder zoom(final float zoom) {
            delegate.zoom(zoom);
            return this;
        }

        @NonNull
        @Override
        public OPFCameraPosition build() {
            return delegate.build();
        }
    }
}
