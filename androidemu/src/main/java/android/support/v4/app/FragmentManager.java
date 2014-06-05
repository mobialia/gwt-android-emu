package android.support.v4.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

	Fragment creatingViewForFragment;

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

	public Fragment findFragmentById(int id) {
		for (Fragment fragment : fragments) {
			if (fragment.containerViewId == id) {
				return fragment;
			}
		}
		return null;
	}

	public Fragment findFragmentByTag(String tag) {
		for (Fragment fragment : fragments) {
			if (tag.equals(fragment.tag)) {
				return fragment;
			}
		}
		return null;
	}

	void commit(FragmentTransaction transaction) {
		Fragment fragment = transaction.fragment;
		fragment.mActivity = activity;
		fragment.status = STATUS_NOT_INITIALIZED;
		if (activity.resumed) {
			fragment.targetStatus = STATUS_RESUMED;
		} else {
			fragment.targetStatus = STATUS_CREATED_VIEW;
		}
		fragments.add(fragment);

		if (transaction.addToBackStack) {
			BackStackEntry entry = new BackStackEntry(null, transaction.name);
			entry.fragment = fragment;
			backStack.push(entry);
		}

		// Hide any fragment with the same view ID
		if (transaction.replace) {
			showAndHideOtherFragments(fragment);
		} else {
			checkFragmentsStatus();
			activity.invalidateOptionsMenu();
		}
	}

	void showAndHideOtherFragments(Fragment fragment) {
		fragment.visible = true;
		for (Fragment otherFragment : fragments) {
			if (otherFragment != fragment && fragment.containerViewId == otherFragment.containerViewId) {
				otherFragment.visible = false;
				if (otherFragment.targetStatus == STATUS_RESUMED) {
					otherFragment.targetStatus = STATUS_PAUSED;
				}
			}
		}
		checkFragmentsStatus();
		activity.invalidateOptionsMenu();
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
		activity.invalidateOptionsMenu();
	}

	void onActivityPaused() {
		for (Fragment fragment : fragments) {
			if (fragment.targetStatus == STATUS_RESUMED && fragment.visible) {
				fragment.targetStatus = STATUS_PAUSED;
			}
		}
		checkFragmentsStatus();
	}

	void checkFragmentsStatus() {
		for (Fragment fragment : fragments) {
			checkFragmentStatus(fragment, false);
		}
	}

	public void checkFragmentStatus(Fragment fragment, boolean isChild) {
		//Log.d(TAG, fragment + " " + fragment.status + " => " + fragment.targetStatus);

		while (fragment.status < fragment.targetStatus) {
			switch (fragment.status) {
				case STATUS_NOT_INITIALIZED:
					fragment.onCreate(null);
					fragment.status = STATUS_CREATED;
					break;
				case STATUS_CREATED:
					if (!isChild) {
						creatingViewForFragment = fragment;
					}

					if (fragment.container == null) {
						View viewGroup = activity.view.findViewById(fragment.containerViewId);
						if (viewGroup == null) {
							Log.e(TAG, "View not found to attach fragment: " + Context.resources.getIdAsString(fragment.containerViewId));
						}
						if (!(viewGroup instanceof ViewGroup)) {
							Log.e(TAG, "Parent view is not a ViewGroup: " + viewGroup);
						}
						fragment.container = (ViewGroup) viewGroup;
					}
					fragment.view = fragment.onCreateView(LayoutInflater.from(activity), fragment.container, null);
					fragment.container.addView(fragment.view);
					fragment.status = STATUS_CREATED_VIEW;

					if (!isChild) {
						creatingViewForFragment = null;
					}
					break;
				case STATUS_CREATED_VIEW:
					fragment.onActivityCreated(null);
					fragment.status = STATUS_PAUSED; // DO NOT resume if we are destroying or pausing
					break;
				case STATUS_RESUMED:
					fragment.onPause();
					if (!isChild) {
						fragment.view.setVisibility(View.GONE);
					}
					fragment.status = STATUS_PAUSED;
					break;
				case STATUS_PAUSED:
					fragment.onDestroy();
					fragment.view.getElement().removeFromParent();
					fragment.view = null;
					fragment.status = STATUS_DESTROYED;
					break;
			}
		}

		if (fragment.status == STATUS_PAUSED && fragment.targetStatus == STATUS_RESUMED) {
			fragment.onResume();
			if (!isChild) {
				fragment.view.setVisibility(fragment.visible ? View.VISIBLE : View.GONE);
			}
			fragment.status = STATUS_RESUMED;
		}

		for (Fragment childFragment : fragment.childFragments) {
			childFragment.targetStatus = fragment.targetStatus;
			checkFragmentStatus(childFragment, true);
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
				if (previousFragment != fragment && previousFragment.containerViewId == fragment.containerViewId) {
					previousFragment.visible = true;
					if (previousFragment.targetStatus == STATUS_PAUSED) {
						previousFragment.targetStatus = STATUS_RESUMED;
					}
					break;
				}
			}
			checkFragmentsStatus();
			fragments.remove(fragment);

			activity.invalidateOptionsMenu();
		}
	}

	void onCreateOptionsMenu(Menu menu) {
		for (Fragment fragment : fragments) {
			if (fragment.visible && fragment.hasMenu) {
				fragment.onCreateOptionsMenu(menu, activity.getMenuInflater());
			}
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
