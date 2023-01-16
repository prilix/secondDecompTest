package com.jch.racWiFi.fcm.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.module.base.BaseResponse;
import java.util.List;

public class UserInfo extends BaseResponse {
    @SerializedName("body")
    private Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        @SerializedName("address")
        private Address address;
        @SerializedName("email")
        private String email;
        @SerializedName("familyId")
        private int familyId;
        @SerializedName("familyName")
        private String familyName;
        @SerializedName("firstName")
        private String firstName;
        @SerializedName("id")

        /* renamed from: id */
        private int f447id;
        @SerializedName("lastName")
        private String lastName;
        @SerializedName("middleName")
        private String middleName;
        @SerializedName("phoneNumber")
        private String phoneNumber;
        @SerializedName("pictureData")
        private PictureData pictureData;
        @SerializedName("roles")
        private List<Role> roles;
        @SerializedName("settings")
        private Settings settings;

        public int getFamilyId() {
            return this.familyId;
        }

        public void setFamilyId(int i) {
            this.familyId = i;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public void setFirstName(String str) {
            this.firstName = str;
        }

        public String getLastName() {
            return this.lastName;
        }

        public void setLastName(String str) {
            this.lastName = str;
        }

        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        public void setPhoneNumber(String str) {
            this.phoneNumber = str;
        }

        public String getFamilyName() {
            return this.familyName;
        }

        public void setFamilyName(String str) {
            this.familyName = str;
        }

        public String getMiddleName() {
            return this.middleName;
        }

        public void setMiddleName(String str) {
            this.middleName = str;
        }

        public int getId() {
            return this.f447id;
        }

        public void setId(int i) {
            this.f447id = i;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public Settings getSettings() {
            return this.settings;
        }

        public void setSettings(Settings settings2) {
            this.settings = settings2;
        }

        public Address getAddress() {
            return this.address;
        }

        public void setAddress(Address address2) {
            this.address = address2;
        }

        public PictureData getPictureData() {
            return this.pictureData;
        }

        public void setPictureData(PictureData pictureData2) {
            this.pictureData = pictureData2;
        }

        public List<Role> getRoles() {
            return this.roles;
        }

        public void setRoles(List<Role> list) {
            this.roles = list;
        }
    }
}
