package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {

	static final String TAG = "FragmentStatePagerAdapter";

	FragmentManager fragmentManager;

	public FragmentStatePagerAdapter(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

	public abstract Fragment getItem(int position);

	public void restoreState(Parcelable state) {
	}

	public Parcelable saveState() {
		return null;
	}

	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = getItem(position);
		fragment.mActivity = fragmentManager.creatingViewForFragment.mActivity;
		fragment.containerViewId = container.getId();
		fragment.container = container;
		fragment.targetStatus = FragmentManager.STATUS_CREATED_VIEW;
		fragmentManager.checkFragmentStatus(fragment, true);
		fragmentManager.creatingViewForFragment.childFragments.add(fragment);
		return fragment;
	}

	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		((Fragment) object).view.setVisibility(View.VISIBLE);
		for (int i = 0; i < container.getChildCount(); i++) {
			if (container.getChildAt(i) != null) {
				container.getChildAt(i).setVisibility(i == position ? View.VISIBLE : View.GONE);
			}
		}
	}
}
