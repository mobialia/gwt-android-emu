package androidemu.support.v4.app;

import androidemu.app.Activity;

public class FragmentActivity extends Activity {

	FragmentManager fragmentManager = new FragmentManager(this);

	public FragmentManager getSupportFragmentManager() {
		return fragmentManager;
	}

	boolean resumed = false;

	@Override
	protected void onResume() {
		super.onResume();
		resumed = true;
	}

	@Override
	protected void onPostResume() {
		super.onResume();
		fragmentManager.resumeFragments();
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
}
