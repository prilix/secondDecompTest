package com.jch.racWiFi.fcm.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;
import com.jch.racWiFi.p010di.util.Constants;
import java.util.HashMap;
import java.util.List;

public class FcmResponse extends BaseResponse {
    @SerializedName("body")
    private Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        @SerializedName("message")
        private String message;
        @SerializedName("result")
        private List<Result> result;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public List<Result> getResult() {
            return this.result;
        }

        public void setResult(List<Result> list) {
            this.result = list;
        }

        public static class Result {
            private String body;
            private String category;
            private HashMap<String, String> data;
            @SerializedName("id")

            /* renamed from: id */
            private String f442id;
            private Long sentTimeInMilliseconds;
            private String subCategory;
            private String title;

            public String getSubCategory() {
                String str = this.subCategory;
                if (str != null) {
                    return str.replace(Constants.FCM.DASH, Constants.FCM.UNDER_SCORE);
                }
                return null;
            }

            public String getId() {
                return this.f442id;
            }

            public void setId(String str) {
                this.f442id = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getBody() {
                return this.body;
            }

            public void setBody(String str) {
                this.body = str;
            }

            public String getCategory() {
                return this.category;
            }

            public void setCategory(String str) {
                this.category = str;
            }

            public void setSubCategory(String str) {
                this.subCategory = str;
            }

            public HashMap<String, String> getData() {
                return this.data;
            }

            public void setData(HashMap<String, String> hashMap) {
                this.data = hashMap;
            }

            public Long getSentTimeInMilliseconds() {
                return this.sentTimeInMilliseconds;
            }

            public void setSentTimeInMilliseconds(Long l) {
                this.sentTimeInMilliseconds = l;
            }

            public boolean equals(Object obj) {
                if (obj instanceof Result) {
                    return this.f442id.equals(((Result) obj).f442id);
                }
                return false;
            }
        }
    }
}
