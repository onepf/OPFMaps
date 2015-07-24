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

package org.onepf.opfmaps;

/**
 * Created by akarimova on 02.07.15.
 */
public class OPFMapOptions {
    private boolean rotateGesturesEnabled;
    private boolean compassEnabled;
    private boolean tiltGesturesEnabled;
    private boolean zoomGesturesEnabled;

    public OPFMapOptions rotateGesturesEnabled(boolean enabled) {
        this.rotateGesturesEnabled = enabled;
        return this;
    }

    public OPFMapOptions compassEnabled(boolean enabled) {
        this.compassEnabled = enabled;
        return this;
    }

    public OPFMapOptions tiltGesturesEnabled(boolean enabled) {
        this.tiltGesturesEnabled = enabled;
        return this;
    }

    public OPFMapOptions zoomGesturesEnabled(boolean enabled) {
        this.zoomGesturesEnabled = enabled;
        return this;
    }

    public boolean isRotateGesturesEnabled() {
        return rotateGesturesEnabled;
    }

    public boolean isCompassEnabled() {
        return compassEnabled;
    }

    public boolean isTiltGesturesEnabled() {
        return tiltGesturesEnabled;
    }

    public boolean isZoomGesturesEnabled() {
        return zoomGesturesEnabled;
    }
}
