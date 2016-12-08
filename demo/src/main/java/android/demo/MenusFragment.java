package android.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MenusFragment extends Fragment {
	static final String TAG = MenusFragment.class.getSimpleName();

	View view;

	// Handler works!
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Toast.makeText(getActivity(), R.string.handler_message_received, Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		view = inflater.inflate(R.layout.menus_fragment, container, false);
		return view;
	}


	@Override
	public void onResume() {
		super.onResume();
		((CommonActivity) getActivity()).onFragmentResumed(getString(R.string.sidebar_menus), false);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.demo_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_add) {
			Toast toast = Toast.makeText(getActivity(), R.string.menu_add, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else if (item.getItemId() == R.id.menu_action1) {
			Toast toast = Toast.makeText(getActivity(), R.string.menu_option1, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else if (item.getItemId() == R.id.menu_action2) {
			Toast toast = Toast.makeText(getActivity(), R.string.menu_option2, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
		return false;
	}

}
