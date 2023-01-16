package com.jch.racWiFi.weather;

import androidx.core.app.NotificationCompat;
import com.amplitude.api.Constants;
import com.jch.racWiFi.weather.model.Location;
import com.jch.racWiFi.weather.model.Weather;
import org.json.JSONException;
import org.json.JSONObject;
import p020ua.naiksoftware.stomp.dto.StompHeader;

public class JSONWeatherParser {
    public static Weather getWeather(String str) throws JSONException {
        Weather weather = new Weather();
        JSONObject jSONObject = new JSONObject(str);
        Location location = new Location();
        JSONObject object = getObject("coord", jSONObject);
        location.setLatitude(getFloat("lat", object));
        location.setLongitude(getFloat("lon", object));
        JSONObject object2 = getObject(NotificationCompat.CATEGORY_SYSTEM, jSONObject);
        location.setCountry(getString(Constants.AMP_TRACKING_OPTION_COUNTRY, object2));
        location.setSunrise((long) getInt("sunrise", object2));
        location.setSunset((long) getInt("sunset", object2));
        location.setCity(getString("name", jSONObject));
        weather.location = location;
        JSONObject jSONObject2 = jSONObject.getJSONArray("weather").getJSONObject(0);
        weather.currentCondition.setWeatherId(getInt(StompHeader.f739ID, jSONObject2));
        weather.currentCondition.setDescr(getString("description", jSONObject2));
        weather.currentCondition.setCondition(getString("main", jSONObject2));
        weather.currentCondition.setIcon(getString("icon", jSONObject2));
        JSONObject object3 = getObject("main", jSONObject);
        weather.currentCondition.setHumidity((float) getInt("humidity", object3));
        weather.currentCondition.setPressure((float) getInt("pressure", object3));
        weather.temperature.setMaxTemp(getFloat("temp_max", object3));
        weather.temperature.setMinTemp(getFloat("temp_min", object3));
        weather.temperature.setTemp(getFloat("temp", object3));
        weather.wind.setSpeed(getFloat("speed", getObject("wind", jSONObject)));
        weather.clouds.setPerc(getInt("all", getObject("clouds", jSONObject)));
        return weather;
    }

    private static JSONObject getObject(String str, JSONObject jSONObject) throws JSONException {
        return jSONObject.getJSONObject(str);
    }

    private static String getString(String str, JSONObject jSONObject) throws JSONException {
        return jSONObject.getString(str);
    }

    private static float getFloat(String str, JSONObject jSONObject) throws JSONException {
        return (float) jSONObject.getDouble(str);
    }

    private static int getInt(String str, JSONObject jSONObject) throws JSONException {
        return jSONObject.getInt(str);
    }
}
