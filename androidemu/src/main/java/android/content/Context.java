package android.content;

import android.app.ActivityManager;
import android.app.Application;
import android.app.ServiceManager;
import android.content.res.Resources;

public class Context {

	public static final int BIND_AUTO_CREATE = 0x00000001;

	/**
	 * Initialized from AndroidManifest
	 */
	public static int icon;
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

	public void startService(Intent intent) {
		ServiceManager.startService(intent);
	}

	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		return ServiceManager.bindService(service, conn, flags);
	}

	public void unbindService(ServiceConnection conn) {
		conn.onServiceDisconnected(null);
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