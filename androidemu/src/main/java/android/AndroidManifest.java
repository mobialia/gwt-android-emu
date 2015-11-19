package android;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.google.gwt.core.client.EntryPoint;

public abstract class AndroidManifest implements EntryPoint {
	static final String TAG = "BaseAndroidManifest";

	public static AndroidManifest instance;

	public void onModuleLoad() {
		Res.R.style().ensureInjected();

		instance = this;

		Context.icon = getIcon();
		Context.application = getApplication();

		Resources resources = getResources();
		if (resources != null) {
			Context.resources = resources;
		} else {
			Log.i(TAG, "No Resources defined");
		}

		ActivityManager.setup();

		Class defaultActivityClass = getDefaultActivityClass();
		if (defaultActivityClass != null) {
			ActivityManager.startActivity(new Intent(null, defaultActivityClass), null);
		} else {
			Log.e(TAG, "No default activity defined");
		}
	}

	public abstract int getIcon();

	public abstract Application getApplication();

	public abstract Resources getResources();

	public abstract Class getDefaultActivityClass();

	/**
	 * Creates and returns a new Activity
	 */
	public abstract Activity getActivity(Class activityClass);

	/**
	 * Returns a reference to an existing service instance
	 */
	public abstract Service getService(Class serviceClass);
}
