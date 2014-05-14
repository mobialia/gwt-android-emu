package android.support.v4.app;

import android.content.res.Resources;

public class FragmentTransaction {

	FragmentManager fragmentManager;

	String containerViewId;
	Fragment fragment;
	String tag;
	String name;
	boolean replace = true;
	boolean addToBackStack = false;

	public FragmentTransaction(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	public FragmentTransaction replace(int containerViewId, Fragment fragment, String tag) {
		this.replace = true;
		this.containerViewId = Resources.getResourceResolver().getIdAsString(containerViewId);
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
