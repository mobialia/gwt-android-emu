package android.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class DemoFragment extends Fragment {
	static final String TAG = "DemoFragment";

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		view = inflater.inflate(R.layout.demo_fragment, container, false);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");

		((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(R.string.demo_fragment);
	}

	@Override
	public void onPause() {
		Log.d(TAG, "onPause");
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_launch_fragment) {
			FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
			Fragment fragment = new DemoFragment2();
			fragmentTransaction.replace(R.id.Fragment, fragment);
			fragmentTransaction.addToBackStack("demo2");
			fragmentTransaction.commit();
			return true;
		}
		return false;
	}
}
