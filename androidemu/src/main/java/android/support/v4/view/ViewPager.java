package android.support.v4.view;

import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;

import java.util.ArrayList;

public class ViewPager extends ViewGroup {
	static final String TAG = "ViewPager";

	public static interface OnPageChangeListener {
		abstract void onPageScrollStateChanged(int state);

		abstract void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

		abstract void onPageSelected(int position);
	}

	int currentItem = -1;
	ArrayList<Object> pages = new ArrayList<Object>();
	PagerAdapter adapter;
	OnPageChangeListener listener;

	public ViewPager(Element el) {
		super(el);
	}

	public int getCurrentItem() {
		return currentItem;
	}

	public void setAdapter(PagerAdapter adapter) {
		this.adapter = adapter;
		for (int i = 0; i < adapter.getCount(); i++) {
			pages.add(adapter.instantiateItem(this, i));
		}
	}

	public PagerAdapter getAdapter() {
		return adapter;
	}

	public void setCurrentItem(int item) {
		if (currentItem != item) {
			this.currentItem = item;
			adapter.setPrimaryItem(this, item, pages.get(item));
		}
	}

	public void setCurrentItem(int item, boolean smoothScroll) {
		setCurrentItem(item);
	}

	/**
	 * Unused, it always creates all the views
	 */
	public void setOffscreenPageLimit(int limit) {

	}

	public void setOnPageChangeListener(OnPageChangeListener listener) {
		this.listener = listener;
	}
}
