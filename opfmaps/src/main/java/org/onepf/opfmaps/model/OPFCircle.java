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

/**
 * Created by akarimova on 10.06.15.
 */
public class OPFCircle extends OPFShape {
    private OPFLatLng center;
    private double radius;
    private int fillColor = Color.TRANSPARENT;
    private int strokeColor = Color.BLACK;
    private float strokeWidth = 10f;

    private OPFCircle() {
    }

    public OPFLatLng getCenter() {
        return center;
    }

    public void center(OPFLatLng center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void radius(double radius) {
        this.radius = radius;
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

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void strokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public static class Builder implements org.onepf.opfmaps.model.Builder<OPFCircle> {
        private OPFCircle opfCircle;

        public Builder() {
            opfCircle = new OPFCircle();
        }

        public Builder setCenter(OPFLatLng opfLatLng) {
            opfCircle.center(opfLatLng);
            return this;
        }

        public Builder setRadius(double radius) {
            opfCircle.radius(radius);
            return this;
        }

        public Builder setFillColor(int fillColor) {
            opfCircle.fillColor(fillColor);
            return this;
        }

        public Builder setStrokeColor(int strokeColor) {
            opfCircle.strokeColor(strokeColor);
            return this;
        }

        public Builder setStrokeWidth(float strokeWidth) {
            opfCircle.strokeWidth(strokeWidth);
            return this;
        }

        public Builder setzIndex(float zIndex) {
            opfCircle.zIndex(zIndex);
            return this;
        }

        public OPFCircle build() {
            if (opfCircle.center == null) {
                throw new IllegalStateException("OPFCircle: center coordinates not set!");
            }
            return opfCircle;
        }

    }

}
