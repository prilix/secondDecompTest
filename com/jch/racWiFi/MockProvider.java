package com.jch.racWiFi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.userManagement.model.dto.InitialAppConfigDto;
import java.util.HashMap;
import java.util.List;

public class MockProvider {
    private static final MockProvider ourInstance = new MockProvider();
    Gson gson = new GsonBuilder().create();

    public static MockProvider getInstance() {
        return ourInstance;
    }

    private MockProvider() {
    }

    public List<DetailedIduModel> getDeatailedIduListMock() {
        return (List) this.gson.fromJson("[{\"id\":1,\"vendorThingId\":\"HITACHI_1a9bbc\",\"name\":\"Home\",\"roomTemperature\":24,\"mode\":\"DE_HUMIDIFY_MANUAL\",\"iduTemperature\":24,\"humidity\":60,\"power\":\"ON\",\"relativeTemperature\":0,\"fanSpeed\":0,\"wifiTimerScheduleSetting\":0,\"buzzer\":1,\"online\":true},{\"id\":1,\"vendorThingId\":\"HITACHI_1a9b88\",\"name\":\"Bedroom\",\"roomTemperature\":24,\"mode\":\"FAN\",\"iduTemperature\":22,\"humidity\":50,\"power\":\"ON\",\"relativeTemperature\":0,\"fanSpeed\":0,\"wifiTimerScheduleSetting\":0,\"buzzer\":1,\"online\":true}]", new TypeToken<List<DetailedIduModel>>() {
        }.getType());
    }

    public HashMap<String, Boolean> getIduAccessModelMap() {
        return (HashMap) this.gson.fromJson("{\"HOME_PAGE.*\":true,\"MODE.*\":true,\"HOME.*\":true,\"ECO.*\":true,\"LOGOUT.*\":true,\"SETTINGS_USER_PREFERENCE.*\":true,\"CLEANING_MODE.*\":true,\"ENERGY_CONSUMPTION.*\":true,\"ON_OFF.*\":false,\"MY_ACCOUNT.*\":true,\"FAN.*\":true,\"WEEKLY_TIMER.*\":true,\"NOTIFICATIONS.*\":true,\"MANAGE_USERS.*\":false,\"SETTINGS_APP_LOCK.*\":true,\"TIMER.*\":true,\"AUTO_CHANGE.*\":true,\"SWING.*\":true,\"TEMPERATURE.*\":true,\"CUSTOMER_CARE.*\":true,\"SETTINGS_OUT_OF_HOME_REMINDER.*\":true,\"SERVICE_REQUEST.*\":true,\"MANAGE_DEVICES.*\":true,\"SETTINGS_LANGUAGE.*\":true,\"HELP.*\":true,\"SETTINGS.*\":true,\"MNUBO_DATA_SCIENCE_PLATFORM.*\":true,\"FEEDBACK.*\":true,\"ADD_DEVICES.*\":true,\"ADD_MEMBERS.*\":true,\"HOLIDAY_MODE.*\":true,\"INDIVIDUAL_IDU_PAGE.*\":true}", new TypeToken<HashMap<String, Boolean>>() {
        }.getType());
    }

    public HashMap<String, Boolean> getDefaultIduAccessModelMap() {
        return (HashMap) this.gson.fromJson("{\"HOME_PAGE.*\":true,\"MODE.*\":false,\"HOME.*\":true,\"LOGOUT.*\":true,\"SETTINGS_USER_PREFERENCE.*\":true,\"CLEANING_MODE.*\":false,\"ENERGY_CONSUMPTION.*\":true,\"ON_OFF.*\":false,\"MY_ACCOUNT.*\":true,\"WEEKLY_TIMER.*\":true,\"NOTIFICATIONS.*\":true,\"MANAGE_USERS.*\":true,\"SETTINGS_APP_LOCK.*\":true,\"TIMER.*\":false,\"AUTO_CHANGE.*\":false,\"SWING.*\":false,\"TEMPERATURE.*\":false,\"CUSTOMER_CARE.*\":true,\"SETTINGS_OUT_OF_HOME_REMINDER.*\":true,\"SERVICE_REQUEST.*\":true,\"MANAGE_DEVICES.*\":true,\"SETTINGS_LANGUAGE.*\":true,\"HELP.*\":true,\"SETTINGS.*\":false,\"MNUBO_DATA_SCIENCE_PLATFORM.*\":false,\"FEEDBACK.*\":true,\"ADD_DEVICES.*\":true,\"ADD_MEMBERS.*\":true,\"HOLIDAY_MODE.*\":true,\"INDIVIDUAL_IDU_PAGE.*\":true,\"ECO.*\":false,\"FAN.*\":false}", new TypeToken<HashMap<String, Boolean>>() {
        }.getType());
    }

    public InitialAppConfigDto getConfigMock() {
        return (InitialAppConfigDto) this.gson.fromJson("{\"features\":{\"8\":{\"id\":8,\"name\":\"NOTIFICATIONS\"},\"9\":{\"id\":9,\"name\":\"MODE\"},\"10\":{\"id\":10,\"name\":\"LOGOUT\"},\"11\":{\"id\":11,\"name\":\"SETTINGS\"},\"12\":{\"id\":12,\"name\":\"HOME\"},\"13\":{\"id\":13,\"name\":\"TEMPERATURE\"},\"14\":{\"id\":14,\"name\":\"TIMER\"},\"15\":{\"id\":15,\"name\":\"SETTINGS_LANGUAGE\"},\"16\":{\"id\":16,\"name\":\"SETTINGS_APP_LOCK\"},\"17\":{\"id\":17,\"name\":\"SETTINGS_USER_PREFERENCE\"},\"18\":{\"id\":18,\"name\":\"HOME_PAGE\"},\"19\":{\"id\":19,\"name\":\"ADD_MEMBERS\"},\"20\":{\"id\":20,\"name\":\"ADD_DEVICES\"},\"21\":{\"id\":21,\"name\":\"INDIVIDUAL_IDU_PAGE\"},\"22\":{\"id\":22,\"name\":\"ON_OFF\"},\"23\":{\"id\":23,\"name\":\"FAN\"},\"24\":{\"id\":24,\"name\":\"SWING\"},\"25\":{\"id\":25,\"name\":\"ECO\"},\"26\":{\"id\":26,\"name\":\"AUTO_CHANGE\"},\"27\":{\"id\":27,\"name\":\"CLEANING_MODE\"},\"28\":{\"id\":28,\"name\":\"WEEKLY_TIMER\"},\"29\":{\"id\":29,\"name\":\"MNUBO_DATA_SCIENCE_PLATFORM\"},\"30\":{\"id\":30,\"name\":\"SETTINGS_OUT_OF_HOME_REMINDER\"},\"31\":{\"id\":31,\"name\":\"WEEKLY_TIMER\"},\"32\":{\"id\":32,\"name\":\"HOLIDAY_MODE\"},\"33\":{\"id\":33,\"name\":\"MANAGE_USERS\"},\"34\":{\"id\":34,\"name\":\"MANAGE_DEVICES\"},\"35\":{\"id\":35,\"name\":\"ENERGY_CONSUMPTION\"},\"36\":{\"id\":36,\"name\":\"CUSTOMER_CARE\"},\"37\":{\"id\":37,\"name\":\"SERVICE_REQUEST\"},\"38\":{\"id\":38,\"name\":\"MY_ACCOUNT\"},\"39\":{\"id\":39,\"name\":\"FEEDBACK\"},\"40\":{\"id\":40,\"name\":\"HELP\"}},\"roles\":{\"1\":{\"id\":1,\"name\":\"OWNER\",\"level\":1},\"2\":{\"id\":2,\"name\":\"MEMBER\",\"level\":2},\"3\":{\"id\":3,\"name\":\"CHILD\",\"level\":3},\"4\":{\"id\":4,\"name\":\"MANAGEMENT\",\"level\":0}}}", InitialAppConfigDto.class);
    }

    public UserInfo getUserInfoMock() {
        return (UserInfo) this.gson.fromJson("{\"firstName\":\"Ross\",\"lastName\":Eve,\"settings\":{\"outOfHomeAddress\":NY,\"sensitiveToCold\":false,\"temperatureUnit\":\"degF\",\"outOfHomeLongitude\":77.6370733,\"homeOnWeekdays\":false,\"language\":\"en\",\"outOfHomeRadius\":0,\"homeOnWeekends\":false,\"outOfHomeRemainderEnabled\":true,\"outOfHomeLatitude\":12.9554982},\"profilePicture\":null,\"address\":{\"zipCode\":560071,\"city\":Banglore,\"street\":Domlur,\"state\":Karnataka,\"addressLine\":37},\"phoneNumber\":\"+8801711927841\",\"roles\":[{\"level\":1,\"name\":\"OWNER\",\"id\":2}],\"middleName\":null,\"id\":3,\"family\":2,\"email\":\"ross.eve@gmail.com\"}", UserInfo.class);
    }
}
