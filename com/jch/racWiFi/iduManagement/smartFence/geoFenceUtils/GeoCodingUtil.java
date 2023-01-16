package com.jch.racWiFi.iduManagement.smartFence.geoFenceUtils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.jch.racWiFi.userManagement.model.UserAddress;
import java.io.IOException;
import java.util.List;

public class GeoCodingUtil {
    public static LatLng latLngFromLocation(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public static class GeoCode {
        public static Address getAddressFromLatLong(Context context, Location location) throws IOException {
            List<Address> fromLocation = new Geocoder(context).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (fromLocation.isEmpty()) {
                return null;
            }
            return fromLocation.get(0);
        }

        public static Address getAddressFromLatLong(Context context, LatLng latLng) throws IOException {
            List<Address> fromLocation = new Geocoder(context).getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (fromLocation.isEmpty()) {
                return null;
            }
            return fromLocation.get(0);
        }
    }

    public static class Reverse {
        public static LatLng getLocationFromAddress(Context context, UserAddress userAddress) throws IOException {
            List<Address> fromLocationName = new Geocoder(context).getFromLocationName(userAddress.toString(), 1);
            Address address = fromLocationName.isEmpty() ? null : fromLocationName.get(0);
            if (address != null) {
                return new LatLng(address.getLatitude(), address.getLongitude());
            }
            return null;
        }
    }

    public static Location locationFromLatLng(LatLng latLng) {
        Location location = new Location("");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);
        return location;
    }
}
