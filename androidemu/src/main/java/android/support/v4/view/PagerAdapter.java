package android.support.v4.view;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;

public abstract class PagerAdapter {

	public abstract int getCount();

	public abstract Fragment getItem(int position);

	public abstract CharSequence getPageTitle(int position);

	public Object instantiateItem(ViewGroup container, int position) {
		return null;
	}

	public void setPrimaryItem(ViewGroup container, int position, Object object) {

	}
}
