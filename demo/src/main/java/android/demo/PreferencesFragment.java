package android.demo;

import android.Res;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.PreferencesBuilder;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

/**
 * At the moments the preferences must be created manually using the PreferencesBuilder
 */
public class PreferencesFragment extends Fragment {
	static final String TAG = PreferencesFragment.class.getSimpleName();

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		Element e = DOM.createDiv();
		e.addClassName(Res.R.style().preferencesFragment());

		Element vp = DOM.createDiv();
		vp.addClassName(Res.R.style().preferencesFragmentContent());

		SharedPreferences sharedPrefs;
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

		PreferencesBuilder.addGroupLabel(vp, R.string.preferences_group);

		PreferencesBuilder.addListPreference(vp, sharedPrefs, "preference_list",
				R.string.preference_list, R.string.preference_list_summary,
				R.array.array1, R.array.array1,
				"");
		PreferencesBuilder.addBooleanPreference(vp, sharedPrefs, "preference_boolean",
				R.string.preference_boolean, R.string.preference_boolean_summary, true);

		e.appendChild(vp);
		return new View(e);
	}

	@Override
	public void onResume() {
		super.onResume();
		((CommonActivity) getActivity()).onFragmentResumed(getString(R.string.sidebar_preferences), true);
	}
}
