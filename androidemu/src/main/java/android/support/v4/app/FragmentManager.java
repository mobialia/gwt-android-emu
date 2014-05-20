package android.support.v4.app;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class FragmentManager {
	static final String TAG = "FragmentManager";
	static final int STATUS_NOT_INITIALIZED = 0;
	static final int STATUS_CREATED = 1;
	static final int STATUS_CREATED_VIEW = 2;
	static final int STATUS_RESUMED = 3;
	static final int STATUS_PAUSED = 4;
	static final int STATUS_DESTROYED = 5;

	FragmentActivity activity;
	LinkedList<Fragment> fragments = new LinkedList<Fragment>();

	Stack<BackStackEntry> backStack = new Stack<BackStackEntry>();

	public class BackStackEntry {
		String id;
		String name;

		Fragment fragment;

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

	void commit(FragmentTransaction transaction) {
		Fragment fragment = transaction.fragment;

		fragment.containerViewId = transaction.containerViewId;
		fragment.visible = true;
		fragment.mActivity = activity;
		fragment.status = STATUS_NOT_INITIALIZED;
		if (activity.resumed) {
			fragment.targetStatus = STATUS_RESUMED;
		} else {
			fragment.targetStatus = STATUS_CREATED_VIEW;
		}

		// Hide any fragment with the same view ID
		if (transaction.replace) {
			for (Fragment otherFragment : fragments) {
				if (transaction.containerViewId.equals(otherFragment.containerViewId)) {
					otherFragment.visible = false;
					if (otherFragment.targetStatus == STATUS_RESUMED) {
						otherFragment.targetStatus = STATUS_PAUSED;
					}
				}
			}
		}
		fragments.add(transaction.fragment);

		if (transaction.addToBackStack) {
			BackStackEntry entry = new BackStackEntry(null, transaction.name);
			entry.fragment = fragment;
			backStack.push(entry);
		}

		checkFragmentsStatus();
	}

	void onActivityPostCreate() {
		for (Fragment fragment : fragments) {
			if (fragment.targetStatus == STATUS_CREATED_VIEW) {
				fragment.targetStatus = STATUS_PAUSED;
			}
		}
		checkFragmentsStatus();
	}

	void onActivityPostResume() {
		for (Fragment fragment : fragments) {
			if (fragment.targetStatus == STATUS_PAUSED && fragment.visible) {
				fragment.targetStatus = STATUS_RESUMED;
			}
		}
		checkFragmentsStatus();
	}

	private void checkFragmentsStatus() {
		for (Fragment fragment : fragments) {
			while (fragment.status < fragment.targetStatus) {
				switch (fragment.status) {
					case STATUS_NOT_INITIALIZED:
						fragment.onCreate(null);
						fragment.status = STATUS_CREATED;
						break;
					case STATUS_CREATED:
						Element el = DOM.getElementById(fragment.containerViewId);
						if (el == null) {
							Log.e(TAG, "View " + fragment.containerViewId + " not found");
						}
						ViewGroup viewGroup = new ViewGroup(el);
						fragment.mView = fragment.onCreateView(new LayoutInflater(), viewGroup, null);
						el.appendChild(fragment.mView.getElement());
						fragment.status = STATUS_CREATED_VIEW;
						break;
					case STATUS_CREATED_VIEW:
						fragment.onActivityCreated(null);
						fragment.status = STATUS_PAUSED; // DO NOT resume if we are destroying or pausing
						break;
					case STATUS_RESUMED:
						fragment.onPause();
						fragment.mView.setVisibility(View.GONE);
						fragment.status = STATUS_PAUSED;
						break;
					case STATUS_PAUSED:
						fragment.onDestroy();
						fragment.mView.getElement().removeFromParent();
						fragment.mView = null;
						fragment.status = STATUS_DESTROYED;
						break;
				}
			}

			if (fragment.status == STATUS_PAUSED && fragment.targetStatus == STATUS_RESUMED) {
				fragment.onResume();
				fragment.mView.setVisibility(View.VISIBLE);
				fragment.status = STATUS_RESUMED;
			}
		}
	}

	public void popBackStack() {
		if (backStack.size() > 0) {
			BackStackEntry backStackEntry = backStack.pop();
			Fragment fragment = backStackEntry.fragment;
			fragment.targetStatus = STATUS_DESTROYED;

			// Iterate in reverse to set visible the fragment under this one (with the same view id)
			ListIterator<Fragment> li = fragments.listIterator(fragments.size());
			while (li.hasPrevious()) {
				Fragment previousFragment = li.previous();
				if (previousFragment != fragment && previousFragment.containerViewId.equals(fragment.containerViewId)) {
					previousFragment.visible = true;
					if (previousFragment.targetStatus == STATUS_PAUSED) {
						previousFragment.targetStatus = STATUS_RESUMED;
					}
					break;
				}
			}
			checkFragmentsStatus();
			fragments.remove(fragment);
		}
	}

	/**
	 * Invokes onOptionsItemSelected in all visible fragments
	 *
	 * @param item
	 * @return
	 */
	boolean onOptionsItemSelected(MenuItem item) {
		for (Fragment fragment : fragments) {
			if (fragment.visible) {
				if (fragment.onOptionsItemSelected(item)) {
					return true;
				}
			}
		}
		return false;
	}
}
