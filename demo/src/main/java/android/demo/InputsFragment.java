package android.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InputsFragment extends Fragment {
	static final String TAG = InputsFragment.class.getSimpleName();

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		view = inflater.inflate(R.layout.inputs_fragment, container, false);

		Spinner spinner = (Spinner) view.findViewById(R.id.Spinner);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), 0, getResources().getStringArray(R.array.array1));
		spinner.setAdapter(spinnerArrayAdapter);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		((CommonActivity) getActivity()).onFragmentResumed(getString(R.string.sidebar_inputs), false);
	}
}

