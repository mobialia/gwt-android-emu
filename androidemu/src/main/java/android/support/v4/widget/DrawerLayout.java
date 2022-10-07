package android.support.v4.widget;

import android.Res;
import android.view.View;
import android.view.ViewGroup;
import com.google.gwt.dom.client.Element;

public class DrawerLayout extends ViewGroup {
	static final String TAG = DrawerLayout.class.getSimpleName();

	public interface DrawerListener {

		void onDrawerClosed(View drawerView);

		void onDrawerOpened(View drawerView);

		void onDrawerSlide(View drawerView, float slideOffset);

		void onDrawerStateChanged(int newState);
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
		drawerOverlay.setOnClickListener(v -> closeDrawer(0));
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
