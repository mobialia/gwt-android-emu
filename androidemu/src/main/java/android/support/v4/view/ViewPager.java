package android.support.v4.view;

import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;

import java.util.ArrayList;

public class ViewPager extends ViewGroup {
	static final String TAG = "ViewPager";

	int currentItem = 0;
	ArrayList<Object> pages = new ArrayList<Object>();
	PagerAdapter adapter;

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
}
