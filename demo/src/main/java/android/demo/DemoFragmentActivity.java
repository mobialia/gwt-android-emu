package android.demo;

import android.demo.res.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class DemoFragmentActivity extends FragmentActivity {

	public static final String TAG = "FragmentActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_fragment_activity);
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

}
