package android.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PreferencesFragment extends Fragment {
	static final String TAG = PreferencesFragment.class.getSimpleName();

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		view = inflater.inflate(R.layout.preferences_fragment, container, false);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		((CommonActivity) getActivity()).onFragmentResumed(getString(R.string.sidebar_preferences), false);
	}
}
