package android.support.v7.app;

import android.support.v4.app.FragmentActivity;

public class ActionBarActivity extends FragmentActivity {

	ActionBar actionBar;

	public ActionBarActivity() {
		actionBar = new ActionBar(this);
	}


	public ActionBar getSupportActionBar() {
		return actionBar;
	}

}
