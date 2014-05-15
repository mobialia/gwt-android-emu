package android.support.v7.app;

import android.support.v4.app.FragmentActivity;

public class ActionBarActivity extends FragmentActivity {

	ActionBar actionBar = new ActionBar();

	public ActionBar getSupportActionBar() {
		return actionBar;
	}

}
