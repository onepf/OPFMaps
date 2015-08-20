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

package org.onepf.maps.osmdroid.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.maps.osmdroid.R;
import org.onepf.maps.osmdroid.utils.CompareUtils;
import org.onepf.opfutils.OPFLog;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Roman Savin
 * @since 17.08.2015
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
        public BitmapDescriptor[] newArray(final int size) {
            return new BitmapDescriptor[size];
        }
    };

    enum BitmapSource {
        DEFAULT,
        DEFAULT_HUE,
        ASSET,
        BITMAP,
        FILE_NAME,
        ABS_PATH,
        RES_ID
    }

    @NonNull
    private final BitmapSource source;
    @Nullable
    private final Bitmap image;
    @Nullable
    private final String path;
    private final float hue;
    private final int resourceId;

    BitmapDescriptor(@NonNull final BitmapSource source,
                     @Nullable final Bitmap image,
                     @Nullable final String path,
                     final float hue,
                     final int resourceId) {
        this.source = source;
        this.image = image;
        this.path = path;
        this.hue = hue;
        this.resourceId = resourceId;
    }

    private BitmapDescriptor(@NonNull final Parcel parcel) {
        this.source = BitmapSource.valueOf(parcel.readString());
        this.image = parcel.readParcelable(Bitmap.class.getClassLoader());
        this.path = parcel.readString();
        this.hue = parcel.readFloat();
        this.resourceId = parcel.readInt();
    }

    @NonNull
    public Drawable createDrawable(@NonNull final Context context) {
        Drawable drawable = null;
        switch (source) {
            case DEFAULT:
                drawable = createDefault(context, DEFAULT_HUE);
                break;
            case DEFAULT_HUE:
                drawable = createDefault(context, hue);
                break;
            case BITMAP:
                drawable = image == null ? null : new BitmapDrawable(context.getResources(), image);
                break;
            case ASSET:
            case FILE_NAME:
                drawable = createFromStream(context);
                break;
            case ABS_PATH:
                drawable = Drawable.createFromPath(path);
                break;
            case RES_ID:
                //noinspection deprecation
                drawable = context.getResources().getDrawable(resourceId);
                break;
        }

        return drawable == null ? createDefault(context, DEFAULT_HUE) : drawable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(source.name());
        dest.writeParcelable(image, flags);
        dest.writeString(path);
        dest.writeFloat(hue);
        dest.writeInt(resourceId);
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

            return this.source == other.source
                    && CompareUtils.isEquals(this.image, other.image)
                    && CompareUtils.isEquals(this.source, other.source)
                    && this.hue == other.hue
                    && this.resourceId == other.resourceId;
        }
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (hue != +0.0f ? Float.floatToIntBits(hue) : 0);
        result = 31 * result + resourceId;
        return result;
    }

    @NonNull
    private Drawable createDefault(@NonNull final Context context, final float hue) {
        //noinspection deprecation,ConstantConditions
        final Drawable drawable = context.getResources().getDrawable(R.drawable.ic_place_white_36dp).mutate();
        drawable.setColorFilter(new PorterDuffColorFilter(
                Color.HSVToColor(new float[]{hue, SATURATION, VALUE}),
                PorterDuff.Mode.MULTIPLY
        ));

        return drawable;
    }

    @Nullable
    private Drawable createFromStream(@NonNull final Context context) {
        OPFLog.logMethod();
        if (path == null) {
            return null;
        }

        InputStream inputStream = null;
        try {
            inputStream = createStream(context);
            return Drawable.createFromStream(inputStream, null);
        } catch (IOException e) {
            OPFLog.e(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @NonNull
    private InputStream createStream(@NonNull final Context context) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Path can't be null");
        }

        switch (source) {
            case ASSET:
                return context.getAssets().open(path);
            case FILE_NAME:
                return context.openFileInput(path);
        }

        throw new IllegalArgumentException("Wrong source : " + source);
    }
}
