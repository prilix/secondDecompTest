package com.jch.racWiFi.iduOnBoarding.directOnboarding;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class DirectOnBoardingModel implements Parcelable {
    public static final Parcelable.Creator<DirectOnBoardingModel> CREATOR = new Parcelable.Creator<DirectOnBoardingModel>() {
        public DirectOnBoardingModel createFromParcel(Parcel parcel) {
            return new DirectOnBoardingModel(parcel);
        }

        public DirectOnBoardingModel[] newArray(int i) {
            return new DirectOnBoardingModel[i];
        }
    };
    public static final String PARCEL_KEY = "DirectOnBoardingModel_PARCEL_KEY";
    private static List<DirectOnBoardingModel> directOnBoardingModelList = new ArrayList();
    private String qrString;
    private String vendorThing;

    public int describeContents() {
        return 0;
    }

    public DirectOnBoardingModel(String str, String str2) {
        this.qrString = str;
        this.vendorThing = str2;
    }

    public DirectOnBoardingModel(Parcel parcel) {
        this.qrString = parcel.readString();
        this.vendorThing = parcel.readString();
    }

    public String getQrString() {
        return this.qrString;
    }

    public String getVendorThing() {
        return this.vendorThing;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.qrString);
        parcel.writeString(this.vendorThing);
    }

    public static List<DirectOnBoardingModel> getDirectOnBoardingModelList() {
        if (directOnBoardingModelList.isEmpty()) {
            generateDirectOnBoardingModelLsit();
        }
        return directOnBoardingModelList;
    }

    private static void generateDirectOnBoardingModelLsit() {
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-801a9bbb/pass=a8d2c3523b/type=0", "JCH-801a9bbb"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-886000cc/pass=86k2aa7854/type=0", "JCH-886000cc"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JcH-801a9bd3/pass=a8d2ab5L53/type=0", "JcH-801a9bd3"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-88600585/pass=862df17S0d/type=1", "JCH-88600585"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-886005b9/pass=862dbd7841/type=1", "JCH-886005b9"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-886004c4/pass=862eb27849/type=1", "JCH-886004c4"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-8860006b/pass=86320b7af3/type=1", "JCH-8860006b"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-88600151/pass=8631c578d9/type=1", "JCH-88600151"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-88600161/pass=8631r578e9/type=1", "JCH-88600161"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-8860038a/pass=812fec7812/type=2", "JCH-8860038a"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-886003ba/pass=8d2fbc7842/type=2", "JCH-886003ba"));
        directOnBoardingModelList.add(new DirectOnBoardingModel("ssid=JCH-88600226/pass=863050B8ae/type=2", "JCH-88600226"));
    }
}
