package android;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.BaseResourceResolver;
import android.content.res.Resources;
import android.util.Log;

import com.google.gwt.core.client.EntryPoint;

public abstract class BaseAndroidManifest implements EntryPoint {

	static final String TAG = "BaseAndroidManifest";

	public void onModuleLoad() {
		Res.R.style().ensureInjected();

		BaseResourceResolver rr = getResourceResolver();
		if (rr != null) {
			Resources.setResourceResolver(rr);
		} else {
			Log.i(TAG, "No Resource Resolver defined");
		}

		ActivityManager.setup();

		Activity act = getDefaultActivity();
		if (act != null) {
			ActivityManager.startActivity(new Intent(act, act), null);
		} else {
			Log.e(TAG, "No default activity defined");
		}
	}

	public abstract BaseResourceResolver getResourceResolver();

	public abstract Activity getDefaultActivity();
}
