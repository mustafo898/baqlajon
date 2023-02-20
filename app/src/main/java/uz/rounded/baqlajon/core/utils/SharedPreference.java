package uz.rounded.baqlajon.core.utils;

import static uz.rounded.baqlajon.core.extensions.FragmentExtensionsKt.jsonToObject;
import static uz.rounded.baqlajon.core.extensions.FragmentExtensionsKt.objectToJson;

import android.content.Context;
import android.content.SharedPreferences;

import uz.rounded.baqlajon.domain.model.DataModel;
import uz.rounded.baqlajon.domain.model.UserResponseModel;

public class SharedPreference {
    SharedPreferences prefs;
    private static SharedPreference sharePreference;
    SharedPreferences.Editor editor;

    public static SharedPreference getInstance(Context context) {
        if (sharePreference != null) return sharePreference;
        else return sharePreference = new SharedPreference(context);
    }

    private SharedPreference(Context context) {
        prefs = context.getSharedPreferences(getClass().getName(), Context.MODE_PRIVATE);
    }

    public void setLang(String lang) {
        editor = prefs.edit();
        editor.putString("lang", lang);
        editor.apply();
    }

    public String getLang() {
        return prefs.getString("lang", "en");
    }


    public void setHasLang(boolean hasLang) {
        editor = prefs.edit();
        editor.putBoolean("hasLang", hasLang);
        editor.apply();
    }

    public int getType() {
        return prefs.getInt("type", 0);
    }

    public void setType(int type) {
        editor = prefs.edit();
        editor.putInt("type", type);
        editor.apply();
    }

    public boolean getHasLang() {
        return prefs.getBoolean("hasLang", false);
    }

    public void setHasToken(Boolean token) {
        editor = prefs.edit();
        editor.putBoolean("hasToken", token);
        editor.apply();
    }

    public Boolean getHasToken() {
        return prefs.getBoolean("hasToken", false);
    }

    public void setToken(String token) {
        editor = prefs.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken() {
        return prefs.getString("token", "");
    }

    public void setUser(DataModel user) {
        editor = prefs.edit();
        editor.putString("user", objectToJson(user));
        editor.apply();
    }

    public void setPassword(String string) {
        editor = prefs.edit();

        editor.putString("password", string).apply();
    }

    public String getPassword() {
        return prefs.getString("password", "");
    }

    public DataModel getUser() {
        return jsonToObject(prefs.getString("user", ""));
    }


}
