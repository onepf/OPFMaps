# OPFMaps

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-OPFMaps-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2622)

OPFMaps is an open source library which provides an easy way for developers to integrate different android maps into their apps.

Currently OPFMaps supports the following maps: [Google Maps][google-maps], [Amazon Maps][amazon-maps], [Osmdroid][osmdroid], [Yandex Maps JavaScript API][yandex-web-maps].

##How To Use

**Add dependencies**

The main dependencies are the `opfmaps` module and the [OPFUtils][opfutils] library:

```gradle
compile 'com.github.siekierskip.OPFMaps:opfmaps:v0.1.6'
compile 'org.onepf:opfutils:0.1.26'
```
Then you have to add at least one map provider dependency.

*Google Maps Provider*:

```gradle
compile 'com.github.siekierskip.OPFMaps:google:v0.1.6'
compile 'com.google.android.gms:play-services-maps:10.0.1'
```

*NOTE:* Also for Google Maps you must add [Maps API Key][google-api-key] to the AndroidManifest.xml file.

*Amazon Maps Provider*:

```gradle
compile 'com.github.siekierskip.OPFMaps:amazon:v0.1.6'
compile 'com.amazon:amazon-maps-api:2.0'
```

*Osmdroid Provider*:

```gradle
compile 'com.github.siekierskip.OPFMaps:osmdroid:v0.1.6'
compile 'org.osmdroid:osmdroid-android:5.6.4'
compile 'com.github.MKergall:osmbonuspack:6.3'
compile 'org.slf4j:slf4j-android:1.7.12'
compile 'org.apache.commons:commons-lang3:3.4'
compile 'com.google.code.gson:gson:2.3.1'
```

If you use *Amazon Maps* or/and *Osmdroid* you have to add the following repo which hosts `amazon-maps-api` and `osmdroid-bounspack` jars:

```gradle
allprojects {
  repositories {
    ...
    // third-party dependencies
    maven { url 'https://raw.githubusercontent.com/onepf/OPF-mvn-repo/master/' }
    maven { url "https://jitpack.io" }
  }
}
```

*Yandex Maps Provider*:

```gradle
compile 'com.github.siekierskip.OPFMaps:yandex:v0.1.6'
```


**Initialization**

You must init `OPFMapsHelper` before using OPFMaps. You must do it in the `Main Thread`. `Application.onCreate()` method is the best place for this.

```java
public class MyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    OPFLog.setEnabled(BuildConfig.DEBUG, true); //Optional. It enables debug logs of the OPFMaps library in the debug build of your apk.
    
    final OPFMapConfiguration configuration = new OPFMapConfiguration.Builder()
                .addProviders(new YaWebMapProvider(), new OsmdroidMapProvider(), new GoogleMapProvider(), new AmazonMapProvider()) //Add all providers. The priority of the providers corresponds to the order in which they were added.
                .setSelectSystemPreferred(true) //If you set true, the system push provider will get the highest priority. Default value is false.
                .build(); 
    
    OPFMapHelper.getInstance().init(this, configuration);
  }
}
```

**Using**

Add the `main.xml` file which contains a `<fragment>` element:

```xml
<fragment
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:map="http://schemas.android.com/apk/res-auto"
  android:id="@+id/map"
  android:name="org.onepf.opfmaps.OPFMapFragment"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  map:opf_cameraTargetLat="37.7"
  map:opf_cameraTargetLng="-122.4"
  map:opf_mapType="hybrid"/>
```

Get `OPFMap` object in your activity:

```java
public class MainActivity extends Activity implements OnMapReadyCallback {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    final OPFMapFragment mapFragment = (OPFMapFragment) getFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }
  
  @Override
  public void onMapReady(@NonNull final OPFMap opfMap) {
    opfMap.addMarker(new OPFMarkerOptions()
      .position(new OPFLatLng(0, 0))
      .title("Marker"));
  }
}
```

You can use `OPFMapFragment`/`OPFSupportMapFragment` or `OPFMapView` to obtain the instance of `OPFMap` class (The main Maps API class).

##More Information

The OPFMaps library delegates all methods invokes to the specific map provider which is selected during initialization. It has almost the same API as Google Maps Android API v2. So for more information see [Google instructions][google-instructions].

##Restrictions

The OPFMaps library provides all methods which are provided by Google Maps. Not all map providers support whole API. For example Yandex Web Provider doesn't support rotation and tilt gestures and Amazon Map Provider doesn't support draggable markers. See Javadoc of each `OPFMapProvider` before using to know which methods are stubbed by the specific provider.

## License

    Copyright 2012-2015 One Platform Foundation

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
    
[google-maps]: https://developers.google.com/maps/documentation/android-api
[amazon-maps]: https://developer.amazon.com/public/apis/experience/maps
[osmdroid]: https://github.com/osmdroid/osmdroid
[yandex-web-maps]: https://tech.yandex.ru/maps/jsapi
[google-api-key]: https://developers.google.com/maps/documentation/android-api/signup
[google-instructions]: https://developers.google.com/maps/documentation/android-api/intro
[opfutils]: https://github.com/onepf/OPFUtils
