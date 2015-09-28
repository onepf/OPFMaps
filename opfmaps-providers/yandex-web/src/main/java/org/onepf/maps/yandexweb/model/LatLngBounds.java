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

package org.onepf.maps.yandexweb.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.utils.CompareUtils;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class LatLngBounds implements Parcelable {

    public static final Creator<LatLngBounds> CREATOR = new Creator<LatLngBounds>() {
        @Override
        public LatLngBounds createFromParcel(final Parcel source) {
            return new LatLngBounds(source);
        }

        @Override
        public LatLngBounds[] newArray(final int size) {
            return new LatLngBounds[size];
        }
    };

    private static final double DEGREES_CIRCLE = 360.0D;

    @NonNull
    private final LatLng northeast;
    @NonNull
    private final LatLng southwest;

    @NonNull
    public static LatLngBounds.Builder builder() {
        return new LatLngBounds.Builder();
    }

    public LatLngBounds(@NonNull final LatLng southwest, @NonNull final LatLng northeast) {
        if (southwest.getLat() > northeast.getLat()) {
            throw new IllegalArgumentException("southern latitude exceeds northern latitude ("
                    + southwest.getLat() + " > " + northeast.getLat() + ")");
        } else {
            this.southwest = southwest;
            this.northeast = northeast;
        }
    }

    private LatLngBounds(@NonNull final Parcel source) {
        this.northeast = source.readParcelable(LatLng.class.getClassLoader());
        this.southwest = source.readParcelable(LatLng.class.getClassLoader());
    }

    public boolean contains(@NonNull final LatLng point) {
        return includes(this, point);
    }

    @NonNull
    public LatLng getCenter() {
        double latCenter = (northeast.getLat() + southwest.getLat()) / 2.0D;
        double west = southwest.getLng();
        double east = northeast.getLng();
        double longCenter;
        if (west <= east) {
            longCenter = (west + east) / 2;
        } else {
            double width = east - west + DEGREES_CIRCLE;
            longCenter = east - width / 2;
        }

        return new LatLng(latCenter, longCenter);
    }

    @NonNull
    public LatLngBounds including(@NonNull final LatLng point) {
        return new LatLngBounds.Builder(this).include(point).build();
    }

    @NonNull
    public LatLng getNortheast() {
        return northeast;
    }

    @NonNull
    public LatLng getSouthwest() {
        return southwest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(northeast, flags);
        dest.writeParcelable(southwest, flags);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof LatLngBounds)) {
            return false;
        } else {
            final LatLngBounds other = (LatLngBounds) obj;
            return CompareUtils.isEquals(other.northeast, other.northeast)
                    && CompareUtils.isEquals(other.southwest, other.southwest);
        }
    }

    @Override
    public int hashCode() {
        int result = northeast.hashCode();
        result = 31 * result + southwest.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LatLngBounds{southwest=" + southwest + ", northeast=" + northeast + "}";
    }

    private static boolean includes(@NonNull final LatLngBounds latLngBounds,
                                    @NonNull final LatLng point) {
        if (latLngBounds.northeast.getLat() >= point.getLat()
                && latLngBounds.southwest.getLat() <= point.getLat()) {
            final double west = latLngBounds.southwest.getLng();
            final double east = latLngBounds.northeast.getLng();

            if (west <= east) {
                if (point.getLng() < west || east < point.getLng()) {
                    return false;
                }
            } else if (point.getLng() < west && east < point.getLng()) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public static final class Builder {

        private boolean hasPoints;
        private double north;
        private double east;
        private double south;
        private double west;

        public Builder() {
            this.hasPoints = false;
        }

        private Builder(@NonNull final LatLngBounds bounds) {
            this.north = bounds.northeast.getLat();
            this.east = bounds.northeast.getLng();
            this.south = bounds.southwest.getLat();
            this.west = bounds.southwest.getLng();
            this.hasPoints = true;
        }

        @NonNull
        public LatLngBounds.Builder include(@NonNull final LatLng point) {
            if (!hasPoints) {
                north = point.getLat();
                south = point.getLat();
                east = point.getLng();
                west = point.getLng();
                hasPoints = true;
                return this;
            } else {
                if (north < point.getLat()) {
                    north = point.getLat();
                } else if (point.getLat() < south) {
                    south = point.getLat();
                }

                final double westwardDelta;
                final double eastwardDelta;

                if (west <= east) {
                    if (point.getLng() < west) {
                        westwardDelta = west - point.getLng();
                        eastwardDelta = point.getLng() + DEGREES_CIRCLE - east;

                        if (westwardDelta < eastwardDelta) {
                            west = point.getLng();
                        } else {
                            east = point.getLng();
                        }
                    } else if (east < point.getLng()) {
                        westwardDelta = point.getLng() - east;
                        eastwardDelta = west - (point.getLng() - DEGREES_CIRCLE);

                        if (eastwardDelta < westwardDelta) {
                            west = point.getLng();
                        } else {
                            east = point.getLng();
                        }
                    }
                } else {
                    if (point.getLng() >= west || point.getLng() <= east) {
                        return this;
                    }

                    westwardDelta = west - point.getLng();
                    eastwardDelta = point.getLng() - east;
                    if (westwardDelta < eastwardDelta) {
                        west = point.getLng();
                    } else {
                        east = point.getLng();
                    }
                }

                return this;
            }
        }

        public LatLngBounds build() {
            if (!hasPoints) {
                throw new IllegalStateException("no included points");
            } else {
                return new LatLngBounds(new LatLng(south, west), new LatLng(north, east));
            }
        }
    }
}
