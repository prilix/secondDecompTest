package com.jch.racWiFi.Localization;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.settings.model.LanguageModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;

public class LocaleConfiguration {
    public static final Locale CROATIAN = new Locale("hr", "HR");
    public static final Locale CZECH = new Locale("cs", "CZ");
    public static final Locale DEFAULT = Locale.US;
    public static LanguageModel DEFAULT_LANGUAGE_MODEL = new LanguageModel(Locale.US, R.string.common_item_langEngUS, R.string.common_item_langEngUS);
    public static final Locale DUTCH = new Locale("nl", "NL");
    public static final Locale GERMANY = new Locale("de", "DE");
    public static final Locale GREEK = new Locale("el", "GR");
    public static final Locale INDONESIA = new Locale("in", "ID");
    public static final String LOCALIZATION_PREF_KEY = "LPK";
    public static final Locale MAGYAR = new Locale("hu", "HU");
    public static final Locale MALAYSIA = new Locale("ms", "MY");
    public static final Locale POLISH = new Locale("pl", "PL");
    public static final Locale PORTUGESEBR = new Locale("pt", "BR");
    public static final Locale PORTUGESEEU = new Locale("pt", "PT");
    public static final Locale RORNANIAN = new Locale("ro", "RO");
    public static final Locale SLOVENIAN = new Locale("sl", "SL");
    public static final Locale SPANISH = new Locale("es", "ES");
    public static final Locale THAILAND = new Locale("th", "TH");
    public static final Locale VIETNAM = new Locale("vi", "VN");

    public enum LanguageCodeServer {
        EN,
        ID,
        VI,
        TH,
        MS,
        ZH_HANT,
        ZH_HANS,
        DE,
        ES,
        NL,
        PL,
        HU,
        CS,
        RO,
        EL,
        HR,
        SL,
        FR,
        IT,
        PT_BR,
        PT_PT
    }

    public static Locale getCurrentAppLocale() {
        return getLocaleFromJson(SharedPref.getInstance().getSharedPreferencesReader().getString(LOCALIZATION_PREF_KEY, getJsonFromLocale(DEFAULT)));
    }

    private static String getJsonFromLocale(Locale locale) {
        return new Gson().toJson((Object) locale);
    }

    private static Locale getLocaleFromJson(String str) {
        return (Locale) new Gson().fromJson(str, Locale.class);
    }

    public static void persistLocalization(Locale locale) {
        SharedPref.getInstance().getSharedPreferenceEditor().putString(LOCALIZATION_PREF_KEY, getJsonFromLocale(locale)).commit();
    }

    public static void clearLocalization() {
        SharedPref.getInstance().getSharedPreferenceEditor().putString(LOCALIZATION_PREF_KEY, getJsonFromLocale(DEFAULT));
    }

    public static LanguageCodeServer getLanguageCodeForLocale(Locale locale) {
        LanguageCodeServer languageCodeServer = LanguageCodeServer.EN;
        if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            return LanguageCodeServer.ZH_HANS;
        }
        if (locale.equals(Locale.TRADITIONAL_CHINESE)) {
            return LanguageCodeServer.ZH_HANT;
        }
        if (locale.equals(THAILAND)) {
            return LanguageCodeServer.TH;
        }
        if (locale.equals(VIETNAM)) {
            return LanguageCodeServer.VI;
        }
        if (locale.equals(MALAYSIA)) {
            return LanguageCodeServer.MS;
        }
        if (locale.equals(INDONESIA)) {
            return LanguageCodeServer.ID;
        }
        if (locale.equals(GERMANY)) {
            return LanguageCodeServer.DE;
        }
        if (locale.equals(SPANISH)) {
            return LanguageCodeServer.ES;
        }
        if (locale.equals(PORTUGESEBR)) {
            return LanguageCodeServer.PT_BR;
        }
        if (locale.equals(DUTCH)) {
            return LanguageCodeServer.NL;
        }
        if (locale.equals(POLISH)) {
            return LanguageCodeServer.PL;
        }
        if (locale.equals(MAGYAR)) {
            return LanguageCodeServer.HU;
        }
        if (locale.equals(CZECH)) {
            return LanguageCodeServer.CS;
        }
        if (locale.equals(PORTUGESEEU)) {
            return LanguageCodeServer.PT_PT;
        }
        if (locale.equals(RORNANIAN)) {
            return LanguageCodeServer.RO;
        }
        if (locale.equals(GREEK)) {
            return LanguageCodeServer.EL;
        }
        if (locale.equals(CROATIAN)) {
            return LanguageCodeServer.HR;
        }
        if (locale.equals(SLOVENIAN)) {
            return LanguageCodeServer.SL;
        }
        if (locale.equals(Locale.FRENCH)) {
            return LanguageCodeServer.FR;
        }
        return locale.equals(Locale.ITALY) ? LanguageCodeServer.IT : languageCodeServer;
    }

    public static String getLanguageCodeForCurrentLocale() {
        return getLanguageCodeForLocale(getCurrentAppLocale()).name();
    }

    public static class LanguageSelectionUtils {
        private static ArrayList<LanguageModel> languageModels = new ArrayList<>();

        public static List<LanguageModel> getLanguageModelList() {
            if (languageModels.isEmpty()) {
                generateListOfLanguageModels();
            }
            return languageModels;
        }

        public static void clearSelectedFlags() {
            if (!languageModels.isEmpty()) {
                Iterator<LanguageModel> it = languageModels.iterator();
                while (it.hasNext()) {
                    it.next().setSelected(false);
                }
            }
        }

        public static void init() {
            if (languageModels.isEmpty()) {
                generateListOfLanguageModels();
            }
        }

        public static LanguageModel getLanguageModelIfPresentInList(LanguageModel languageModel) {
            int indexOf = languageModels.indexOf(languageModel);
            return (LanguageModel) (indexOf == -1 ? languageModels.get(0) : languageModels.get(indexOf));
        }

        public static LanguageModel getLanguageModelBasedOnLocaleIfPresentInList(Locale locale) {
            return getLanguageModelIfPresentInList(new LanguageModel(locale));
        }

        private static void generateListOfLanguageModels() {
            languageModels.add(new LanguageModel(Locale.US, R.string.common_item_langEngUS, R.string.common_item_langEngUS));
            languageModels.add(new LanguageModel(Locale.FRENCH, R.string.common_item_langFrench, R.string.common_item_french));
            languageModels.add(new LanguageModel(Locale.ITALY, R.string.common_item_langItalian, R.string.common_item_italian));
            languageModels.add(new LanguageModel(LocaleConfiguration.SPANISH, R.string.common_item_langSpanish, R.string.common_item_spanish));
            languageModels.add(new LanguageModel(LocaleConfiguration.GERMANY, R.string.common_item_langGermany, R.string.common_item_germany));
            languageModels.add(new LanguageModel(LocaleConfiguration.PORTUGESEBR, R.string.common_item_langPortugeseBR, R.string.common_item_portugeseBR));
            languageModels.add(new LanguageModel(LocaleConfiguration.DUTCH, R.string.common_item_langDutch, R.string.common_item_dutch));
            languageModels.add(new LanguageModel(LocaleConfiguration.THAILAND, R.string.common_item_langThai, R.string.common_item_thai));
            languageModels.add(new LanguageModel(Locale.TRADITIONAL_CHINESE, R.string.common_item_langTraditionalChinese, R.string.common_item_traditionalChinese));
            languageModels.add(new LanguageModel(LocaleConfiguration.INDONESIA, R.string.common_item_langBasaIndonesia, R.string.common_item_basaIndonesia));
            languageModels.add(new LanguageModel(LocaleConfiguration.POLISH, R.string.common_item_langPolish, R.string.common_item_polish));
            languageModels.add(new LanguageModel(LocaleConfiguration.MAGYAR, R.string.common_item_langMagyar, R.string.common_item_magyar));
            languageModels.add(new LanguageModel(LocaleConfiguration.CZECH, R.string.common_item_langCzech, R.string.common_item_czech));
            languageModels.add(new LanguageModel(LocaleConfiguration.PORTUGESEEU, R.string.common_item_langPortugeseEU, R.string.common_item_portugeseEU));
            languageModels.add(new LanguageModel(LocaleConfiguration.RORNANIAN, R.string.common_item_langRornanian, R.string.common_item_rornanian));
            languageModels.add(new LanguageModel(LocaleConfiguration.GREEK, R.string.common_item_langGreek, R.string.common_item_greek));
            languageModels.add(new LanguageModel(LocaleConfiguration.CROATIAN, R.string.common_item_langcroatian, R.string.common_item_croatian));
            languageModels.add(new LanguageModel(LocaleConfiguration.SLOVENIAN, R.string.common_item_langSlovenian, R.string.common_item_slovenian));
            languageModels.add(new LanguageModel(LocaleConfiguration.VIETNAM, R.string.common_item_langVietnamese, R.string.common_item_vietnamese));
            languageModels.add(new LanguageModel(Locale.SIMPLIFIED_CHINESE, R.string.common_item_langSimpleChinese, R.string.common_item_simpleChinese));
            languageModels.add(new LanguageModel(LocaleConfiguration.MALAYSIA, R.string.common_item_langMalayMalaysia, R.string.common_item_malayMalaysia));
            languageModels.add(new LanguageModel(new Locale("zz", "ZZ"), R.string.common_item_langBasaIndonesia, R.string.common_item_langBasaIndonesia));
            languageModels.add(new LanguageModel(new Locale("zz", "ZZ"), R.string.common_item_langBasaIndonesia, R.string.common_item_langBasaIndonesia));
            languageModels.add(new LanguageModel(new Locale("zz", "ZZ"), R.string.common_item_langBasaIndonesia, R.string.common_item_langBasaIndonesia));
        }
    }

    public static class AddUserAndAddAcButtonConfigurationUtils {
        private static final String USER_SESSION = "USER_SESSION";
        private boolean isLoggedIn = false;
        private boolean isMemberRemoved = false;
        private boolean isRacRemoved = false;
        private int loginCount = 0;
        private long sessionTimeInMilSec;
        private int userId = -1;

        public void setIsLoggedIn(boolean z, AddUserAndAddAcButtonConfigurationUtils addUserAndAddAcButtonConfigurationUtils) {
            this.isLoggedIn = z;
            commitChanges(addUserAndAddAcButtonConfigurationUtils);
        }

        public boolean getIsLoggedIn() {
            return this.isLoggedIn;
        }

        public int getUserId() {
            return this.userId;
        }

        public void setUserId(int i) {
            this.userId = i;
        }

        public long getSessionTimeInMilSec() {
            return this.sessionTimeInMilSec;
        }

        public void setSessionTimeInMilSec(long j) {
            this.sessionTimeInMilSec = j;
        }

        public boolean isRacRemoved() {
            return this.isRacRemoved;
        }

        public void setRacRemoved(boolean z, AddUserAndAddAcButtonConfigurationUtils addUserAndAddAcButtonConfigurationUtils) {
            this.isRacRemoved = z;
            commitChanges(addUserAndAddAcButtonConfigurationUtils);
        }

        public boolean isMemberRemoved() {
            return this.isMemberRemoved;
        }

        public void setMemberRemoved(boolean z, AddUserAndAddAcButtonConfigurationUtils addUserAndAddAcButtonConfigurationUtils) {
            this.isMemberRemoved = z;
            commitChanges(addUserAndAddAcButtonConfigurationUtils);
        }

        public int getLoginCount() {
            return this.loginCount;
        }

        public void setLoginCount(int i, AddUserAndAddAcButtonConfigurationUtils addUserAndAddAcButtonConfigurationUtils) {
            this.loginCount = i;
            this.isLoggedIn = true;
            addUserAndAddAcButtonConfigurationUtils.sessionTimeInMilSec = System.currentTimeMillis();
            commitChanges(addUserAndAddAcButtonConfigurationUtils);
        }

        private void commitChanges(AddUserAndAddAcButtonConfigurationUtils addUserAndAddAcButtonConfigurationUtils) {
            String json = new Gson().toJson((Object) addUserAndAddAcButtonConfigurationUtils);
            SharedPreferences.Editor sharedPreferenceEditor = SharedPref.getInstance().getSharedPreferenceEditor();
            sharedPreferenceEditor.putString(USER_SESSION + this.userId, json).commit();
        }

        public static void persistUser(int i) {
            AddUserAndAddAcButtonConfigurationUtils addUserAndAddAcButtonConfigurationUtils = new AddUserAndAddAcButtonConfigurationUtils();
            addUserAndAddAcButtonConfigurationUtils.userId = i;
            addUserAndAddAcButtonConfigurationUtils.loginCount = 1;
            addUserAndAddAcButtonConfigurationUtils.isMemberRemoved = false;
            addUserAndAddAcButtonConfigurationUtils.isRacRemoved = false;
            addUserAndAddAcButtonConfigurationUtils.isLoggedIn = true;
            addUserAndAddAcButtonConfigurationUtils.sessionTimeInMilSec = System.currentTimeMillis();
            String json = new Gson().toJson((Object) addUserAndAddAcButtonConfigurationUtils);
            SharedPreferences.Editor sharedPreferenceEditor = SharedPref.getInstance().getSharedPreferenceEditor();
            sharedPreferenceEditor.putString(USER_SESSION + i, json).commit();
        }

        public static AddUserAndAddAcButtonConfigurationUtils getCurrentUserConfig(int i) {
            Gson gson = new Gson();
            SharedPreferences sharedPreferencesReader = SharedPref.getInstance().getSharedPreferencesReader();
            return (AddUserAndAddAcButtonConfigurationUtils) gson.fromJson(sharedPreferencesReader.getString(USER_SESSION + i, ""), AddUserAndAddAcButtonConfigurationUtils.class);
        }

        public static int getDayDifference(Long l) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(l.longValue());
            Date time = instance.getTime();
            Calendar instance2 = Calendar.getInstance();
            instance2.setTimeInMillis(System.currentTimeMillis());
            return (int) ((time.getTime() - instance2.getTime().getTime()) / DateUtils.MILLIS_PER_DAY);
        }
    }
}
