package android.demo;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.res.Resources;

public class AndroidManifest extends android.AndroidManifest {

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
		} else if (DemoFragmentActivity.class.equals(activityClass)) {
			return new DemoFragmentActivity();
		}
		return null;
	}

	@Override
	public Service getService(Class serviceClass) {

		return null;
	}

}
