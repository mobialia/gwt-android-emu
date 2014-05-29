package com.viewpagerindicator;

import android.Res;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gwt.dom.client.Element;

public class TitlePageIndicator extends ViewGroup implements PageIndicator {

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
		for (int i = 0; i < getChildCount(); i++) {
			if (i == position) {
				getChildAt(i).element.addClassName(Res.R.style().titlePagerElementSelected());
			} else {
				getChildAt(i).element.removeClassName(Res.R.style().titlePagerElementSelected());
			}
		}
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
		element.addClassName(Res.R.style().titlePager());
		element.removeAllChildren();
		for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
			TextView b = new TextView((Context) null);
			b.element.addClassName(Res.R.style().titlePagerElement());
			if (i == mViewPager.getCurrentItem()) {
				b.element.addClassName(Res.R.style().titlePagerElementSelected());
			}
			b.setText(mViewPager.getAdapter().getPageTitle(i).toString());
			addView(b);

			final int position = i;
			b.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mViewPager.setCurrentItem(position, true);
				}
			});
		}
	}

}
