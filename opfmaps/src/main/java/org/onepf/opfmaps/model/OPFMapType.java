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

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public enum OPFMapType {

    /**
     * No base map tiles.
     */
    NONE(0),

    /**
     * Basic maps.
     */
    NORMAL(1),

    /**
     * Satellite maps with no labels.
     */
    SATELLITE(2),

    /**
     * Terrain maps.
     */
    TERRAIN(3),

    /**
     * Satellite maps with a transparent layer of major streets.
     */
    HYBRID(4);

    private final int id;

    OPFMapType(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //CHECKSTYLE:OFF
    @NonNull
    public static OPFMapType fromId(final int id) {
        switch (id) {
            case 0:
                return NONE;
            case 1:
                return NORMAL;
            case 2:
                return SATELLITE;
            case 3:
                return TERRAIN;
            case 4:
                return HYBRID;
            default:
                throw new IllegalArgumentException("Wrong map type");
        }
    }
    //CHECKSTYLE:ON
}
