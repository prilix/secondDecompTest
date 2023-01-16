package com.jch.racWiFi.iduManagement.smartFence.model;

import android.animation.ValueAnimator;
import com.github.mikephil.charting.utils.C1030Utils;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils.LocationUtil;

public class GeoFenceLayerHolder {
    private static final boolean SMOOTH_TRANSITIONS = true;
    private static final int STROKE_COLOR = -3997649;
    private static final int STROKE_WIDTH = 4;
    private Circle circleArriving;
    private Marker circleArrivingTopInvisibleMarker;
    private Circle circleLeaving;
    private Marker circleLeavingTopInvisibleMarker;
    /* access modifiers changed from: private */
    public Marker marker;
    private ValueAnimator valueAnimatorCircle;
    private ValueAnimator valueAnimatorMarker;

    public Marker getCircleArrivingTopInvisibleMarker() {
        return this.circleArrivingTopInvisibleMarker;
    }

    public void setCircleArrivingTopInvisibleMarker(Marker marker2) {
        this.circleArrivingTopInvisibleMarker = marker2;
    }

    public Marker getCircleLeavingTopInvisibleMarker() {
        return this.circleLeavingTopInvisibleMarker;
    }

    public void setCircleLeavingTopInvisibleMarker(Marker marker2) {
        this.circleLeavingTopInvisibleMarker = marker2;
    }

    public Circle getCircleLeaving() {
        return this.circleLeaving;
    }

    public Marker getMarker() {
        return this.marker;
    }

    public Circle getCircleArriving() {
        return this.circleArriving;
    }

    public GeoFenceLayerHolder(Marker marker2, Circle circle, Circle circle2) {
        this.marker = marker2;
        this.circleArriving = circle;
        this.circleLeaving = circle2;
    }

    public GeoFencePair getGeoFencePair() {
        return (GeoFencePair) this.marker.getTag();
    }

    public void updateCenterForGeoFenceCircles(LatLng latLng, int i, int i2) {
        Circle circle = this.circleArriving;
        if (circle != null) {
            circle.setCenter(latLng);
        }
        Circle circle2 = this.circleLeaving;
        if (circle2 != null) {
            circle2.setCenter(latLng);
        }
        Marker marker2 = this.marker;
        if (marker2 != null) {
            marker2.setPosition(latLng);
        }
        Marker marker3 = this.circleArrivingTopInvisibleMarker;
        if (marker3 != null) {
            marker3.setPosition(LocationUtil.destinationPoint(latLng, (double) i, C1030Utils.DOUBLE_EPSILON));
        }
        Marker marker4 = this.circleLeavingTopInvisibleMarker;
        if (marker4 != null) {
            marker4.setPosition(LocationUtil.destinationPoint(latLng, (double) i2, C1030Utils.DOUBLE_EPSILON));
        }
    }

    public void updateArrivingCircleRadius(int i) {
        if (getCircleArriving() != null) {
            double radius = (double) ((int) getCircleArriving().getRadius());
            double d = (double) i;
            move(getCircleArriving(), radius, d);
            getCircleArriving().setStrokeColor(STROKE_COLOR);
            getCircleArriving().setStrokeWidth(4.0f);
            if (getCircleLeaving() != null) {
                getCircleLeaving().setStrokeColor(0);
            }
            Marker marker2 = this.circleArrivingTopInvisibleMarker;
            if (marker2 != null) {
                moveInfoWindow(marker2, radius, d);
            }
        }
    }

    public void updateLeavingCircleRadius(int i) {
        if (getCircleLeaving() != null) {
            double radius = (double) ((int) getCircleLeaving().getRadius());
            double d = (double) i;
            move(getCircleLeaving(), radius, d);
            getCircleLeaving().setStrokeColor(STROKE_COLOR);
            getCircleLeaving().setStrokeWidth(4.0f);
            if (getCircleArriving() != null) {
                getCircleArriving().setStrokeColor(0);
            }
            Marker marker2 = this.circleLeavingTopInvisibleMarker;
            if (marker2 != null) {
                moveInfoWindow(marker2, radius, d);
            }
        }
    }

    public void removeStrokeColorArrivingGeoFence() {
        if (getCircleArriving() != null) {
            getCircleArriving().setStrokeColor(0);
        }
    }

    public void removeStrokeColorLeavingGeoFence() {
        if (getCircleLeaving() != null) {
            getCircleLeaving().setStrokeColor(0);
        }
    }

    public void addStrokeColorArrivingGeoFence() {
        if (getCircleArriving() != null) {
            getCircleArriving().setStrokeColor(STROKE_COLOR);
            getCircleArriving().setStrokeWidth(4.0f);
        }
    }

    public void addStrokeColorLeavingGeoFence() {
        if (getCircleLeaving() != null) {
            getCircleLeaving().setStrokeColor(STROKE_COLOR);
            getCircleLeaving().setStrokeWidth(4.0f);
        }
    }

    public void move(final Circle circle, double d, double d2) {
        ValueAnimator valueAnimator = this.valueAnimatorCircle;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.valueAnimatorCircle.end();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{(int) d, (int) d2});
        this.valueAnimatorCircle = ofInt;
        ofInt.setDuration((long) 1000);
        this.valueAnimatorCircle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                circle.setRadius(Double.parseDouble(valueAnimator.getAnimatedValue().toString()));
            }
        });
        this.valueAnimatorCircle.start();
    }

    public void moveInfoWindow(final Marker marker2, double d, double d2) {
        ValueAnimator valueAnimator = this.valueAnimatorMarker;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.valueAnimatorMarker.end();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{(int) d, (int) d2});
        this.valueAnimatorMarker = ofInt;
        ofInt.setDuration((long) 1000);
        this.valueAnimatorMarker.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                marker2.setPosition(LocationUtil.destinationPoint(GeoFenceLayerHolder.this.marker.getPosition(), Double.parseDouble(valueAnimator.getAnimatedValue().toString()), C1030Utils.DOUBLE_EPSILON));
            }
        });
        this.valueAnimatorMarker.start();
    }

    public boolean notFullyDrawn() {
        return this.marker == null || this.circleArriving == null || this.circleLeaving == null;
    }

    public void clearAll() {
        Marker marker2 = this.marker;
        if (marker2 != null) {
            marker2.remove();
        }
        Circle circle = this.circleArriving;
        if (circle != null) {
            circle.remove();
        }
        Circle circle2 = this.circleLeaving;
        if (circle2 != null) {
            circle2.remove();
        }
        Marker marker3 = this.circleArrivingTopInvisibleMarker;
        if (marker3 != null) {
            marker3.remove();
        }
        Marker marker4 = this.circleLeavingTopInvisibleMarker;
        if (marker4 != null) {
            marker4.remove();
        }
    }
}
