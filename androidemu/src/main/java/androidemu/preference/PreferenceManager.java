package androidemu.preference;

import androidemu.content.Context;

public class PreferenceManager {

	public static SharedPreferences getDefaultSharedPreferences(Context ctx) {
		return new SharedPreferences();
	}
}
