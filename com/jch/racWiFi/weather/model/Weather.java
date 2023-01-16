package com.jch.racWiFi.weather.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.jch.racWiFi.weather.WeatherIconsFactory;

public class Weather implements Parcelable {
    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        public Weather createFromParcel(Parcel parcel) {
            return new Weather(parcel);
        }

        public Weather[] newArray(int i) {
            return new Weather[i];
        }
    };
    private static Weather CURRENT = new Weather();
    public Clouds clouds = new Clouds();
    public CurrentCondition currentCondition = new CurrentCondition();
    public byte[] iconData;
    public boolean isWeatherDataAvailable = false;
    public Location location;
    public Rain rain = new Rain();
    public Snow snow = new Snow();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();

    public int describeContents() {
        return 0;
    }

    public static Weather getCurrent() {
        return CURRENT;
    }

    public Weather() {
    }

    public void clear() {
        CURRENT = new Weather();
    }

    public static class CurrentCondition implements Parcelable {
        public static final Parcelable.Creator<CurrentCondition> CREATOR = new Parcelable.Creator<CurrentCondition>() {
            public CurrentCondition createFromParcel(Parcel parcel) {
                return new CurrentCondition(parcel);
            }

            public CurrentCondition[] newArray(int i) {
                return new CurrentCondition[i];
            }
        };
        private String condition;
        private String descr;
        private float humidity;
        private String icon;
        private float pressure;
        private int weatherIcon;
        private int weatherId;

        public int describeContents() {
            return 0;
        }

        public CurrentCondition() {
        }

        protected CurrentCondition(Parcel parcel) {
            this.weatherId = parcel.readInt();
            this.condition = parcel.readString();
            this.descr = parcel.readString();
            this.icon = parcel.readString();
            this.weatherIcon = parcel.readInt();
            this.pressure = parcel.readFloat();
            this.humidity = parcel.readFloat();
        }

        public int getWeatherIcon(boolean z) {
            if (z) {
                this.weatherIcon = WeatherIconsFactory.getWeatherIcon(this.weatherId);
            } else {
                this.weatherIcon = WeatherIconsFactory.getDisabledWeatherIcon(this.weatherId);
            }
            return this.weatherIcon;
        }

        public void copy(CurrentCondition currentCondition) {
            this.weatherId = currentCondition.weatherId;
            this.condition = currentCondition.condition;
            this.descr = currentCondition.descr;
            this.icon = currentCondition.icon;
            this.pressure = currentCondition.pressure;
            this.humidity = currentCondition.humidity;
        }

        public int getWeatherId() {
            return this.weatherId;
        }

        public void setWeatherId(int i) {
            this.weatherId = i;
        }

        public String getCondition() {
            return this.condition;
        }

        public void setCondition(String str) {
            this.condition = str;
        }

        public String getDescr() {
            return this.descr;
        }

        public void setDescr(String str) {
            this.descr = str;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public float getPressure() {
            return this.pressure;
        }

        public void setPressure(float f) {
            this.pressure = f;
        }

        public float getHumidity() {
            return this.humidity;
        }

        public void setHumidity(float f) {
            this.humidity = f;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.weatherId);
            parcel.writeString(this.condition);
            parcel.writeString(this.descr);
            parcel.writeString(this.icon);
            parcel.writeInt(this.weatherIcon);
            parcel.writeFloat(this.pressure);
            parcel.writeFloat(this.humidity);
        }
    }

    public static class Temperature implements Parcelable {
        public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
            public Temperature createFromParcel(Parcel parcel) {
                return new Temperature(parcel);
            }

            public Temperature[] newArray(int i) {
                return new Temperature[i];
            }
        };
        private float maxTemp;
        private float minTemp;
        private float temp;

        public int describeContents() {
            return 0;
        }

        public Temperature() {
        }

        protected Temperature(Parcel parcel) {
            this.temp = parcel.readFloat();
            this.minTemp = parcel.readFloat();
            this.maxTemp = parcel.readFloat();
        }

        public void copy(Temperature temperature) {
            this.temp = temperature.temp;
            this.minTemp = temperature.minTemp;
            this.maxTemp = temperature.maxTemp;
        }

        public float getTemp() {
            return this.temp;
        }

        public void setTemp(float f) {
            this.temp = f;
        }

        public float getMinTemp() {
            return this.minTemp;
        }

        public void setMinTemp(float f) {
            this.minTemp = f;
        }

        public float getMaxTemp() {
            return this.maxTemp;
        }

        public void setMaxTemp(float f) {
            this.maxTemp = f;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.temp);
            parcel.writeFloat(this.minTemp);
            parcel.writeFloat(this.maxTemp);
        }
    }

    public static class Wind implements Parcelable {
        public static final Parcelable.Creator<Wind> CREATOR = new Parcelable.Creator<Wind>() {
            public Wind createFromParcel(Parcel parcel) {
                return new Wind(parcel);
            }

            public Wind[] newArray(int i) {
                return new Wind[i];
            }
        };
        private float deg;
        private float speed;

        public int describeContents() {
            return 0;
        }

        public Wind() {
        }

        protected Wind(Parcel parcel) {
            this.speed = parcel.readFloat();
            this.deg = parcel.readFloat();
        }

        public float getSpeed() {
            return this.speed;
        }

        public void setSpeed(float f) {
            this.speed = f;
        }

        public float getDeg() {
            return this.deg;
        }

        public void setDeg(float f) {
            this.deg = f;
        }

        public void copy(Wind wind) {
            this.speed = wind.speed;
            this.deg = wind.deg;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.speed);
            parcel.writeFloat(this.deg);
        }
    }

    public static class Rain implements Parcelable {
        public static final Parcelable.Creator<Rain> CREATOR = new Parcelable.Creator<Rain>() {
            public Rain createFromParcel(Parcel parcel) {
                return new Rain(parcel);
            }

            public Rain[] newArray(int i) {
                return new Rain[i];
            }
        };
        private float ammount;
        private String time;

        public int describeContents() {
            return 0;
        }

        public Rain() {
        }

        protected Rain(Parcel parcel) {
            this.time = parcel.readString();
            this.ammount = parcel.readFloat();
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String str) {
            this.time = str;
        }

        public float getAmmount() {
            return this.ammount;
        }

        public void setAmmount(float f) {
            this.ammount = f;
        }

        public void copy(Rain rain) {
            this.time = rain.time;
            this.ammount = rain.ammount;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.time);
            parcel.writeFloat(this.ammount);
        }
    }

    public static class Snow implements Parcelable {
        public static final Parcelable.Creator<Snow> CREATOR = new Parcelable.Creator<Snow>() {
            public Snow createFromParcel(Parcel parcel) {
                return new Snow(parcel);
            }

            public Snow[] newArray(int i) {
                return new Snow[i];
            }
        };
        private float ammount;
        private String time;

        public int describeContents() {
            return 0;
        }

        public Snow() {
        }

        protected Snow(Parcel parcel) {
            this.time = parcel.readString();
            this.ammount = parcel.readFloat();
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String str) {
            this.time = str;
        }

        public float getAmmount() {
            return this.ammount;
        }

        public void setAmmount(float f) {
            this.ammount = f;
        }

        public void copy(Snow snow) {
            this.time = snow.time;
            this.ammount = snow.ammount;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.time);
            parcel.writeFloat(this.ammount);
        }
    }

    public static class Clouds implements Parcelable {
        public static final Parcelable.Creator<Clouds> CREATOR = new Parcelable.Creator<Clouds>() {
            public Clouds createFromParcel(Parcel parcel) {
                return new Clouds(parcel);
            }

            public Clouds[] newArray(int i) {
                return new Clouds[i];
            }
        };
        private int perc;

        public int describeContents() {
            return 0;
        }

        public Clouds() {
        }

        protected Clouds(Parcel parcel) {
            this.perc = parcel.readInt();
        }

        public int getPerc() {
            return this.perc;
        }

        public void setPerc(int i) {
            this.perc = i;
        }

        public void copy(Clouds clouds) {
            this.perc = clouds.perc;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.perc);
        }
    }

    protected Weather(Parcel parcel) {
        boolean z = false;
        this.isWeatherDataAvailable = parcel.readByte() != 0 ? true : z;
        this.currentCondition = (CurrentCondition) parcel.readParcelable(CurrentCondition.class.getClassLoader());
        this.temperature = (Temperature) parcel.readParcelable(Temperature.class.getClassLoader());
        this.wind = (Wind) parcel.readParcelable(Wind.class.getClassLoader());
        this.rain = (Rain) parcel.readParcelable(Rain.class.getClassLoader());
        this.snow = (Snow) parcel.readParcelable(Snow.class.getClassLoader());
        this.clouds = (Clouds) parcel.readParcelable(Clouds.class.getClassLoader());
        this.iconData = parcel.createByteArray();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isWeatherDataAvailable ? (byte) 1 : 0);
        parcel.writeParcelable(this.currentCondition, i);
        parcel.writeParcelable(this.temperature, i);
        parcel.writeParcelable(this.wind, i);
        parcel.writeParcelable(this.rain, i);
        parcel.writeParcelable(this.snow, i);
        parcel.writeParcelable(this.clouds, i);
        parcel.writeByteArray(this.iconData);
    }

    public void copy(Weather weather) {
        Location location2 = new Location();
        this.location = location2;
        location2.copy(weather.location);
        CurrentCondition currentCondition2 = new CurrentCondition();
        this.currentCondition = currentCondition2;
        currentCondition2.copy(weather.currentCondition);
        Temperature temperature2 = new Temperature();
        this.temperature = temperature2;
        temperature2.copy(weather.temperature);
        Wind wind2 = new Wind();
        this.wind = wind2;
        wind2.copy(weather.wind);
        Rain rain2 = new Rain();
        this.rain = rain2;
        rain2.copy(weather.rain);
        Snow snow2 = new Snow();
        this.snow = snow2;
        snow2.copy(weather.snow);
        Clouds clouds2 = new Clouds();
        this.clouds = clouds2;
        clouds2.copy(weather.clouds);
        this.iconData = weather.iconData;
        this.isWeatherDataAvailable = weather.isWeatherDataAvailable;
    }
}
