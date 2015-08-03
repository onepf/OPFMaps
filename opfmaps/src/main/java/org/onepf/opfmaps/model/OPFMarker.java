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
import org.onepf.opfmaps.delegate.model.MarkerDelegate;

/**
 * Created by akarimova on 09.06.15.
 */
public final class OPFMarker implements MarkerDelegate {

    @NonNull
    private final MarkerDelegate delegate;

    public OPFMarker(@NonNull final MarkerDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public float getAlpha() {
        return delegate.getAlpha();
    }

    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return delegate.getPosition();
    }

    @Override
    public float getRotation() {
        return delegate.getRotation();
    }

    @NonNull
    @Override
    public String getSnippet() {
        return delegate.getSnippet();
    }

    @NonNull
    @Override
    public String getTitle() {
        return delegate.getTitle();
    }

    @Override
    public void hideInfoWindow() {
        delegate.hideInfoWindow();
    }

    @Override
    public boolean isDraggable() {
        return delegate.isDraggable();
    }

    @Override
    public boolean isFlat() {
        return delegate.isFlat();
    }

    @Override
    public boolean isInfoWindowShown() {
        return delegate.isInfoWindowShown();
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @Override
    public void remove() {
        delegate.remove();
    }

    @Override
    public void setAlpha(final float alpha) {
        delegate.setAlpha(alpha);
    }

    @Override
    public void setAnchor(final float anchorU, final float anchorV) {
        delegate.setAnchor(anchorU, anchorV);
    }

    @Override
    public void setDraggable(final boolean draggable) {
        delegate.setDraggable(draggable);
    }

    @Override
    public void setFlat(final boolean flat) {
        delegate.setFlat(flat);
    }

    @Override
    public void setIcon(final OPFBitmapDescriptor icon) {
        delegate.setIcon(icon);
    }

    @Override
    public void setInfoWindowAnchor(final float anchorU, final float anchorV) {
        delegate.setInfoWindowAnchor(anchorU, anchorV);
    }

    @Override
    public void setPosition(@NonNull final OPFLatLng latLng) {
        delegate.setPosition(latLng);
    }

    @Override
    public void setRotation(final float rotation) {
        delegate.setRotation(rotation);
    }

    @Override
    public void setSnippet(@NonNull final String snippet) {
        delegate.setSnippet(snippet);
    }

    @Override
    public void setTitle(@NonNull final String title) {
        delegate.setTitle(title);
    }

    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    @Override
    public void showInfoWindow() {
        delegate.showInfoWindow();
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFMarker)) return false;

        return delegate.equals(((OPFMarker) other).delegate);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
