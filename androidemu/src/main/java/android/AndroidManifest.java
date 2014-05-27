package android;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.google.gwt.core.client.EntryPoint;

public abstract class AndroidManifest implements EntryPoint {

	static final String TAG = "BaseAndroidManifest";

	public void onModuleLoad() {
		Res.R.style().ensureInjected();

		Context.application = getApplication();

		Resources resources = getResources();
		if (resources != null) {
			Context.resources = resources;
		} else {
			Log.i(TAG, "No Resources defined");
		}

		ActivityManager.setup();

		Activity act = getDefaultActivity();
		if (act != null) {
			ActivityManager.startActivity(new Intent(act, act), null);
		} else {
			Log.e(TAG, "No default activity defined");
		}
	}

	public abstract Application getApplication();

	public abstract Resources getResources();

	public abstract Activity getDefaultActivity();
}
