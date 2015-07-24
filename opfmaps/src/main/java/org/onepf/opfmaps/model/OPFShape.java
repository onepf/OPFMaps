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

/**
 * Created by akarimova on 15.06.15.
 */
public class OPFShape extends OPFMapObject {
    private float zIndex = 0.0f;

    public void zIndex(float zIndex) {
        this.zIndex = zIndex;
    }

    public float getzIndex() {
        return zIndex;
    }

    public static abstract class Builder<T extends OPFShape> implements org.onepf.opfmaps.model.Builder<T> {
        private T shape;

        public Builder() {
            this.shape = createShape();
        }

        public Builder<T> setzIndex(float zIndex) {
            shape.zIndex(zIndex);
            return this;
        }

        protected abstract T createShape();

        protected T getShape() {
            if (shape == null) {
                shape = createShape();
            }
            return shape;
        }
    }
}
