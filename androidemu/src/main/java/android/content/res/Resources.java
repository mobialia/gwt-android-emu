package android.content.res;

import android.util.Log;

public class Resources {
	static final String TAG = "Resources";

	public static BaseResourceResolver resourceResolver;

	public static void setResourceResolver(BaseResourceResolver resourceResolver) {
		Resources.resourceResolver = resourceResolver;
	}

	public static BaseResourceResolver getResourceResolver() {
		if (Resources.resourceResolver == null) {
			Log.e(TAG, "NO Resource resolver defined");
			return null;
		}
		return Resources.resourceResolver;
	}

	public String getText(int id) {
		return getResourceResolver().getString(id);
	}

	public String getString(int id) {
		return getResourceResolver().getString(id);
	}

	public String[] getStringArray(int id) {
		return getResourceResolver().getStringArray(id);
	}
}
