package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.gson.annotations.SerializedName;

public class SmartFenceErrorResponse {
    public static final String FAMILY_ID_NOT_FOUND_IN_DB = "NFE026";
    public static final String KII_USER_NOT_FOUND = "NFE027";
    public static final String LOCATION_CONTROL_IS_DISABLE_FOR_USER = "PCF019";
    public static final String NO_MAPPING_FOR_THE_USER_LOCATION_CONTROL = "NFE023";
    public static final String RAC_DOES_NO_SUPPORT_SMART_FENCE = "PCF020";
    public static final String RAC_IS_NOT_MAPPED_WITH_FAMILY = "NFE025";
    public static final String RAC_NOT_FOUND_IN_DB = "NFE028";
    public static final String USER_IS_NOT_ASSOCIATED_WITH_RACS = "NFE024";
    @SerializedName("code")
    public String code;
    @SerializedName("details")
    public String details;
    @SerializedName("message")
    public String message;
    public int statusCode;
    @SerializedName("time")
    public String time;
}
