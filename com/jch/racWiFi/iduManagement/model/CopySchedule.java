package com.jch.racWiFi.iduManagement.model;

import com.google.gson.annotations.SerializedName;

public class CopySchedule {

    public static class DayWise {
        @SerializedName("fromDay")
        private String from;
        private long racId;
        @SerializedName("toDays")

        /* renamed from: to */
        private String[] f448to;

        public DayWise(long j, String str, String[] strArr) {
            this.racId = j;
            this.from = str;
            this.f448to = strArr;
        }

        public long getRacId() {
            return this.racId;
        }

        public String getFrom() {
            return this.from;
        }

        public String[] getTo() {
            return this.f448to;
        }
    }

    public static class RacWise {
        private long from;

        /* renamed from: to */
        private long[] f449to;

        public RacWise(long j, long[] jArr) {
            this.from = j;
            this.f449to = jArr;
        }

        public long getFrom() {
            return this.from;
        }

        public void setFrom(long j) {
            this.from = j;
        }

        public long[] getTo() {
            return this.f449to;
        }

        public void setTo(long[] jArr) {
            this.f449to = jArr;
        }
    }
}
