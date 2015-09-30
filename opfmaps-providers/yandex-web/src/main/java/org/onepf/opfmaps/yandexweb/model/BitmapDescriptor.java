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

package org.onepf.opfmaps.yandexweb.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.yandexweb.utils.ConvertUtils;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class BitmapDescriptor implements Parcelable {

    private static final float DEFAULT_HUE = 3F;
    private static final float SATURATION = 0.73F;
    private static final float VALUE = 0.96F;

    public static final Creator<BitmapDescriptor> CREATOR = new Creator<BitmapDescriptor>() {
        @Override
        public BitmapDescriptor createFromParcel(final Parcel source) {
            return new BitmapDescriptor(source);
        }

        @Override
        public BitmapDescriptor[] newArray(int size) {
            return new BitmapDescriptor[size];
        }
    };

    private final float hue;

    public BitmapDescriptor() {
        this(DEFAULT_HUE);
    }

    public BitmapDescriptor(final float hue) {
        this.hue = hue;
    }

    private BitmapDescriptor(@NonNull final Parcel parcel) {
        this.hue = parcel.readFloat();
    }

    @NonNull
    public String getRGBColor() {
        return ConvertUtils.convertColor(Color.HSVToColor(new float[]{hue, SATURATION, VALUE}));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            BitmapDescriptor other = (BitmapDescriptor) obj;

            return this.hue == other.hue;
        }
    }

    @Override
    public int hashCode() {
        return (hue != +0.0f ? Float.floatToIntBits(hue) : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeFloat(hue);
    }
}
