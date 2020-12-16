package com.example.restaurantadvisorapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.example.restaurantadvisorapp.entities.AccessToken;

public class TokenManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    @SuppressLint("CommitPrefEdits")
    private TokenManager(SharedPreferences prefs) {
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    static synchronized TokenManager getInstance(SharedPreferences prefs) {
        if(INSTANCE == null) {
            INSTANCE = new TokenManager(prefs);
        }

        return INSTANCE;
    }

    void saveToken(AccessToken token) {
        editor.putString("ACCESS_TOKEN", token.getAccessToken()).commit();
    }

    void deleteToken() {
        editor.remove("ACCESS_TOKEN").commit();
    }

    public AccessToken getToken() {
        AccessToken token = new AccessToken();
        token.setAccessToken(prefs.getString("ACCESS_TOKEN", null));
        return token;
    }
}
