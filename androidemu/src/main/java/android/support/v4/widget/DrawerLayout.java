package android.support.v4.widget;

import android.view.View;
import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;


public class DrawerLayout extends ViewGroup {

	public static interface DrawerListener {

		abstract void onDrawerClosed(View drawerView);

		abstract void onDrawerOpened(View drawerView);

		abstract void onDrawerSlide(View drawerView, float slideOffset);

		abstract void onDrawerStateChanged(int newState);
	}

	public DrawerLayout(Element element) {
		super(element);
	}

	public void setDrawerListener(DrawerLayout.DrawerListener listener) {

	}

	public void openDrawer(int gravity) {

	}

	public void closeDrawer(int gravity) {

	}

}
