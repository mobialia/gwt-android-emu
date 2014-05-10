package android.preference;

import android.content.Context;

public class PreferenceManager {

	public static SharedPreferences getDefaultSharedPreferences(Context ctx) {
		return new SharedPreferences();
	}
}
