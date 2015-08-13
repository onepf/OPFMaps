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

package org.onepf.maps.osmdroid.delegate.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.IndoorLevel;
import org.onepf.opfmaps.delegate.model.IndoorLevelDelegate;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidIndoorLevelDelegate implements IndoorLevelDelegate {

    @NonNull
    private final IndoorLevel indoorLevel;

    public OsmdroidIndoorLevelDelegate(@NonNull final IndoorLevel indoorLevel) {
        this.indoorLevel = indoorLevel;
    }

    @Override
    public void activate() {
        indoorLevel.activate();
    }

    @Nullable
    @Override
    public String getName() {
        return indoorLevel.getName();
    }

    @Nullable
    @Override
    public String getShortName() {
        return indoorLevel.getShortName();
    }

    @Override
    public int hashCode() {
        return indoorLevel.hashCode();
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidIndoorLevelDelegate)) return false;

        return indoorLevel.equals(((OsmdroidIndoorLevelDelegate) other).indoorLevel);
    }
    //CHECKSTYLE:ON

    @Override
    public String toString() {
        return indoorLevel.toString();
    }
}
