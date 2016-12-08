package android.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IntroFragment extends Fragment {
	static final String TAG = IntroFragment.class.getSimpleName();

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		view = inflater.inflate(R.layout.intro_fragment, container, false);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();

		((CommonActivity) getActivity()).onFragmentResumed(getString(R.string.intro_title), false);
	}
}
