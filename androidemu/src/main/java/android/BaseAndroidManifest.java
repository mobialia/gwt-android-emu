package android;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.BaseResourceResolver;
import android.content.res.Resources;
import com.google.gwt.core.client.EntryPoint;

public abstract class BaseAndroidManifest implements EntryPoint {

	public void onModuleLoad() {
		Res.R.style().ensureInjected();

		Resources.setResourceResolver(getResourceResolver());

		ActivityManager.setup();

		Activity act = getDefaultActivity();
		ActivityManager.startActivity(new Intent(act, act), null);
	}

	public abstract BaseResourceResolver getResourceResolver();

	public abstract Activity getDefaultActivity();
}
