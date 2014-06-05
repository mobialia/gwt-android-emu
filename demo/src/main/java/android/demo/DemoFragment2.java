package android.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DemoFragment2 extends Fragment {
	static final String TAG = "DemoFragment2";

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		view = inflater.inflate(R.layout.demo_fragment2, container, false);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(R.string.demo_fragment2);
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}
}
