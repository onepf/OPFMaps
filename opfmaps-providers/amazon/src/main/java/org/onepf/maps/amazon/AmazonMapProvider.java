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

package org.onepf.maps.amazon;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import com.amazon.geo.mapsv2.util.AmazonMapsRuntimeUtil;
import com.amazon.geo.mapsv2.util.ConnectionResult;
import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfmaps.listener.OPFOnIndoorStateChangeListener;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFUiSettings;
import org.onepf.opfutils.OPFLog;

/**
 * Amazon Map Provider.
 * <p/>
 * This provider doesn't support the following methods (stubs implemented):
 * <ul>
 * <ul>
 *     Markers:
 *     <li>{@link org.onepf.opfmaps.model.OPFMarker#setDraggable(boolean)}</li>
 *     <li>{@link OPFMarker#isDraggable()}</li>
 *     <li>{@link OPFMarker#setFlat(boolean)}</li>
 *     <li>{@link OPFMarker#isFlat()}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFMarkerOptions#draggable(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFMarkerOptions#flat(boolean)}</li>
 * </ul>
 * <ul>
 *     Ground overlays:
 *     <li>{@link org.onepf.opfmaps.model.OPFGroundOverlay} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFGroundOverlayOptions} all methods</li>
 * </ul>
 * <ul>
 *     Tile overlays:
 *     <li>{@link org.onepf.opfmaps.model.OPFTileOverlay} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFTileOverlayOptions} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFTileProvider} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFUrlTileProvider} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFTile} all methods</li>
 * </ul>
 * <ul>
 *     UiSettings:
 *     <li>{@link org.onepf.opfmaps.model.OPFUiSettings#setIndoorLevelPickerEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isIndoorLevelPickerEnabled()}</li>
 * </ul>
 * <ul>
 *     Map:
 *     <li>{@link org.onepf.opfmaps.OPFMap#addGroundOverlay(org.onepf.opfmaps.model.OPFGroundOverlayOptions)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#setOnMarkerDragListener(org.onepf.opfmaps.listener.OPFOnMarkerDragListener)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#addTileOverlay(org.onepf.opfmaps.model.OPFTileOverlayOptions)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#setIndoorEnabled(boolean)}</li>
 *     <li>{@link OPFMap#isIndoorEnabled()}</li>
 *     <li>{@link OPFMap#setOnIndoorStateChangeListener(OPFOnIndoorStateChangeListener)}</li>
 *     <li>{@link OPFMap#isBuildingsEnabled()}</li>
 *     <li>{@link OPFMap#isIndoorEnabled()}</li>
 *     <li>{@link OPFMap#getFocusedBuilding()}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#liteMode(boolean)}</li>
 * </ul>
 * <p/>
 * </ul>
 *
 * @author Anastasiia Karimova
 * @since 24.06.2015
 */
public class AmazonMapProvider extends BaseOPFMapProvider {

    private static final String HOST_APP_PACKAGE = "com.amazon.venezia";
    private static final String AMAZON_MANUFACTURER = "Amazon";

    public AmazonMapProvider() {
        super(AmazonMapProvider.class.getSimpleName(), HOST_APP_PACKAGE);
    }

    @NonNull
    @Override
    public DelegatesAbstractFactory getDelegatesFactory() {
        return new AmazonDelegatesFactory();
    }

    @Override
    public boolean isAvailable(@NonNull final Context context) {
        if (super.isAvailable(context)) {
            int availabilityResult = AmazonMapsRuntimeUtil.isAmazonMapsRuntimeAvailable(context);
            if (availabilityResult == ConnectionResult.SUCCESS) {
                return Build.MANUFACTURER.equals(AMAZON_MANUFACTURER);
            } else {
                OPFLog.d(AmazonMapsRuntimeUtil.getErrorString(availabilityResult));
            }
        }
        return false;
    }
}
