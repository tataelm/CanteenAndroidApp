package com.makel.ata.yemekhane;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.makel.ata.yemekhane.entities.FoodPlan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AppConfig {
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat _simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy, EEEE");

    public static final List<FoodPlan> _listFoodPlan = new ArrayList<>();
    public static FoodPlan _foodPlan = null;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BASE_URL = "http://192.168.223.1:8090/foodplan";


    public static class Settings
    {
        private static final String settings_ShowOldPlans = "showOldPlans";
        public static boolean _showOldPlans = false;

        public static void loadSettings() {
            SharedPreferences sharedPreferences = MainActivity.getInstance().getSharedPreferences(AppConfig.SHARED_PREFS, Context.MODE_PRIVATE);
            _showOldPlans = sharedPreferences.getBoolean(settings_ShowOldPlans, false);
        }

        public static void saveSettings()
        {
            SharedPreferences sharedPreferences = MainActivity.getInstance().getSharedPreferences(AppConfig.SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(settings_ShowOldPlans, _showOldPlans);
            editor.apply();
        }
    }
}
