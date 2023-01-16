package com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils;

import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import com.google.android.gms.maps.MapView;

public class GoogleMapUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String TAG = "GoogleMapUtil";

    public static void moveCompassButton(View view) {
        try {
            View findViewWithTag = view.findViewWithTag("GoogleMapMyLocationButton");
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(11, -1);
            layoutParams.addRule(21, -1);
            layoutParams.addRule(12);
            layoutParams.setMargins(0, 0, 32, 32);
            findViewWithTag.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveZoomControls(MapView mapView) {
        View findViewById = mapView.findViewById(1);
        if (findViewById != null && (findViewById.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.addRule(12, -1);
            layoutParams.addRule(20, -1);
            layoutParams.addRule(9, -1);
            int applyDimension = (int) TypedValue.applyDimension(1, 32.0f, mapView.getContext().getResources().getDisplayMetrics());
            layoutParams.setMargins(applyDimension, 0, 0, applyDimension);
        }
    }
}
