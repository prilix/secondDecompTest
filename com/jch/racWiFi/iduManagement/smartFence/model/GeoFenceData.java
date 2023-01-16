package com.jch.racWiFi.iduManagement.smartFence.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch_hitachi.aircloudglobal.R;

public class GeoFenceData implements Parcelable {
    public static final Parcelable.Creator<GeoFenceData> CREATOR = new Parcelable.Creator<GeoFenceData>() {
        public GeoFenceData createFromParcel(Parcel parcel) {
            return new GeoFenceData(parcel);
        }

        public GeoFenceData[] newArray(int i) {
            return new GeoFenceData[i];
        }
    };
    private boolean draggable;
    public long familyID;
    private FenceTransitionType fenceTransitionType;
    private GeoFenceDynamics geoFenceDynamics;
    private int geoFenceIdFromServer;
    private GeoFenceUiElements geoFenceUiElements;
    private ModeTempSettings modeTempSettings;

    public int describeContents() {
        return 0;
    }

    public enum FenceTransitionType {
        ENTER(1),
        EXIT(2);
        
        private int transitionTypeForGoogleAPI;

        public int getTransitionTypeForGoogleAPI() {
            return this.transitionTypeForGoogleAPI;
        }

        private FenceTransitionType(int i) {
            this.transitionTypeForGoogleAPI = i;
        }
    }

    protected GeoFenceData(Parcel parcel) {
        boolean z = true;
        this.draggable = true;
        this.geoFenceIdFromServer = parcel.readInt();
        this.geoFenceUiElements = (GeoFenceUiElements) parcel.readParcelable(GeoFenceUiElements.class.getClassLoader());
        this.draggable = parcel.readByte() == 0 ? false : z;
        this.geoFenceDynamics = (GeoFenceDynamics) parcel.readParcelable(GeoFenceDynamics.class.getClassLoader());
        this.modeTempSettings = (ModeTempSettings) parcel.readParcelable(GeoFenceDynamics.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.geoFenceIdFromServer);
        parcel.writeParcelable(this.geoFenceUiElements, i);
        parcel.writeByte(this.draggable ? (byte) 1 : 0);
        parcel.writeParcelable(this.geoFenceDynamics, i);
        parcel.writeParcelable(this.modeTempSettings, i);
    }

    public FenceTransitionType getFenceTransitionType() {
        return this.fenceTransitionType;
    }

    public ModeTempSettings getModeTempSettings() {
        return this.modeTempSettings;
    }

    public void setModeTempSettings(ModeTempSettings modeTempSettings2) {
        this.modeTempSettings = modeTempSettings2;
    }

    public void setFenceTransitionType(FenceTransitionType fenceTransitionType2) {
        this.fenceTransitionType = fenceTransitionType2;
    }

    public void setId(int i) {
        this.geoFenceIdFromServer = i;
    }

    public void setGeoFenceUiElements(GeoFenceUiElements geoFenceUiElements2) {
        this.geoFenceUiElements = geoFenceUiElements2;
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
    }

    public void setGeoFenceDynamics(GeoFenceDynamics geoFenceDynamics2) {
        this.geoFenceDynamics = geoFenceDynamics2;
    }

    public int getId() {
        return this.geoFenceIdFromServer;
    }

    public GeoFenceUiElements getGeoFenceUiElements() {
        return this.geoFenceUiElements;
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    public GeoFenceDynamics getGeoFenceDynamics() {
        return this.geoFenceDynamics;
    }

    public GeoFenceData() {
        this.draggable = true;
        this.geoFenceDynamics = new GeoFenceDynamics();
        this.geoFenceUiElements = GeoFenceUiElements.DEFAULT;
    }

    public static class GeoFenceUiElements implements Parcelable {
        public static final Parcelable.Creator<GeoFenceUiElements> CREATOR = new Parcelable.Creator<GeoFenceUiElements>() {
            public GeoFenceUiElements createFromParcel(Parcel parcel) {
                return new GeoFenceUiElements(parcel);
            }

            public GeoFenceUiElements[] newArray(int i) {
                return new GeoFenceUiElements[i];
            }
        };
        public static final GeoFenceUiElements DEFAULT = new GeoFenceUiElements(R.color.fill_color, 17170445, R.drawable.ic_location_controls_postion);
        private int borderColor;
        private int fillColor;
        private int markerIconRes;

        public int describeContents() {
            return 0;
        }

        protected GeoFenceUiElements(Parcel parcel) {
            this.fillColor = parcel.readInt();
            this.borderColor = parcel.readInt();
            this.markerIconRes = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.fillColor);
            parcel.writeInt(this.borderColor);
            parcel.writeInt(this.markerIconRes);
        }

        public int getMarkerIconRes() {
            return this.markerIconRes;
        }

        public int getFillColor() {
            return this.fillColor;
        }

        public int getBorderColor() {
            return this.borderColor;
        }

        public GeoFenceUiElements(int i, int i2, int i3) {
            this.fillColor = i;
            this.borderColor = i2;
            this.markerIconRes = i3;
        }
    }

    public static class GeoFenceDynamics implements Parcelable {
        public static final Parcelable.Creator<GeoFenceDynamics> CREATOR = new Parcelable.Creator<GeoFenceDynamics>() {
            public GeoFenceDynamics createFromParcel(Parcel parcel) {
                return new GeoFenceDynamics(parcel);
            }

            public GeoFenceDynamics[] newArray(int i) {
                return new GeoFenceDynamics[i];
            }
        };
        private boolean geoFenceEnabled = true;
        private float geoFenceRadiusInMeters;

        public int describeContents() {
            return 0;
        }

        protected GeoFenceDynamics(Parcel parcel) {
            boolean z = true;
            this.geoFenceRadiusInMeters = parcel.readFloat();
            this.geoFenceEnabled = parcel.readByte() == 0 ? false : z;
        }

        public void setGeoFenceRadiusInMeters(float f) {
            this.geoFenceRadiusInMeters = f;
        }

        public void setGeoFenceEnabled(boolean z) {
            this.geoFenceEnabled = z;
        }

        public GeoFenceDynamics() {
        }

        public GeoFenceDynamics(float f, boolean z) {
            this.geoFenceRadiusInMeters = f;
            this.geoFenceEnabled = z;
        }

        public Float getGeoFenceRadiusInMeters() {
            return Float.valueOf(this.geoFenceRadiusInMeters);
        }

        public boolean isGeoFenceEnabled() {
            return this.geoFenceEnabled;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.geoFenceRadiusInMeters);
            parcel.writeByte(this.geoFenceEnabled ? (byte) 1 : 0);
        }
    }

    public String getGeoFenceId() {
        return this.familyID + "_" + this.geoFenceIdFromServer + "_" + this.fenceTransitionType.name();
    }

    public boolean equals(Object obj) {
        if (obj instanceof GeoFenceData) {
            return getGeoFenceId().equals(((GeoFenceData) obj).getGeoFenceId());
        }
        return false;
    }

    public GeoFenceData parcelClone() {
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        GeoFenceData createFromParcel = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    public static class ModeTempSettings implements Parcelable {
        public static final Parcelable.Creator<ModeTempSettings> CREATOR = new Parcelable.Creator<ModeTempSettings>() {
            public ModeTempSettings createFromParcel(Parcel parcel) {
                return new ModeTempSettings(parcel);
            }

            public ModeTempSettings[] newArray(int i) {
                return new ModeTempSettings[i];
            }
        };
        public String fanSpeed;
        public String fanSwing;
        public double humidity;
        public boolean isSettingsDataSet = false;
        public boolean isSettingsEnabled = true;
        public String mode = null;
        public String powerMode;
        public double relativeTemperature;
        public boolean sendNullObjectToServer;
        public double temperature = -1.0d;

        public int describeContents() {
            return 0;
        }

        protected ModeTempSettings() {
        }

        public void resetSettings() {
            this.humidity = C1030Utils.DOUBLE_EPSILON;
            this.mode = null;
            this.powerMode = null;
            this.relativeTemperature = C1030Utils.DOUBLE_EPSILON;
            this.temperature = -1.0d;
            this.fanSpeed = null;
            this.fanSwing = null;
            this.isSettingsDataSet = false;
        }

        public boolean isSettingsEnabled() {
            return this.isSettingsEnabled;
        }

        public void setSettingsEnabled(boolean z) {
            this.isSettingsEnabled = z;
        }

        protected ModeTempSettings(Parcel parcel) {
            this.humidity = (double) parcel.readInt();
            this.mode = parcel.readString();
            this.powerMode = parcel.readString();
            this.relativeTemperature = parcel.readDouble();
            this.temperature = parcel.readDouble();
            this.fanSpeed = parcel.readString();
            this.fanSwing = parcel.readString();
        }

        public String toString() {
            return "ModeTempSettings{humidity=" + this.humidity + ", mode='" + this.mode + '\'' + ", powerMode='" + this.powerMode + '\'' + ", relativeTemperature=" + this.relativeTemperature + ", temperature=" + this.temperature + ", fanSpeed='" + this.fanSpeed + '\'' + ", fanSwing='" + this.fanSwing + '\'' + ", isSettingsDataSet=" + this.isSettingsDataSet + ", isSettingsEnabled=" + this.isSettingsEnabled + '}';
        }

        public ModeTempSettings parcelClone() {
            ModeTempSettings modeTempSettings = new ModeTempSettings();
            modeTempSettings.humidity = this.humidity;
            modeTempSettings.mode = this.mode;
            modeTempSettings.powerMode = this.powerMode;
            modeTempSettings.relativeTemperature = this.relativeTemperature;
            modeTempSettings.temperature = this.temperature;
            modeTempSettings.fanSpeed = this.fanSpeed;
            modeTempSettings.fanSwing = this.fanSwing;
            modeTempSettings.isSettingsDataSet = this.isSettingsDataSet;
            modeTempSettings.isSettingsEnabled = this.isSettingsEnabled;
            return modeTempSettings;
        }

        public ModeTempSettings parcelCloneV2() {
            ModeTempSettings modeTempSettings = new ModeTempSettings();
            modeTempSettings.humidity = this.humidity;
            modeTempSettings.mode = this.mode;
            modeTempSettings.powerMode = this.powerMode;
            modeTempSettings.relativeTemperature = this.relativeTemperature;
            modeTempSettings.temperature = this.temperature;
            modeTempSettings.fanSpeed = this.fanSpeed;
            modeTempSettings.fanSwing = this.fanSwing;
            return modeTempSettings;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(this.humidity);
            parcel.writeString(this.mode);
            parcel.writeString(this.powerMode);
            parcel.writeDouble(this.relativeTemperature);
            parcel.writeDouble(this.temperature);
            parcel.writeString(this.fanSpeed);
            parcel.writeString(this.fanSwing);
            parcel.writeByte(this.isSettingsDataSet ? (byte) 1 : 0);
            parcel.writeByte(this.sendNullObjectToServer ? (byte) 1 : 0);
            parcel.writeByte(this.isSettingsEnabled ? (byte) 1 : 0);
        }
    }

    public String toString() {
        return "GeoFenceData{fenceTransitionType=" + this.fenceTransitionType + ", geoFenceIdFromServer=" + this.geoFenceIdFromServer + ", familyID=" + this.familyID + ", geoFenceUiElements=" + this.geoFenceUiElements + ", draggable=" + this.draggable + ", geoFenceDynamics=" + this.geoFenceDynamics + ", modeTempSettings=" + this.modeTempSettings + '}';
    }
}
