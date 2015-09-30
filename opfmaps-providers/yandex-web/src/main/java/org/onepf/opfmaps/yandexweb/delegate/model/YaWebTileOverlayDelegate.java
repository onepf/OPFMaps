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

package org.onepf.opfmaps.yandexweb.delegate.model;

import android.support.annotation.NonNull;

import org.onepf.opfmaps.delegate.model.TileOverlayDelegate;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebTileOverlayDelegate implements TileOverlayDelegate {

    @Override
    public void clearTileCache() {
        OPFLog.logStubCall();
    }

    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        OPFLog.logStubCall();
        return false;
    }

    @NonNull
    @Override
    public String getId() {
        OPFLog.logStubCall();
        return "";
    }

    @Override
    public float getZIndex() {
        OPFLog.logStubCall();
        return 0;
    }

    @Override
    public boolean isVisible() {
        OPFLog.logStubCall();
        return false;
    }

    @Override
    public void remove() {
        OPFLog.logStubCall();
    }

    @Override
    public void setFadeIn(final boolean fadeIn) {
        OPFLog.logStubCall(fadeIn);
    }

    @Override
    public void setVisible(final boolean visible) {
        OPFLog.logStubCall(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
    }
}
