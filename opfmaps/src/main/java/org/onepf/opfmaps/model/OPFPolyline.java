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
 * Created by akarimova on 09.06.15.
 */
public class OPFPolyline extends OPFMutiPointsShape {
    private int color = Color.BLACK;
    private float width = 10.0f;

    private OPFPolyline() {
    }

    public int getColor() {
        return color;
    }

    public void color(int color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void width(float width) {
        this.width = width;
    }

    public static class Builder extends OPFMutiPointsShape.Builder<OPFPolyline> {

        public Builder setColor(int color) {
            getShape().color(color);
            return this;
        }


        public Builder setWidth(float width) {
            getShape().width(width);
            return this;
        }

        @Override
        public OPFPolyline build() {
            if (getShape().getPoints().size() == 0) {
                throw new IllegalStateException("OPFPolyline: no points were found");
            }
            return getShape();
        }

        @Override
        protected OPFPolyline createShape() {
            return new OPFPolyline();
        }

    }
}
