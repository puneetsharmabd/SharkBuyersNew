package com.sharkbuyers.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppController  {

    // TODO: CHANGE THIS TO SOMETHING MEANINGFUL
    private static final String SETTINGS_NAME = "default_settings";
    private static AppController sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;

    /**
     * Class for keeping all the keys used for shared preferences in one place.
     */
    public static class Key {
        /* Recommended naming convention:
         * ints, floats, doubles, longs:
         * SAMPLE_NUM or SAMPLE_COUNT or SAMPLE_INT, SAMPLE_LONG etc.
         *
         * boolean: IS_SAMPLE, HAS_SAMPLE, CONTAINS_SAMPLE
         *
         * String: SAMPLE_KEY, SAMPLE_STR or just SAMPLE
         */

        public static final String USER_LOGIN_PASSWORD_STR = "save_password";
        public static final String USER_LOGIN_EMAIL_STR = "save_login";
        public static final String SAVE_DEVICE_TOKEN = "device_token";
        public static final String SAVE_ACCESS_TOKEN = "accesss_token";
        public static final String USERID = "id";
        public static final String FIRSTNAME = "first_name";
        public static final String LASTNAME = "last_name";
        public static final String EMAIL = "email";
        public static final String PHONENUMBER = "phone_num";
        public static final String GENDER = "gender";
        public static final String OCCUPATION = "occupation";
        public static final String ADDRESS = "address";
        public static final String BRANDIMAGE = "brand_image";
        public static final String RESUME = "resume";
        public static final String LATITUDE = "latitude";
        public static final String LONGITDUE = "longitude";
        public static final String STATE = "state";
        public static final String COUNTRY = "country";
        public static final String STATUS = "verify_status";
        public static final String COUNTRY_ID = "country_id";
        public static final String STATE_ID = "state_id";
        public static final String SAVE_STATE = "state_save";
        public static final String SAVE_COUNTRY = "country_save";
        public static final String SAVE_COUNTRY_ID = "save_country_id";
        public static final String SAVE_STATE_ID = "save_state_id";



    }

    private AppController(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }


    public static AppController getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new AppController(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static AppController getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }

        //Option 1:
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");

        //Option 2:
        // Alternatively, you can create a new instance here
        // with something like this:
        // getInstance(MyCustomApplication.getAppContext());
    }

    public void put(String key, String val) {
        doEdit();
        mEditor.putString(key, val);
        doCommit();
    }

    public void put(String key, int val) {
        doEdit();
        mEditor.putInt(key, val);
        doCommit();
    }

    public void put(String key, boolean val) {
        doEdit();
        mEditor.putBoolean(key, val);
        doCommit();
    }

    public void put(String key, float val) {
        doEdit();
        mEditor.putFloat(key, val);
        doCommit();
    }

    /**
     * Convenience method for storing doubles.
     *
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to 
     * cast to and from String.
     *
     * @param key The name of the preference to store.
     * @param val The new value for the preference.
     */
    public void put(String key, double val) {
        doEdit();
        mEditor.putString(key, String.valueOf(val));
        doCommit();
    }

    public void put(String key, long val) {
        doEdit();
        mEditor.putLong(key, val);
        doCommit();
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key, defaultValue);
    }

    public String getString(String key) {
        return mPref.getString(key, null);
    }

    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return mPref.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return mPref.getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return mPref.getLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return mPref.getFloat(key, 0);
    }

    public float getFloat(String key, float defaultValue) {
        return mPref.getFloat(key, defaultValue);
    }

    /**
     * Convenience method for retrieving doubles. 
     *
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to 
     * cast to and from String.
     *
     * @param key The name of the preference to fetch.
     */
    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    /**
     * Convenience method for retrieving doubles. 
     *
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to 
     * cast to and from String.
     *
     * @param key The name of the preference to fetch.
     */
    public double getDouble(String key, double defaultValue) {
        try {
            return Double.valueOf(mPref.getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPref.getBoolean(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    /**
     * Remove keys from SharedPreferences.
     *
     * @param keys The name of the key(s) to be removed.
     */
    public void remove(String ... keys) {
        doEdit();
        for (String key : keys) {
            mEditor.remove(key);
        }
        doCommit();
    }

    /**
     * Remove all keys from SharedPreferences.
     */
    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
}
