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
public class OPFGroundOverlay {
    private float anchorU;
    private float anchorV;
    private float bearing;
    private boolean visible;
    private float transparency;
    private float zIndex;


    public OPFGroundOverlay() {
//        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
//        groundOverlayOptions.anchor(); //float u, float v
//        groundOverlayOptions.bearing(); //float bearing
//        groundOverlayOptions.image(); //bitmap descriptor
//        groundOverlayOptions.position();
//        groundOverlayOptions.visible(); //boolean
//        groundOverlayOptions.transparency();//float
//        groundOverlayOptions.zIndex();//float
    }

    public float getAnchorU() {
        return anchorU;
    }


    public void anchor(float anchorU, float anchorV){
        this.anchorU = anchorU;
        this.anchorV = anchorV;
    }

    public float getAnchorV() {
        return anchorV;
    }

    public float getBearing() {
        return bearing;
    }

    public void bearing(float bearing) {
        this.bearing = bearing;
    }

    public boolean isVisible() {
        return visible;
    }

    public void visible(boolean visible) {
        this.visible = visible;
    }

    public float getTransparency() {
        return transparency;
    }

    public void transparency(float transparency) {
        this.transparency = transparency;
    }

    public float getzIndex() {
        return zIndex;
    }

    public void zIndex(float zIndex) {
        this.zIndex = zIndex;
    }
}
