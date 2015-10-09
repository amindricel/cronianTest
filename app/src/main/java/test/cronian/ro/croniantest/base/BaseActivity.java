package test.cronian.ro.croniantest.base;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Alex on 9/25/2015.
 */
public class BaseActivity extends FragmentActivity {
    public static final int SAVE_INT = 0;
    public static final int SAVE_STRING = 1;
    public static final int SAVE_BOOLEAN = 2;
    public static final int GET_INT = 3;
    public static final int GET_STRING = 4;
    public static final int GET_BOOLEAN = 5;

    public void saveToSharedPreferences(int type, String key, Object toSave) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        switch (type) {
            case SAVE_INT:
                preferences.edit().putInt(key, (int) toSave).commit();
                break;
            case SAVE_STRING:
                preferences.edit().putString(key, (String) toSave).commit();
                break;
            case SAVE_BOOLEAN:
                preferences.edit().putBoolean(key, (Boolean) toSave).commit();
                break;
            default:
                break;
        }

    }


    public Object getValueFormPreferences(String key, int retriveType) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Object return_obj = null;
        switch (retriveType) {
            case GET_INT:
                return_obj = preferences.getInt(key, 0);
                break;
            case GET_BOOLEAN:
                return_obj = preferences.getBoolean(key, false);
                break;
            case GET_STRING:
                return_obj = preferences.getString(key, "");
                break;
        }
        return return_obj;
    }
}
