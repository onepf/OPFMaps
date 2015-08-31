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

import android.support.annotation.NonNull;

import org.onepf.opfmaps.delegate.model.BitmapDescriptorDelegate;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public final class OPFBitmapDescriptor {

    @NonNull
    private final BitmapDescriptorDelegate delegate;

    public OPFBitmapDescriptor(@NonNull final BitmapDescriptorDelegate delegate) {
        this.delegate = delegate;
    }

    @NonNull
    public BitmapDescriptorDelegate getDelegate() {
        return delegate;
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFBitmapDescriptor
                && delegate.equals(((OPFBitmapDescriptor) other).delegate));
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
