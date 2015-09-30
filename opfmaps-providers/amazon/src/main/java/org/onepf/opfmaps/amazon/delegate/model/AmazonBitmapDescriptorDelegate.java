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

package org.onepf.opfmaps.amazon.delegate.model;

import android.support.annotation.NonNull;

import com.amazon.geo.mapsv2.model.BitmapDescriptor;

import org.onepf.opfmaps.delegate.model.BitmapDescriptorDelegate;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class AmazonBitmapDescriptorDelegate implements BitmapDescriptorDelegate<BitmapDescriptor> {

    @NonNull
    private final BitmapDescriptor bitmapDescriptor;

    public AmazonBitmapDescriptorDelegate(@NonNull final BitmapDescriptor bitmapDescriptor) {
        this.bitmapDescriptor = bitmapDescriptor;
    }

    @NonNull
    @Override
    public BitmapDescriptor getBitmapDescriptor() {
        return bitmapDescriptor;
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof AmazonBitmapDescriptorDelegate
                && bitmapDescriptor.equals(((AmazonBitmapDescriptorDelegate) other).bitmapDescriptor));
    }

    @Override
    public int hashCode() {
        return bitmapDescriptor.hashCode();
    }

    @Override
    public String toString() {
        return bitmapDescriptor.toString();
    }
}
