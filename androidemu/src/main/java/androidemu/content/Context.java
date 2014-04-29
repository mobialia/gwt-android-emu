package androidemu.content;

import androidemu.app.ActivityManager;
import androidemu.app.Application;
import androidemu.content.res.Resources;

public class Context {

	static Application application = new Application();
	Resources resources = new Resources();

	public Resources getResources() {
		return resources;
	}

	public String getString(String in) {
		return in;
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