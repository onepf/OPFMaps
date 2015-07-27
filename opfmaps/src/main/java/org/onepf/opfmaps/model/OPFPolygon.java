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

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarimova on 15.06.15.
 */
@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass") //TODO: fix this PMD issue
public final class OPFPolygon extends OPFMultiPointsShape {
    private int fillColor = Color.TRANSPARENT;
    private int strokeColor = Color.BLACK;
    private float strokeWidth = 10.0f;
    private final List<OPFLatLng> holes;

    private OPFPolygon() {
        super();
        holes = new ArrayList<>();
    }

    public int getFillColor() {
        return fillColor;
    }

    public void fillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void strokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void addHole(Iterable<OPFLatLng> points) {
        ArrayList<OPFLatLng> hole = new ArrayList<>();
        for (OPFLatLng holePoint : points) {
            hole.add(holePoint);
        }
        this.holes.addAll(hole);
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void strokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }


    public static class Builder extends OPFMultiPointsShape.Builder<OPFPolygon> {

        public Builder setFillColor(int fillColor) {
            getShape().fillColor(fillColor);
            return this;
        }

        public Builder setStrokeColor(int strokeColor) {
            getShape().strokeColor(strokeColor);
            return this;
        }

        public Builder addHole(Iterable<OPFLatLng> points) {
            getShape().addHole(points);
            return this;
        }

        @Override
        public OPFPolygon build() {
            if (getShape().getPoints().size() == 0) {
                throw new IllegalStateException("OPFPolygon: no points were found");
            }
            return getShape();
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        @Override
        protected OPFPolygon createShape() {
           return new OPFPolygon();
        }
    }

}
