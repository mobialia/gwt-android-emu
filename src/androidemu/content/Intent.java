package androidemu.content;

import androidemu.app.Activity;
import androidemu.net.Uri;
import androidemu.os.Bundle;

public class Intent {

	public static final String ACTION_VIEW = "android.intent.action.VIEW";

	Bundle extras;
	Context ctx;
	Class<?> intentClass;
	public Activity activity;

	public Intent(String action, Uri uri) {

	}
	
	public Intent(Context ctx, Class<?> intentClass) {
		this.ctx = ctx;
		this.intentClass = intentClass;
	}

	public Intent(Context ctx, Object activity) {
		this.ctx = ctx;
		this.activity = (Activity) activity;
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
