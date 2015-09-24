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

    public OPFMapOptions() {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createMapOptionsDelegate();
    }

    private OPFMapOptions(@NonNull final MapOptionsDelegate delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFMapOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @NonNull
    public OPFMapOptions camera(@NonNull final OPFCameraPosition camera) {
        delegate.camera(camera);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions compassEnabled(final boolean enabled) {
        delegate.compassEnabled(enabled);
        return this;
    }

    @Override
    @Nullable
    public OPFCameraPosition getCamera() {
        return delegate.getCamera();
    }

    @Override
    @Nullable
    public Boolean getCompassEnabled() {
        return delegate.getCompassEnabled();
    }

    @Override
    @Nullable
    public Boolean getLiteMode() {
        return delegate.getLiteMode();
    }

    @Override
    @Nullable
    public Boolean getMapToolbarEnabled() {
        return delegate.getMapToolbarEnabled();
    }

    @Override
    @NonNull
    public OPFMapType getMapType() {
        return delegate.getMapType();
    }

    @Override
    @Nullable
    public Boolean getRotateGesturesEnabled() {
        return delegate.getRotateGesturesEnabled();
    }

    @Override
    @Nullable
    public Boolean getScrollGesturesEnabled() {
        return delegate.getScrollGesturesEnabled();
    }

    @Override
    @Nullable
    public Boolean getTiltGesturesEnabled() {
        return delegate.getTiltGesturesEnabled();
    }

    @Override
    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        return delegate.getUseViewLifecycleInFragment();
    }

    @Override
    @Nullable
    public Boolean getZOrderOnTop() {
        return delegate.getZOrderOnTop();
    }

    @Override
    @Nullable
    public Boolean getZoomControlsEnabled() {
        return delegate.getZoomControlsEnabled();
    }

    @Override
    @Nullable
    public Boolean getZoomGesturesEnabled() {
        return delegate.getZoomGesturesEnabled();
    }

    @Override
    @NonNull
    public OPFMapOptions liteMode(final boolean enabled) {
        delegate.liteMode(enabled);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions mapToolbarEnabled(final boolean enabled) {
        delegate.mapToolbarEnabled(enabled);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions mapType(@NonNull final OPFMapType mapType) {
        delegate.mapType(mapType);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions rotateGesturesEnabled(final boolean enabled) {
        delegate.rotateGesturesEnabled(enabled);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions scrollGesturesEnabled(final boolean enabled) {
        delegate.scrollGesturesEnabled(enabled);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions tiltGesturesEnabled(final boolean enabled) {
        delegate.tiltGesturesEnabled(enabled);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        delegate.useViewLifecycleInFragment(useViewLifecycleInFragment);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions zOrderOnTop(final boolean zOrderOnTop) {
        delegate.zOrderOnTop(zOrderOnTop);
        return this;
    }

    @Override
    @NonNull
    public OPFMapOptions zoomControlsEnabled(final boolean enabled) {
        delegate.zoomControlsEnabled(enabled);
        return this;
    }

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
