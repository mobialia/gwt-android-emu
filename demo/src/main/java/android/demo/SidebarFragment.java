package android.demo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SidebarFragment extends ListFragment {
	public static final String TAG = SidebarFragment.class.getSimpleName();

	View view;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new SidebarAdapter(getActivity(),
				new int[]{SidebarAdapter.VIEW_TYPE_ITEM, SidebarAdapter.VIEW_TYPE_ITEM, SidebarAdapter.VIEW_TYPE_ITEM, SidebarAdapter.VIEW_TYPE_ITEM,
						SidebarAdapter.VIEW_TYPE_SEPARATOR, SidebarAdapter.VIEW_TYPE_ITEM},
				new int[]{R.string.sidebar_intro, R.string.sidebar_notifications, R.string.sidebar_inputs, R.string.sidebar_other,
						0, R.string.sidebar_preferences},
				new int[]{R.drawable.ic_intro, R.drawable.ic_notifications, R.drawable.ic_inputs, R.drawable.ic_other,
						0, R.drawable.ic_preferences}));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.sidebar, container, false);
		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		switch (position) {
			case 0:
				((MainActivity) getActivity()).onIntroAction();
				break;
			case 1:
				((MainActivity) getActivity()).onNotificationsAction();
				break;
			case 2:
				((MainActivity) getActivity()).onInputsAction();
				break;
			case 3:
				((MainActivity) getActivity()).onOtherAction();
				break;
			case 5:
				((MainActivity) getActivity()).onPreferencesAction();
				break;
		}
		((MainActivity) getActivity()).closeDrawer();
	}
}