package android.support.v4.view;

import android.view.View;
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
	public ArrayList<Object> pages = new ArrayList<Object>();
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

	public void addView(View v) {
		if (currentItem != getChildCount()) {
			v.setVisibility(View.GONE);
		}
		super.addView(v);
	}

	public PagerAdapter getAdapter() {
		return adapter;
	}

	public void setCurrentItem(int position) {
		if (currentItem != position) {
			this.currentItem = position;
			adapter.setPrimaryItem(this, position, pages.get(position));

			if (listener != null) {
				listener.onPageSelected(position);
			}
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
