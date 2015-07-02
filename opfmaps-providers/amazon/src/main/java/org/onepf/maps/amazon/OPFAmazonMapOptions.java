package org.onepf.maps.amazon;

import android.os.Bundle;

import com.amazon.geo.mapsv2.AmazonMapOptions;
import com.amazon.geo.mapsv2.model.CameraPosition;

/**
 * Wrapper for the {@link AmazonMapOptions} class, which is final.
 */
public class OPFAmazonMapOptions {

  private AmazonMapOptions options;

  public OPFAmazonMapOptions(AmazonMapOptions options) {
    this.options = options;
  }

  public OPFAmazonMapOptions zOrderOnTop(boolean zOrderOnTop) {
    options.zOrderOnTop(zOrderOnTop);
    return this;
  }

  public OPFAmazonMapOptions useViewLifecycleInFragment(boolean useViewLifecycleInFragment) {
    options.useViewLifecycleInFragment(useViewLifecycleInFragment);
    return this;
  }

  public OPFAmazonMapOptions mapType(int mapType) {
    options.mapType(mapType);
    return this;
  }

  public OPFAmazonMapOptions camera(CameraPosition camera) {
    options.camera(camera);
    return this;
  }

  public OPFAmazonMapOptions zoomControlsEnabled(boolean enabled) {
    options.zoomControlsEnabled(enabled);
    return this;
  }

  public OPFAmazonMapOptions compassEnabled(boolean enabled) {
    options.compassEnabled(enabled);
    return this;
  }

  public OPFAmazonMapOptions scrollGesturesEnabled(boolean enabled) {
    options.scrollGesturesEnabled(enabled);
    return this;
  }

  public OPFAmazonMapOptions zoomGesturesEnabled(boolean enabled) {
    options.zoomGesturesEnabled(enabled);
    return this;
  }

  public OPFAmazonMapOptions tiltGesturesEnabled(boolean enabled) {
    options.tiltGesturesEnabled(enabled);
    return this;
  }

  public OPFAmazonMapOptions rotateGesturesEnabled(boolean enabled) {
    options.rotateGesturesEnabled(enabled);
    return this;
  }

  public OPFAmazonMapOptions liteMode(boolean enabled) {
    options.liteMode(enabled);
    return this;
  }

  public OPFAmazonMapOptions mapToolbarEnabled(boolean enabled) {
    options.mapToolbarEnabled(enabled);
    return this;
  }

  public Boolean getZOrderOnTop() {
    return options.getZOrderOnTop();
  }

  public Boolean getUseViewLifecycleInFragment() {
    return options.getUseViewLifecycleInFragment();
  }

  public int getMapType() {
    return options.getMapType();
  }

  public CameraPosition getCamera() {
    return options.getCamera();
  }

  public Boolean getZoomControlsEnabled() {
    return options.getZoomControlsEnabled();
  }

  public Boolean getCompassEnabled() {
    return options.getCompassEnabled();
  }

  public Boolean getScrollGesturesEnabled() {
    return options.getScrollGesturesEnabled();
  }

  public Boolean getZoomGesturesEnabled() {
    return options.getZoomGesturesEnabled();
  }

  public Boolean getTiltGesturesEnabled() {
    return options.getTiltGesturesEnabled();
  }

  public Boolean getRotateGesturesEnabled() {
    return options.getRotateGesturesEnabled();
  }

  public Boolean getLiteMode() {
    return options.getLiteMode();
  }

  public Boolean getMapToolbarEnabled() {
    return options.getMapToolbarEnabled();
  }

  public Bundle toBundle() {
    Bundle args = new Bundle();
    // this is internal to SupportMapFragment
    args.putParcelable("MapOptions", options);
    return args;
  }
}
