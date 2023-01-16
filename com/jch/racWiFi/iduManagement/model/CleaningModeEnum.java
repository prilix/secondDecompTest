package com.jch.racWiFi.iduManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.jch_hitachi.aircloudglobal.R;

public enum CleaningModeEnum {
    FROST_WASH;
    
    public static final String CLEAN_FAN_COMMAND_SUCCESS = "CLEAN_FILTER_COMMAND_SUCCESS";
    public static final String CLEAN_FILTER_COMMAND_SUCCESS = "CLEAN_FILTER_COMMAND_SUCCESS";
    public static final String CLEAN_MOLD_COMMAND_SUCCESS = "CLEAN_FILTER_COMMAND_SUCCESS";
    public static final String FROST_WASH_COMMAND_SUCCESS = "FROST_WASH_COMMAND_SUCCESS";
    public static final String ODU_FROST_WASH_COMMAND_SUCCESS = "ODU_FROST_WASH_COMMAND_SUCCESS";
    public static final String PARCEL_KEY = "CleaningModeEnum";

    /* renamed from: com.jch.racWiFi.iduManagement.model.CleaningModeEnum$1 */
    static /* synthetic */ class C18471 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$CleaningModeEnum = null;

        static {
            int[] iArr = new int[CleaningModeEnum.values().length];
            $SwitchMap$com$jch$racWiFi$iduManagement$model$CleaningModeEnum = iArr;
            try {
                iArr[CleaningModeEnum.FROST_WASH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public CleaningModeDisplayInfoModel getCleaningModeDisplayInfoModel() {
        CleaningModeDisplayInfoModel cleaningModeDisplayInfoModel = new CleaningModeDisplayInfoModel();
        if (C18471.$SwitchMap$com$jch$racWiFi$iduManagement$model$CleaningModeEnum[ordinal()] != 1) {
            return null;
        }
        cleaningModeDisplayInfoModel.warning = R.string.idu_lbl_frostWashIndoorDesc;
        cleaningModeDisplayInfoModel.heading = R.string.notification_lbl_frostWashIndoor;
        cleaningModeDisplayInfoModel.subTitle = R.string.idu_lbl_frostWashIndoorIsComplete;
        cleaningModeDisplayInfoModel.subTitleBottom = R.string.idu_lbl_frostWashIndoorComplete;
        cleaningModeDisplayInfoModel.image = R.drawable.ic_idu_frost_wash_svg;
        cleaningModeDisplayInfoModel.cleanButtonStringResource = R.string.idu_btn_startFrostWashIndoor;
        cleaningModeDisplayInfoModel.progressBottomSubTitle = R.string.idu_lbl_frostWashIndoorInProgress;
        return cleaningModeDisplayInfoModel;
    }

    public static class CleaningModeDisplayInfoModel implements Parcelable {
        public static final Parcelable.Creator<CleaningModeDisplayInfoModel> CREATOR = null;
        public static final String PARCEL_KEY = "CleaningModeDisplayInfoModel";
        public int cleanButtonStringResource;
        public int cleaningDoneMainTitle;
        public int cleaningDoneSubTitle;
        public int heading;
        public int image;
        public int progressBottomSubTitle;
        public int subTitle;
        public int subTitleBottom;
        public int warning;

        public int describeContents() {
            return 0;
        }

        public CleaningModeDisplayInfoModel() {
        }

        protected CleaningModeDisplayInfoModel(Parcel parcel) {
            this.heading = parcel.readInt();
            this.warning = parcel.readInt();
            this.subTitle = parcel.readInt();
            this.cleaningDoneMainTitle = parcel.readInt();
            this.cleaningDoneSubTitle = parcel.readInt();
            this.progressBottomSubTitle = parcel.readInt();
        }

        static {
            CREATOR = new Parcelable.Creator<CleaningModeDisplayInfoModel>() {
                public CleaningModeDisplayInfoModel createFromParcel(Parcel parcel) {
                    return new CleaningModeDisplayInfoModel(parcel);
                }

                public CleaningModeDisplayInfoModel[] newArray(int i) {
                    return new CleaningModeDisplayInfoModel[i];
                }
            };
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.heading);
            parcel.writeInt(this.warning);
            parcel.writeInt(this.subTitle);
            parcel.writeInt(this.cleaningDoneMainTitle);
            parcel.writeInt(this.cleaningDoneSubTitle);
            parcel.writeInt(this.progressBottomSubTitle);
        }
    }
}
