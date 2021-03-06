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
import android.support.annotation.Nullable;

import org.onepf.opfmaps.delegate.model.IndoorBuildingDelegate;

import java.util.List;

/**
 * Represents a building.
 *
 * @author Roman Savin
 * @since 30.07.2015
 */
public final class OPFIndoorBuilding implements IndoorBuildingDelegate {

    @NonNull
    private final IndoorBuildingDelegate delegate;

    public OPFIndoorBuilding(@NonNull final IndoorBuildingDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Gets the index in the list returned by {@link #getLevels()} of the level that is currently active in this
     * building (default if no active level was previously set).
     *
     * @return The active level index value.
     */
    @Override
    public int getActiveLevelIndex() {
        return delegate.getActiveLevelIndex();
    }

    /**
     * Gets the index in the list returned by {@link #getLevels()} of the default level for this building.
     *
     * @return The default level index value.
     */
    @Override
    public int getDefaultLevelIndex() {
        return delegate.getDefaultLevelIndex();
    }

    /**
     * Gets the levels in the building. While a level is usually enclosed by a single building,
     * a level might be enclosed by several buildings (e.g., a carpark level might span multiple buildings).
     * The levels are in 'display order' from top to bottom.
     *
     * @return The list of indoor levels.
     */
    @Nullable
    @Override
    public List<OPFIndoorLevel> getLevels() {
        return delegate.getLevels();
    }

    /**
     * Returns {@code true} if the building is entirely underground.
     *
     * @return {@code true} if the building is entirely underground, {@code false} otherwise.
     */
    @Override
    public boolean isUnderground() {
        return delegate.isUnderground();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFIndoorBuilding
                && delegate.equals(((OPFIndoorBuilding) other).delegate));
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
