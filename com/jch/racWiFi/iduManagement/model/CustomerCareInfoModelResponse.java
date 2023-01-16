package com.jch.racWiFi.iduManagement.model;

import android.app.Activity;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class CustomerCareInfoModelResponse {
    @SerializedName("result")
    public ArrayList<CustomerCareInfo> customerCareInfoArrayList;
    @SerializedName("message")
    public String message;

    public enum TimeFormat {
        FORMAT_12_HRS,
        FORMAT_24_HRS
    }

    public static class WorkingDays {
        @SerializedName("from")
        public Days from;
        @SerializedName("to")

        /* renamed from: to */
        public Days f452to;
    }

    public static class WorkingTimings {
        @SerializedName("format")
        public TimeFormat format;
        @SerializedName("from")
        public String from;
        @SerializedName("to")

        /* renamed from: to */
        public String f453to;
    }

    public class CustomerCareInfo {
        @SerializedName("channel")
        private String channel;
        @SerializedName("contactNumber")
        private String[] contactNumber;
        @SerializedName("country")
        private String country;
        @SerializedName("emailId")
        private String[] emailId = null;
        @SerializedName("ïd")

        /* renamed from: id */
        private float f451id;
        @SerializedName("region")
        private String region;
        @SerializedName("websiteUrl")
        private String[] websiteUrl;
        @SerializedName("workHours")
        @Deprecated
        private String workHours;
        @SerializedName("availableDaysAndHours")
        public ArrayList<WorkingHoursModel> workingHoursModels;

        public CustomerCareInfo() {
        }

        @Deprecated
        public String getWorkHours() {
            return this.workHours;
        }

        public float getId() {
            return this.f451id;
        }

        public String getRegion() {
            return this.region;
        }

        public String getCountry() {
            return this.country;
        }

        public String getChannel() {
            return this.channel;
        }

        public String[] getContactNumber() {
            return this.contactNumber;
        }

        public String[] getEmailId() {
            return this.emailId;
        }

        public String[] getWebsiteUrl() {
            return this.websiteUrl;
        }

        public void setId(float f) {
            this.f451id = f;
        }

        public void setRegion(String str) {
            this.region = str;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public void setChannel(String str) {
            this.channel = str;
        }

        public void setContactNumber(String[] strArr) {
            this.contactNumber = strArr;
        }

        public void setEmailId(String[] strArr) {
            this.emailId = strArr;
        }

        public void setWebsiteUrl(String[] strArr) {
            this.websiteUrl = strArr;
        }
    }

    public static class WorkingHoursModel {
        @SerializedName("days")
        public WorkingDays workingDays;
        @SerializedName("timings")
        public WorkingTimings workingTimings;

        public String getWorkingHoursString(Activity activity) {
            String str;
            StringBuilder sb = new StringBuilder();
            WorkingDays workingDays2 = this.workingDays;
            if (workingDays2 != null) {
                Days days = workingDays2.from;
                Days days2 = this.workingDays.f452to;
                if (days != null) {
                    sb.append(activity.getString(days.displayResId));
                }
                if (days2 != null) {
                    sb.append(" ");
                    sb.append(activity.getString(R.string.common_lbl_To));
                    sb.append(" ");
                    sb.append(activity.getString(days2.displayResId));
                }
            }
            if (this.workingTimings != null) {
                sb.append(StringUtils.f715LF);
                String str2 = this.workingTimings.from;
                String str3 = this.workingTimings.f453to;
                String str4 = "";
                if (this.workingTimings.format == null || !this.workingTimings.format.equals(TimeFormat.FORMAT_12_HRS)) {
                    str = str4;
                } else {
                    str2 = DateAndTimeUtils.convert12HoursFormatWithoutAMandPM(this.workingTimings.from);
                    str3 = DateAndTimeUtils.convert12HoursFormatWithoutAMandPM(this.workingTimings.f453to);
                    str4 = activity.getString(R.string.common_lbl_Am);
                    str = activity.getString(R.string.common_lbl_Pm);
                }
                sb.append(str2);
                sb.append(" ");
                sb.append(str4);
                sb.append(" ");
                sb.append(Constants.FCM.DASH);
                sb.append(" ");
                sb.append(str3);
                sb.append(" ");
                sb.append(str);
            }
            return sb.toString();
        }
    }

    public enum Days {
        MON(R.string.common_lbl_Monday),
        TUE(R.string.common_lbl_Tuesday),
        WED(R.string.common_lbl_Wednesday),
        THU(R.string.common_lbl_Thursday),
        FRI(R.string.common_lbl_Friday),
        SAT(R.string.common_lbl_Saturday),
        SUN(R.string.common_lbl_Sunday);
        
        /* access modifiers changed from: private */
        public int displayResId;

        private Days(int i) {
            this.displayResId = -1;
            this.displayResId = i;
        }
    }
}
