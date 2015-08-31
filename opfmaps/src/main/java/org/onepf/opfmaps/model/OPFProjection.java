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

import org.onepf.opfmaps.delegate.model.ProjectionDelegate;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public class OPFProjection implements ProjectionDelegate {

    @NonNull
    private final ProjectionDelegate delegate;

    public OPFProjection(@NonNull final ProjectionDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    @NonNull
    public OPFLatLng fromScreenLocation(@NonNull final Point point) {
        return delegate.fromScreenLocation(point);
    }

    @Override
    @NonNull
    public OPFVisibleRegion getVisibleRegion() {
        return delegate.getVisibleRegion();
    }

    @Override
    @NonNull
    public Point toScreenLocation(@NonNull final OPFLatLng location) {
        return delegate.toScreenLocation(location);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFProjection
                && delegate.equals(((OPFProjection) other).delegate));
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
