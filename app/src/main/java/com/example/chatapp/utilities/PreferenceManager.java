package com.example.chatapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private final SharedPreferences sharedPreferences;

    /**
     * Constructor
     * @param context context of Preference Manager
     */
    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.KEY_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Stores whether the user is signed in
     * @param key User's key
     * @param value if key is signed in
     */
    public void putBoolean(String key, Boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Returns if user is signed in
     * @param key User's key
     * @return if User is signed in
     */
    public Boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * Changes the value of a User
     * @param key Constant
     * @param value new value of constant
     */
    public void putString(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Gets value of constant
     * @param key Constant
     * @return value of constant
     */
    public String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    /**
     * Clears sharedPreferences
     */
    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
