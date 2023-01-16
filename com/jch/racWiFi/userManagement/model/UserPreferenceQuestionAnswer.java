package com.jch.racWiFi.userManagement.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.JsonObject;
import com.jch.racWiFi.IGenericModelData;

public class UserPreferenceQuestionAnswer implements Parcelable, IGenericModelData<String> {
    public static final Parcelable.Creator<UserPreferenceQuestionAnswer> CREATOR = new Parcelable.Creator<UserPreferenceQuestionAnswer>() {
        public UserPreferenceQuestionAnswer createFromParcel(Parcel parcel) {
            return new UserPreferenceQuestionAnswer(parcel);
        }

        public UserPreferenceQuestionAnswer[] newArray(int i) {
            return new UserPreferenceQuestionAnswer[i];
        }
    };
    public static final int MAX_QUESTIONS = 3;
    public static final String USER_PREFERENCE_QUESTION_KEY = "userPrefQuestion";
    private Answer answer;
    private String question;

    public enum Answer {
        NO,
        YES
    }

    public int describeContents() {
        return 0;
    }

    public String getJsonKey() {
        return USER_PREFERENCE_QUESTION_KEY;
    }

    public UserPreferenceQuestionAnswer() {
    }

    public UserPreferenceQuestionAnswer(Parcel parcel) {
        this.question = parcel.readString();
        this.answer = Answer.values()[parcel.readInt()];
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.question);
        parcel.writeInt(this.answer.ordinal());
    }

    public Answer getAnswer() {
        return this.answer;
    }

    public void setAnswer(Answer answer2) {
        this.answer = answer2;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String str) {
        this.question = str;
    }

    public String getJsonValue() {
        return this.question;
    }

    public void importFromJson(JsonObject jsonObject) {
        this.question = jsonObject.get(USER_PREFERENCE_QUESTION_KEY).getAsString();
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(getJsonKey(), getJsonValue());
        return jsonObject;
    }
}
