package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class ActionBar {

	Activity activity;

	public ActionBar(Activity activity) {
		this.activity = activity;
	}

	public void setTitle(int title) {
		setTitle(Context.resources.getString(title));
	}

	public void setTitle(String title) {
		((TextView) activity.view.findViewById("ActionBarTitle")).setText(title);
	}

	public void setDisplayHomeAsUpEnabled(boolean displayHomeAsUpEnabled) {

	}

	public void setHomeButtonEnabled(boolean homeButtonEnabled) {

	}
}
