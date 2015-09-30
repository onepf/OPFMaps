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

package org.onepf.opfmaps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import org.onepf.opfmaps.delegate.MapOptionsDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFMapType;

/**
 * Defines configuration {@link OPFMapOptions} for a {@link OPFMap}.
 * These options can be used when adding a map to your application programmatically (as opposed to via XML).
 * If you are using a {@link OPFMapFragment} or {@link OPFSupportMapFragment}, you can pass these options in using
 * the static factory method {@code newInstance(OPFMapOptions)}. If you are using a {@link OPFMapView},
 * you can pass these options in using the constructor {@link OPFMapView(Context, OPFMapOptions)}.
 * <p/>
 * If you add a map using XML, then you can apply these options using custom XML tags.
 *
 * @author Roman Savin
 * @since 06.08.2015
 */
@SuppressWarnings("PMD.NPathComplexity")
public final class OPFMapOptions implements MapOptionsDelegate {

    public static final Creator<OPFMapOptions> CREATOR = new Creator<OPFMapOptions>() {
        @Override
        public OPFMapOptions createFromParcel(final Parcel source) {
            return new OPFMapOptions(source);
        }

        @Override
        public OPFMapOptions[] newArray(final int size) {
            return new OPFMapOptions[size];
        }
    };

    /**
     * Creates a {@link OPFMapOptions} from the attribute set.
     *
     * @param context The {@link Context} instance.
     * @param attrs   The attributes set.
     * @return The created {@link OPFMapOptions} object.
     */
    @Nullable
    public static OPFMapOptions createFromAttributes(@NonNull final Context context,
                                                     @Nullable final AttributeSet attrs) {

        final OPFMapOptions options = new OPFMapOptions();
        final TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.OPFMapAttrs);

        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_mapType)) {
            options.mapType(OPFMapType.fromId(typedArray.getInt(R.styleable.OPFMapAttrs_opf_mapType, -1)));
        }

        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_liteMode)) {
            options.liteMode(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_liteMode, false));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_uiCompass)) {
            options.compassEnabled(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_uiCompass, true));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_uiRotateGestures)) {
            options.rotateGesturesEnabled(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_uiRotateGestures, true));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_uiScrollGestures)) {
            options.scrollGesturesEnabled(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_uiScrollGestures, true));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_uiTiltGestures)) {
            options.tiltGesturesEnabled(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_uiTiltGestures, true));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_uiZoomControls)) {
            options.zoomControlsEnabled(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_uiZoomControls, false));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_uiZoomGestures)) {
            options.zoomGesturesEnabled(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_uiZoomGestures, true));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_useViewLifecycle)) {
            options.useViewLifecycleInFragment(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_useViewLifecycle, false));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_zOrderOnTop)) {
            options.zOrderOnTop(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_zOrderOnTop, false));
        }
        if (typedArray.hasValue(R.styleable.OPFMapAttrs_opf_uiMapToolbar)) {
            options.mapToolbarEnabled(typedArray.getBoolean(R.styleable.OPFMapAttrs_opf_uiMapToolbar, true));
        }
        final OPFCameraPosition cameraPosition = OPFCameraPosition.createFromAttributes(context, attrs);
        if (cameraPosition != null) {
            options.camera(cameraPosition);
        }

        typedArray.recycle();
        return options;
    }

    @NonNull
    private final MapOptionsDelegate delegate;

    /**
     * Creates a new {@link OPFMapOptions} object.
     */
    public OPFMapOptions() {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createMapOptionsDelegate();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFMapOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Specifies a the initial camera position for the map.
     *
     * @param camera The {@link OPFCameraPosition} instance.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions camera(@NonNull final OPFCameraPosition camera) {
        delegate.camera(camera);
        return this;
    }

    /**
     * Specifies whether the compass should be enabled. The default value is {@code true}.
     *
     * @param enabled {@code true} to enable compass, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions compassEnabled(final boolean enabled) {
        delegate.compassEnabled(enabled);
        return this;
    }

    /**
     * Returns the camera option, or null if unspecified.
     *
     * @return The camera option, or null if unspecified.
     */
    @Override
    @Nullable
    public OPFCameraPosition getCamera() {
        return delegate.getCamera();
    }

    /**
     * Returns the compassEnabled option, or null if unspecified.
     *
     * @return The compassEnabled option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getCompassEnabled() {
        return delegate.getCompassEnabled();
    }

    /**
     * Returns the liteMode option, or null if unspecified.
     *
     * @return The liteMode option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getLiteMode() {
        return delegate.getLiteMode();
    }

    /**
     * Returns the mapToolbarEnabled option, or null if unspecified.
     *
     * @return The mapToolbarEnabled option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getMapToolbarEnabled() {
        return delegate.getMapToolbarEnabled();
    }

    /**
     * Returns the map type options. The default value is {@link OPFMapType#NORMAL}.
     *
     * @return The map type.
     */
    @Override
    @NonNull
    public OPFMapType getMapType() {
        return delegate.getMapType();
    }

    /**
     * Returns the rotateGesturesEnabled option, or null if unspecified.
     *
     * @return The rotateGesturesEnabled option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getRotateGesturesEnabled() {
        return delegate.getRotateGesturesEnabled();
    }

    /**
     * Returns the scrollGesturesEnabled option, or null if unspecified.
     *
     * @return The scrollGesturesEnabled option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getScrollGesturesEnabled() {
        return delegate.getScrollGesturesEnabled();
    }

    /**
     * Returns the tiltGesturesEnabled option, or null if unspecified.
     *
     * @return The tiltGesturesEnabled option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getTiltGesturesEnabled() {
        return delegate.getTiltGesturesEnabled();
    }

    /**
     * Returns the useViewLifecycleInFragment option, or null if unspecified.
     *
     * @return The useViewLifecycleInFragment option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        return delegate.getUseViewLifecycleInFragment();
    }

    /**
     * Returns the zOrderOnTop option, or null if unspecified.
     *
     * @return The zOrderOnTop option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getZOrderOnTop() {
        return delegate.getZOrderOnTop();
    }

    /**
     * Returns the zoomGesturesEnabled option, or null if unspecified.
     *
     * @return The zoomGesturesEnabled option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getZoomControlsEnabled() {
        return delegate.getZoomControlsEnabled();
    }

    /**
     * Returns the zoomGesturesEnabled option, or null if unspecified.
     *
     * @return The zoomGesturesEnabled option, or null if unspecified.
     */
    @Override
    @Nullable
    public Boolean getZoomGesturesEnabled() {
        return delegate.getZoomGesturesEnabled();
    }

    /**
     * Specifies whether the map should be created in lite mode. The default value is {@code false}.
     * If lite mode is enabled, maps will load as static images. This improves performance in the case where a lot
     * of maps need to be displayed at the same time, for example in a scrolling list, however lite-mode maps cannot
     * be panned or zoomed by the user, or tilted or rotated at all.
     *
     * @param enabled {@code true} to enable liteMode, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions liteMode(final boolean enabled) {
        delegate.liteMode(enabled);
        return this;
    }

    /**
     * Specifies whether the mapToolbar should be enabled. The default value is {@code true}.
     *
     * @param enabled {@code true} to enable mapToolbar, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions mapToolbarEnabled(final boolean enabled) {
        delegate.mapToolbarEnabled(enabled);
        return this;
    }

    /**
     * Specifies a change to the initial map type.
     *
     * @param mapType The {@link OPFMapType} value.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions mapType(@NonNull final OPFMapType mapType) {
        delegate.mapType(mapType);
        return this;
    }

    /**
     * Specifies whether rotate gestures should be enabled. The default value is {@code true}.
     *
     * @param enabled {@code true} to enable rotate gestures, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions rotateGesturesEnabled(final boolean enabled) {
        delegate.rotateGesturesEnabled(enabled);
        return this;
    }

    /**
     * Specifies whether scroll gestures should be enabled. The default value is {@code true}.
     *
     * @param enabled {@code true} to enable scroll gestures, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions scrollGesturesEnabled(final boolean enabled) {
        delegate.scrollGesturesEnabled(enabled);
        return this;
    }

    /**
     * Specifies whether tilt gestures should be enabled. The default value is {@code true}.
     *
     * @param enabled {@code true} to enable tilt gestures, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions tiltGesturesEnabled(final boolean enabled) {
        delegate.tiltGesturesEnabled(enabled);
        return this;
    }

    /**
     * When using a {@link OPFMapFragment} or {@link OPFSupportMapFragment},
     * this flag specifies whether the lifecycle of the map should be tied to the fragment's view or the fragment itself.
     * The default value is {@code false}, tying the lifecycle of the map to the fragment.
     *
     * @param useViewLifecycleInFragment {@code true} to use lifecycle in fragment, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        delegate.useViewLifecycleInFragment(useViewLifecycleInFragment);
        return this;
    }

    /**
     * Control whether the map view's surface is placed on top of its window.
     *
     * @param zOrderOnTop {@code true} to enable zOrderOnTop, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions zOrderOnTop(final boolean zOrderOnTop) {
        delegate.zOrderOnTop(zOrderOnTop);
        return this;
    }

    /**
     * Specifies whether the zoom controls should be enabled. The default value is {@code true}.
     *
     * @param enabled {@code true} to enable zoom controls, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions zoomControlsEnabled(final boolean enabled) {
        delegate.zoomControlsEnabled(enabled);
        return this;
    }

    /**
     * Specifies whether zoom gestures should be enabled. The default value is {@code true}.
     *
     * @param enabled {@code true} to enable zoom gestures, {@code false} otherwise.
     * @return This {@link OPFMapOptions} object.
     */
    @Override
    @NonNull
    public OPFMapOptions zoomGesturesEnabled(final boolean enabled) {
        delegate.zoomGesturesEnabled(enabled);
        return this;
    }

    @Override
    public int describeContents() {
        return delegate.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(delegate.getClass().getCanonicalName());
        dest.writeParcelable(delegate, flags);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFMapOptions
                && delegate.equals(((OPFMapOptions) other).delegate));
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
