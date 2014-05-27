package android.content;

import android.app.ActivityManager;
import android.app.Application;
import android.content.res.Resources;

public class Context {

	/**
	 * Initialized from AndroidManifest
	 */
	public static Application application;
	public static Resources resources;

	public Resources getResources() {
		return resources;
	}

	public String getString(int in) {
		return resources.getString(in);
	}

	public void startActivity(Intent intent) {
		ActivityManager.startActivity(intent, null);
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		Context.application = application;
	}

	public String getPackageName() {
		return ""; // TODO
	}
}