package android.support.v4.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FragmentActivity extends Activity {

	FragmentManager fragmentManager = new FragmentManager(this);

	public FragmentManager getSupportFragmentManager() {
		return fragmentManager;
	}

	boolean resumed = false;

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		fragmentManager.onActivityPostCreate();
	}

	@Override
	protected void onResume() {
		super.onResume();
		resumed = true;
	}

	@Override
	protected void onPostResume() {
		super.onResume();
		fragmentManager.onActivityPostResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		resumed = false;
	}

	@Override
	public void onBackPressed() {
		if (fragmentManager.getBackStackEntryCount() > 0) {
			fragmentManager.popBackStack();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		fragmentManager.onCreateOptionsMenu(menu);
		return false;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (!fragmentManager.onOptionsItemSelected(item)) {
			return super.onMenuItemSelected(featureId, item);
		}
		return true;
	}
}
