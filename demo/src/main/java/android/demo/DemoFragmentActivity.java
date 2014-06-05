package android.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

public class DemoFragmentActivity extends ActionBarActivity {

	public static final String TAG = "FragmentActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_fragment_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();

		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		Fragment fragment = new DemoFragment();
		fragmentTransaction.replace(R.id.Fragment, fragment);
		fragmentTransaction.commit();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return (super.onOptionsItemSelected(item));
	}
}
