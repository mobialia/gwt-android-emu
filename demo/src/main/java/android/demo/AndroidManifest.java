package android.demo;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;

public class AndroidManifest extends android.AndroidManifest {

	public int getIcon() {
		return R.drawable.icon;
	}

	public Application getApplication() {
		return new Application();
	}

	public Resources getResources() {
		return new android.demo.res.Resources();
	}

	public Activity getDefaultActivity() {
		return new MainActivity();
	}
}
