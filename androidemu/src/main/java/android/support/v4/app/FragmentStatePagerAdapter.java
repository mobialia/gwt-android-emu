package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
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
		Log.d(TAG, "instantiateItem");
		Fragment fragment = getItem(position);
		fragmentManager.addFragment(container, fragment);
		return fragment;
	}

	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		Log.d(TAG, "setPrimaryItem");
		fragmentManager.showAndHideOtherFragments((Fragment) object);
	}
}
