package com.jch.racWiFi;

import android.os.Parcel;
import android.os.Parcelable;

public class HelpWebPageModel implements Parcelable {
    public static final Parcelable.Creator<HelpWebPageModel> CREATOR = new Parcelable.Creator<HelpWebPageModel>() {
        public HelpWebPageModel createFromParcel(Parcel parcel) {
            return new HelpWebPageModel(parcel);
        }

        public HelpWebPageModel[] newArray(int i) {
            return new HelpWebPageModel[i];
        }
    };
    public static final HelpWebPageModel IDU_HELP_PAGE;
    public static final String IDU_PARCEL_KEY = "IduParcelKey";
    public static final HelpWebPageModel MAIN_HELP_PAGE;
    public static final String PARCEL_KEY = "HelpWebPageModel";
    public HelpType helpType;
    public String url;

    public enum HelpType {
        GENERAL,
        IDU_SPECIFIC
    }

    public int describeContents() {
        return 0;
    }

    static {
        HelpWebPageModel helpWebPageModel = new HelpWebPageModel();
        MAIN_HELP_PAGE = helpWebPageModel;
        helpWebPageModel.url = "https://kadenfan.hitachi.co.jp/ra/app2/index.html";
        helpWebPageModel.helpType = HelpType.GENERAL;
        HelpWebPageModel helpWebPageModel2 = new HelpWebPageModel();
        IDU_HELP_PAGE = helpWebPageModel2;
        helpWebPageModel2.url = "https://kadenfan.hitachi.co.jp/ra/app2/index.html";
        helpWebPageModel2.helpType = HelpType.IDU_SPECIFIC;
    }

    public HelpWebPageModel() {
    }

    protected HelpWebPageModel(Parcel parcel) {
        this.url = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
    }
}
