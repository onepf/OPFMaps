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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by akarimova on 15.06.15.
 */
public abstract class OPFMutiPointsShape extends OPFShape {
    private List<OPFLatLng> points;
    private boolean geodesic;

    public OPFMutiPointsShape() {
        points = new ArrayList<>();
    }

    public void add(OPFLatLng point) {
        points.add(point);
    }

    public void add(OPFLatLng... points) {
        Collections.addAll(this.points, points);
    }

    public void addAll(Iterable<OPFLatLng> points) {
        for (Object point : points) {
            this.points.add((OPFLatLng) point);
        }
    }

    public List<OPFLatLng> getPoints() {
        return points;
    }


    public boolean isGeodesic() {
        return geodesic;
    }

    public void geodesic(boolean geodesic) {
        this.geodesic = geodesic;
    }

    public abstract static class Builder<T extends OPFMutiPointsShape> extends OPFShape.Builder<T>{
        public Builder<T> addPoint(OPFLatLng point){
            getShape().add(point);
            return this;
        }

        public Builder<T> addPoints(OPFLatLng... points){
            getShape().add(points);
            return this;
        }

        public Builder<T> addAllPoints(Iterable<OPFLatLng> points){
            getShape().addAll(points);
            return this;
        }

        public Builder<T> setGeodesic(boolean geodesic){
            getShape().geodesic(geodesic);
            return this;
        }
    }
}
