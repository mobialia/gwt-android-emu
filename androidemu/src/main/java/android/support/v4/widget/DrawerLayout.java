package android.support.v4.widget;

import android.Res;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public class DrawerLayout extends ViewGroup {
	static final String TAG = DrawerLayout.class.getSimpleName();

	public static interface DrawerListener {

		abstract void onDrawerClosed(View drawerView);

		abstract void onDrawerOpened(View drawerView);

		abstract void onDrawerSlide(View drawerView, float slideOffset);

		abstract void onDrawerStateChanged(int newState);
	}

	View drawerOverlay, drawer;
	DrawerListener listener;
	boolean isOpen;

	public DrawerLayout(Element element) {
		super(element);
		for (int i = 0; i < element.getChildCount(); i++) {
			if (element.getChild(i) instanceof Element) {
				Element child = (Element) element.getChild(i);
				if ("DrawerOverLay".equals(child.getId())) {
					drawerOverlay = new View(child);
				} else if ("Drawer".equals(child.getId())) {
					drawer = new View(child);
				}
			}
		}
		drawerOverlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				closeDrawer(0);
			}
		});
		closeDrawer(0);
	}

	public void setDrawerListener(DrawerLayout.DrawerListener listener) {
		this.listener = listener;
	}

	public boolean isDrawerOpen(View drawer) {
		return isOpen;
	}

	public void openDrawer(int gravity) {
		drawerOverlay.getElement().addClassName(Res.R.style().drawerOverlayOpened());
		drawer.getElement().addClassName(Res.R.style().drawerOpened());
		if (listener != null) {
			listener.onDrawerOpened(drawer);
		}
		isOpen = true;
	}

	public void closeDrawer(int gravity) {
		drawerOverlay.getElement().removeClassName(Res.R.style().drawerOverlayOpened());
		drawer.getElement().removeClassName(Res.R.style().drawerOpened());
		if (listener != null) {
			listener.onDrawerClosed(drawer);
		}
		isOpen = false;
	}

}
