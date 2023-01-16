package com.jch.racWiFi.iduManagement.smartFence.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.utils.C1030Utils;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jch.racWiFi.gooleMaps.GenericGoogleMapsFragment;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils.LocationUtil;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceLayerHolder;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch_hitachi.aircloudglobal.R;

public class GenericGeoFenceFragment extends GenericGoogleMapsFragment {
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int i) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            drawable.setBounds(0, 0, 24, 24);
            Bitmap createBitmap = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
            drawable.draw(new Canvas(createBitmap));
            return BitmapDescriptorFactory.fromBitmap(createBitmap);
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        Bitmap createBitmap2 = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        drawable.draw(new Canvas(createBitmap2));
        return BitmapDescriptorFactory.fromBitmap(createBitmap2);
    }

    private MarkerOptions createMarker(GeoFencePair geoFencePair) {
        return new MarkerOptions().position(geoFencePair.getLatLng()).icon(bitmapDescriptorFromVector(requireActivity(), geoFencePair.getArrivingGeoFence().getGeoFenceUiElements().getMarkerIconRes())).draggable(geoFencePair.isDraggable());
    }

    private MarkerOptions createMarkerInvisibleArriving(GeoFencePair geoFencePair) {
        return new MarkerOptions().position(LocationUtil.destinationPoint(geoFencePair.getLatLng(), (double) geoFencePair.getArrivingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().floatValue(), C1030Utils.DOUBLE_EPSILON)).icon(bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_baseline_location_on_0_1)).draggable(geoFencePair.isDraggable());
    }

    private MarkerOptions createMarkerInvisibleLeaving(GeoFencePair geoFencePair) {
        return new MarkerOptions().position(LocationUtil.destinationPoint(geoFencePair.getLatLng(), (double) geoFencePair.getLeavingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().floatValue(), C1030Utils.DOUBLE_EPSILON)).icon(bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_baseline_location_on_0_1)).draggable(geoFencePair.isDraggable());
    }

    private CircleOptions createGeoFenceCircleArriving(GeoFencePair geoFencePair) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(geoFencePair.getLatLng());
        circleOptions.radius((double) geoFencePair.getArrivingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().floatValue());
        circleOptions.fillColor(getResources().getColor(geoFencePair.getArrivingGeoFence().getGeoFenceUiElements().getFillColor()));
        circleOptions.strokeColor(getResources().getColor(geoFencePair.getArrivingGeoFence().getGeoFenceUiElements().getBorderColor()));
        circleOptions.strokeWidth(6.0f);
        return circleOptions;
    }

    private CircleOptions createGeoFenceCircleLeaving(GeoFencePair geoFencePair) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(geoFencePair.getLatLng());
        circleOptions.radius((double) geoFencePair.getLeavingGeoFence().getGeoFenceDynamics().getGeoFenceRadiusInMeters().floatValue());
        circleOptions.fillColor(getResources().getColor(geoFencePair.getLeavingGeoFence().getGeoFenceUiElements().getFillColor()));
        circleOptions.strokeColor(getResources().getColor(geoFencePair.getLeavingGeoFence().getGeoFenceUiElements().getBorderColor()));
        circleOptions.strokeWidth(6.0f);
        return circleOptions;
    }

    public GeoFenceLayerHolder drawGeoFence(GeoFencePair geoFencePair) {
        Circle circle;
        Circle circle2 = null;
        if (geoFencePair.getLatLng() == null) {
            return null;
        }
        if (geoFencePair.getArrivingGeoFence().getGeoFenceDynamics().isGeoFenceEnabled()) {
            circle = drawCircle(createGeoFenceCircleArriving(geoFencePair));
            circle.setTag(geoFencePair);
        } else {
            circle = null;
        }
        if (geoFencePair.getLeavingGeoFence().getGeoFenceDynamics().isGeoFenceEnabled()) {
            circle2 = drawCircle(createGeoFenceCircleLeaving(geoFencePair));
            circle2.setTag(geoFencePair);
        }
        Marker drawMarker = drawMarker(createMarker(geoFencePair));
        drawMarker.setTag(geoFencePair);
        GeoFenceLayerHolder geoFenceLayerHolder = new GeoFenceLayerHolder(drawMarker, circle, circle2);
        Marker drawMarker2 = drawMarker(createMarkerInvisibleArriving(geoFencePair));
        drawMarker2.setTag(geoFencePair);
        geoFenceLayerHolder.setCircleArrivingTopInvisibleMarker(drawMarker2);
        Marker drawMarker3 = drawMarker(createMarkerInvisibleLeaving(geoFencePair));
        drawMarker3.setTag(geoFencePair);
        geoFenceLayerHolder.setCircleLeavingTopInvisibleMarker(drawMarker3);
        return geoFenceLayerHolder;
    }

    private Marker drawMarker(MarkerOptions markerOptions) {
        return getGoogleMap().addMarker(markerOptions);
    }

    private Circle drawCircle(CircleOptions circleOptions) {
        return getGoogleMap().addCircle(circleOptions);
    }
}
