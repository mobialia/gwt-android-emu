package android.app;

import android.content.Context;

public class ContextWrapper extends Context {

	public Context getApplicationContext() {
		return this;
	}
}
