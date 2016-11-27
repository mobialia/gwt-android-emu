package android.support.v4.widget;

import android.view.View;
import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;

public class DrawerLayout extends ViewGroup {

	static final String TAG = "DrawerLayout";

	public static interface DrawerListener {

		abstract void onDrawerClosed(View drawerView);

		abstract void onDrawerOpened(View drawerView);

		abstract void onDrawerSlide(View drawerView, float slideOffset);

		abstract void onDrawerStateChanged(int newState);
	}

	º View
	drawerOverlay,drawer;
	DrawerListener listener;
	boolean isOpen;

	public DrawerLayout(Element element) {
		super(element);
		int counter = 0;
		for (int i = 0; i < element.getChildCount(); i++) {
			if (element.getChild(i).getNodeName().equals("DIV")) {
				switch (++counter) {
					case 2:
						drawerOverlay = new View((Element) element.getChild(i));
						break;
					case 3:
						drawer = new View((Element) element.getChild(i));
						break;
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
		drawerOverlay.getElement().addClassName("drawerOverlayOpened");
		drawer.getElement().addClassName("drawerOpened");
		if (listener != null) {
			listener.onDrawerOpened(drawer);
		}
		isOpen = true;
	}

	public void closeDrawer(int gravity) {
		drawerOverlay.getElement().removeClassName("drawerOverlayOpened");
		drawer.getElement().removeClassName("drawerOpened");
		if (listener != null) {
			listener.onDrawerClosed(drawer);
		}
		isOpen = false;
	}

}
