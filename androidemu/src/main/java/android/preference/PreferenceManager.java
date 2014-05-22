package android.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

	public static SharedPreferences getDefaultSharedPreferences(Context ctx) {
		return new SharedPreferences();
	}
}
