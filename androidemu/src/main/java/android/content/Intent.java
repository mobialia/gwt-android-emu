package android.content;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

public class Intent {

	public static final String ACTION_VIEW = "android.intent.action.VIEW";

	String action;
	Uri uri;
	Bundle extras;
	Context ctx;
	Class<?> intentClass;
	public Activity activity;

	public Intent(String action, Uri uri) {
		this.action = action;
		this.uri = uri;
	}

	// public Intent(Context ctx, final Class<?> intentClass) {
	// this.ctx = ctx;
	// this.intentClass = intentClass;
	// this.activity = (Activity) GWT.create(intentClass);
	// }

	public Intent(Context ctx, Object activity) {
		this.ctx = ctx;
		this.activity = (Activity) activity;
	}

	public String getAction() {
		return action;
	}

	public Uri getData() {
		return uri;
	}

	public Class<?> getIntentClass() {
		return intentClass;
	}

	public void putExtras(Bundle extras) {
		this.extras = extras;
	}

	public Bundle getExtras() {
		return extras;
	}
}
