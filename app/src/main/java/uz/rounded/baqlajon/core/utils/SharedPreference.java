package uz.rounded.baqlajon.core.utils;

import static uz.rounded.baqlajon.core.extensions.FragmentExtensionsKt.jsonToObject;
import static uz.rounded.baqlajon.core.extensions.FragmentExtensionsKt.objectToJson;

import android.content.Context;
import android.content.SharedPreferences;

import uz.rounded.baqlajon.domain.model.DataModel;

public class SharedPreference {
    private static final String PREF_NAME = "uz.rounded.baqlajon.core.utils.SharedPreference";
    private static final String KEY_LANG = "lang";
    private static final String KEY_HAS_LANG = "hasLang";
    private static final String KEY_TYPE = "type";
    private static final String KEY_HAS_TOKEN = "hasToken";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_NIGHT_MODE = "nightMode";

    SharedPreferences prefs;

    private static SharedPreference sharePreference;

    public static SharedPreference getInstance(Context context) {
        if (sharePreference != null) return sharePreference;
        else return sharePreference = new SharedPreference(context);
    }

    private SharedPreference(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setLang(String lang) {
        prefs.edit().putString(KEY_LANG, lang).apply();
    }

    public String getLang() {
        return prefs.getString(KEY_LANG, "en");
    }

    public void setHasLang(boolean hasLang) {
        prefs.edit().putBoolean(KEY_HAS_LANG, hasLang).apply();
    }

    public boolean getHasLang() {
        return prefs.getBoolean(KEY_HAS_LANG, false);
    }

    public void setType(int type) {
        prefs.edit().putInt(KEY_TYPE, type).apply();
    }

    public int getType() {
        return prefs.getInt(KEY_TYPE, 0);
    }

    public void setHasToken(Boolean token) {
        prefs.edit().putBoolean(KEY_HAS_TOKEN, token).apply();
    }

    public Boolean getHasToken() {
        return prefs.getBoolean(KEY_HAS_TOKEN, false);
    }

    public void setToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, "");
    }

    public void setUser(DataModel user) {
        prefs.edit().putString(KEY_USER, objectToJson(user)).apply();
    }

    public DataModel getUser() {
        return jsonToObject(prefs.getString(KEY_USER, ""));
    }

    public void setPassword(String string) {
        prefs.edit().putString(KEY_PASSWORD, string).apply();
    }

    public String getPassword() {
        return prefs.getString(KEY_PASSWORD, "");
    }

    public void setLanguage(String language) {
        prefs.edit().putString(KEY_LANGUAGE, language).apply();
    }

    public String getLanguage() {
        return prefs.getString(KEY_LANGUAGE, "");
    }

    public void setNightMode(Boolean nightMode) {
        prefs.edit().putBoolean(KEY_NIGHT_MODE, nightMode).apply();
    }

    public Boolean getNightMode() {
        return prefs.getBoolean(KEY_NIGHT_MODE, false);
    }
}
