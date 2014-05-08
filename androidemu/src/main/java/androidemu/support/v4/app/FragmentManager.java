package androidemu.support.v4.app;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

import java.util.ArrayList;
import java.util.Stack;

import androidemu.util.Log;
import androidemu.view.LayoutInflater;
import androidemu.view.View;
import androidemu.view.ViewGroup;

public class FragmentManager {

	static final String TAG = "FragmentManager";

	FragmentActivity activity;
	ArrayList<Fragment> fragments = new ArrayList<Fragment>();

	Stack<BackStackEntry> backStack = new Stack<BackStackEntry>();

	public class BackStackEntry {
		String id;
		String name;

		BackStackEntry(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	public FragmentManager(FragmentActivity activity) {
		this.activity = activity;
	}

	public FragmentTransaction beginTransaction() {
		return new FragmentTransaction(this);
	}

	public int getBackStackEntryCount() {
		return backStack.size();
	}

	public BackStackEntry getBackStackEntryAt(int index) {
		return backStack.get(index);
	}

	public Fragment findFragmentById(String id) {
		return null;
	}

	public Fragment findFragmentByTag(String tag) {
		return null;
	}

	void addToBackStack(String name, FragmentTransaction transaction) {
		Log.d(TAG, "addToBackStack()");
		BackStackEntry entry = new BackStackEntry(null, name);
		backStack.push(entry);
	}

	void commit(FragmentTransaction transaction) {
		Log.d(TAG, "commit()");
		Element el = DOM.getElementById(transaction.containerViewId);

		transaction.fragment.containerViewId = transaction.containerViewId;
		Log.d(TAG, "Stopping fragments...");
		// Stop any fragment with the same view ID
		for (int i=0; i < fragments.size(); i++) {
			if (transaction.containerViewId.equals(fragments.get(i).containerViewId)) {
				Log.d(TAG, "Calling onPause");
				fragments.get(i).onPause();
				Log.d(TAG, "Hiding");
				fragments.get(i).mView.setVisibility(View.GONE);
				Log.d(TAG, "OK");
			}
		}
		Log.d(TAG, "Adding...");
		fragments.add(transaction.fragment);

		transaction.fragment.mActivity = activity;
		ViewGroup viewGroup = new ViewGroup(el);

		transaction.fragment.onCreate(null);
		transaction.fragment.mView = transaction.fragment.onCreateView(new LayoutInflater(), viewGroup, null);
		Log.d(TAG, "Appending...");
		el.appendChild(transaction.fragment.mView.getElement());
		if (activity.resumed) {
			Log.d(TAG, "Resuming...");
			transaction.fragment.onResume();
		}
	}

	void resumeFragments() {
		// RESUME only the lastest for each view ID...
		for (int i = 0; i < fragments.size(); i++) {
			if (!fragments.get(i).resumed) {
				fragments.get(i).onResume();
			}
		}
	}

	public void popBackStack() {

	}
}
