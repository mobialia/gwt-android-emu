package androidemu.support.v4.app;

import androidemu.app.Activity;
import androidemu.content.Intent;
import androidemu.content.res.Resources;
import androidemu.os.Bundle;
import androidemu.view.LayoutInflater;
import androidemu.view.Menu;
import androidemu.view.MenuInflater;
import androidemu.view.MenuItem;
import androidemu.view.View;
import androidemu.view.ViewGroup;

public class Fragment {
	static final String TAG = "Fragment";

	Bundle args;
	Activity mActivity;

	public void setArguments(Bundle args) {
		this.args = args;
	}

	public Bundle getArguments() {
		return args;
	}

	public Activity getActivity() {
		return mActivity;
	}


	public void onCreate(Bundle savedInstanceState) {

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return null;
	}


	public void onActivityCreated(Bundle savedInstanceState) {

	}

	public void onResume() {
	}

	public void onPause() {

	}

	public void onDestroy() {

	}

	public void setHasOptionsMenu(boolean hasMenu) {

	}


	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	/**
	 * Call {@link Activity#startActivity(Intent)} on the fragment's
	 * containing Activity.
	 */
	public void startActivity(Intent intent) {
		if (mActivity == null) {
			throw new IllegalStateException("Fragment " + this + " not attached to Activity");
		}
		mActivity.startActivityFromFragment(this, intent, -1);
	}

	/**
	 * Return <code>getActivity().getResources()</code>.
	 */
	final public Resources getResources() {
		if (mActivity == null) {
			throw new IllegalStateException("Fragment " + this + " not attached to Activity");
		}
		return mActivity.getResources();
	}
}