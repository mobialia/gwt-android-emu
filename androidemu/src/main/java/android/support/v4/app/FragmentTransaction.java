package android.support.v4.app;

public class FragmentTransaction {

	FragmentManager fragmentManager;
	Fragment fragment;
	String name;
	boolean replace = true;
	boolean addToBackStack = false;

	public FragmentTransaction(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	public FragmentTransaction add(int containerViewId, Fragment fragment) {
		return add(containerViewId, fragment, null);
	}

	public FragmentTransaction add(int containerViewId, Fragment fragment, String tag) {
		this.replace = false;
		this.fragment = fragment;
		fragment.containerViewId = containerViewId;
		fragment.tag = tag;
		return this;
	}

	public FragmentTransaction replace(int containerViewId, Fragment fragment) {
		return replace(containerViewId, fragment, null);
	}

	public FragmentTransaction replace(int containerViewId, Fragment fragment, String tag) {
		this.replace = true;
		this.fragment = fragment;
		fragment.containerViewId = containerViewId;
		fragment.tag = tag;
		return this;
	}

	public FragmentTransaction addToBackStack(String name) {
		this.addToBackStack = true;
		this.name = name;
		return this;
	}

	public void commit() {
		fragmentManager.commit(this);
	}

}
