package android.demo;

import android.Theme;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.res.Resources;
import android.demo.res.AppStyle;

import com.google.gwt.core.client.GWT;

public class AndroidManifest extends android.AndroidManifest {

	@Override
	public void onModuleLoad() {
		// Here you can override the default theme colors
		Theme.setColorPrimary("#247ca9");

		super.onModuleLoad();
		// Inject app style
		((AppStyle) GWT.create(AppStyle.class)).style().ensureInjected();
	}

	@Override
	public int getIcon() {
		return R.drawable.icon;
	}

	@Override
	public Application getApplication() {
		return new Application();
	}

	@Override
	public Resources getResources() {
		return new android.demo.res.Resources();
	}

	@Override
	public Class getDefaultActivityClass() {
		return MainActivity.class;
	}

	@Override
	public Activity getActivity(Class activityClass) {
		if (MainActivity.class.equals(activityClass)) {
			return new MainActivity();
		} else if (SimpleActivity.class.equals(activityClass)) {
			return new SimpleActivity();
		}
		return null;
	}

	@Override
	public Service getService(Class serviceClass) {

		return null;
	}

}
