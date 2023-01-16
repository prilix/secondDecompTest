package com.jch.racWiFi.gooleMaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.jch.racWiFi.GenericFragment;
import com.jch_hitachi.aircloudglobal.R;

public class GenericGoogleMapsFragment extends GenericFragment {
    private GoogleMap googleMap;
    private MapView mMapView;

    public MapView getMapView() {
        return this.mMapView;
    }

    public void setGoogleMap(GoogleMap googleMap2) {
        this.googleMap = googleMap2;
    }

    public GoogleMap getGoogleMap() {
        return this.googleMap;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_map_fragment, viewGroup, false);
        MapView mapView = (MapView) inflate.findViewById(R.id.mapView);
        this.mMapView = mapView;
        mapView.onCreate(bundle);
        try {
            MapsInitializer.initialize(requireActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) viewGroup.findViewById(R.id.mapview_holder);
        constraintLayout.removeAllViews();
        constraintLayout.addView(inflate);
        return viewGroup;
    }

    public void onResume() {
        super.onResume();
        this.mMapView.onResume();
    }

    public void onPause() {
        super.onPause();
        this.mMapView.onPause();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mMapView.onDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mMapView.onLowMemory();
    }
}
