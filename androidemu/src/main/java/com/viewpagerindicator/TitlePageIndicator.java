package com.viewpagerindicator;

import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class TitlePageIndicator extends View implements PageIndicator {

	ViewPager mViewPager;

	public TitlePageIndicator(Element element) {
		super(element);
	}

	@Override
	public void setViewPager(ViewPager view) {
		if (mViewPager == view) {
			return;
		}
		if (mViewPager != null) {
			mViewPager.setOnPageChangeListener(null);
		}
		if (view.getAdapter() == null) {
			throw new IllegalStateException("ViewPager does not have adapter instance.");
		}
		mViewPager = view;
		mViewPager.setOnPageChangeListener(this);
		invalidate();
	}

	public void setTextColor(int color) {

	}

	public void setSelectedColor(int color) {

	}

	public void onPageScrollStateChanged(int state) {

	}

	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	public void onPageSelected(int position) {

	}

	public void setViewPager(ViewPager view, int initialPosition) {

	}

	public void setCurrentItem(int item) {

	}

	public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {

	}

	public void notifyDataSetChanged() {

	}

	@Override
	protected void onDraw(Canvas c) {
		element.removeAllChildren();
		for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
			Element e = DOM.createDiv();
			e.setInnerHTML(mViewPager.getAdapter().getPageTitle(i).toString());
			element.appendChild(e);
		}
	}

}
