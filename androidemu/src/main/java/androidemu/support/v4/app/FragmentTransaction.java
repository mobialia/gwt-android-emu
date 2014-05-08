package androidemu.support.v4.app;

public class FragmentTransaction {

	FragmentManager fragmentManager;

	String containerViewId;
	Fragment fragment;
	String tag;

	public FragmentTransaction(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	public FragmentTransaction replace(String containerViewId, Fragment fragment, String tag) {
		this.containerViewId = containerViewId;
		this.fragment = fragment;
		this.tag = tag;
		return this;
	}

	public FragmentTransaction addToBackStack(String name) {
		fragmentManager.addToBackStack(name, this);
		return this;
	}

	public void commit() {
		fragmentManager.commit(this);
	}

}
