package android.support.v4.app;

public class FragmentTransaction {

	FragmentManager fragmentManager;

	int containerViewId;
	Fragment fragment;
	String tag;
	String name;
	boolean replace = true;
	boolean addToBackStack = false;

	public FragmentTransaction(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	public FragmentTransaction add(int containerViewId, Fragment fragment) {
		return replace(containerViewId, fragment, null);
	}

	public FragmentTransaction add(int containerViewId, Fragment fragment, String tag) {
		this.replace = false;
		this.containerViewId = containerViewId;
		this.fragment = fragment;
		this.tag = tag;
		return this;
	}

	public FragmentTransaction replace(int containerViewId, Fragment fragment) {
		return replace(containerViewId, fragment, null);
	}

	public FragmentTransaction replace(int containerViewId, Fragment fragment, String tag) {
		this.replace = true;
		this.containerViewId = containerViewId;
		this.fragment = fragment;
		this.tag = tag;
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
