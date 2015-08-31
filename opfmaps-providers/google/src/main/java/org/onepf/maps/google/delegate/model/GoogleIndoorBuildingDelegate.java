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

package org.onepf.maps.google.delegate.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.IndoorLevel;

import org.onepf.opfmaps.delegate.model.IndoorBuildingDelegate;
import org.onepf.opfmaps.model.OPFIndoorLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class GoogleIndoorBuildingDelegate implements IndoorBuildingDelegate {

    @NonNull
    private final IndoorBuilding indoorBuilding;

    public GoogleIndoorBuildingDelegate(@NonNull final IndoorBuilding indoorBuilding) {
        this.indoorBuilding = indoorBuilding;
    }

    @Override
    public int getActiveLevelIndex() {
        return indoorBuilding.getActiveLevelIndex();
    }

    @Override
    public int getDefaultLevelIndex() {
        return indoorBuilding.getDefaultLevelIndex();
    }

    @Nullable
    @Override
    public List<OPFIndoorLevel> getLevels() {
        final List<IndoorLevel> googleIndoorLevels = indoorBuilding.getLevels();
        if (googleIndoorLevels == null) {
            return null;
        }

        final List<OPFIndoorLevel> indoorLevels = new ArrayList<>(googleIndoorLevels.size());
        for (IndoorLevel googleLevel : googleIndoorLevels) {
            indoorLevels.add(new OPFIndoorLevel(new GoogleIndoorLevelDelegate(googleLevel)));
        }

        return indoorLevels;
    }

    @Override
    public boolean isUnderground() {
        return indoorBuilding.isUnderground();
    }

    @Override
    public int hashCode() {
        return indoorBuilding.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof GoogleIndoorBuildingDelegate
                && indoorBuilding.equals(((GoogleIndoorBuildingDelegate) other).indoorBuilding));
    }

    @Override
    public String toString() {
        return indoorBuilding.toString();
    }
}
