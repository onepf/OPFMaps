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

package org.onepf.maps.amazon.delegate.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.amazon.geo.mapsv2.model.IndoorBuilding;
import com.amazon.geo.mapsv2.model.IndoorLevel;
import org.onepf.opfmaps.delegate.model.IndoorBuildingDelegate;
import org.onepf.opfmaps.model.OPFIndoorLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class AmazonIndoorBuildingDelegate implements IndoorBuildingDelegate {

    @NonNull
    private final IndoorBuilding indoorBuilding;

    public AmazonIndoorBuildingDelegate(@NonNull final IndoorBuilding indoorBuilding) {
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
        final List<IndoorLevel> amazonIndoorLevels = indoorBuilding.getLevels();
        if (amazonIndoorLevels == null) {
            return null;
        }

        final List<OPFIndoorLevel> indoorLevels = new ArrayList<>(amazonIndoorLevels.size());
        for (IndoorLevel amazonLevel : amazonIndoorLevels) {
            indoorLevels.add(new OPFIndoorLevel(new AmazonIndoorLevelDelegate(amazonLevel)));
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
                && (other == this || other instanceof AmazonIndoorBuildingDelegate
                && indoorBuilding.equals(((AmazonIndoorBuildingDelegate) other).indoorBuilding));
    }

    @Override
    public String toString() {
        return indoorBuilding.toString();
    }
}
