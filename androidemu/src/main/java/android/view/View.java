package android.view;

import android.AndroidManifest;
import android.Res;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public class View {
	static final String TAG = "View";
	public static final int VISIBLE = 0;
	public static final int INVISIBLE = 4;
	public static final int GONE = 8;

	public static final int FOCUS_DOWN = 130;
	public static final int FOCUS_UP = 33;

	public Element element = null;
	int id;

	public View(Element element) {
		this.element = element;
	}

	public View(Widget widget) {
		this.element = widget.getElement();
	}

	public final Context getContext() {
		return AndroidManifest.applicatonContext;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTop() {
		return element.getOffsetTop();
	}

	public int getLeft() {
		return element.getOffsetLeft();
	}

	public int getRight() {
		return element.getOffsetLeft() + element.getClientWidth();
	}

	public int getBottom() {
		return element.getOffsetTop() + element.getClientHeight();
	}

	public int getHeight() {
		return element.getClientHeight();
	}

	public int getWidth() {
		return element.getClientWidth();
	}

	public void getLocationOnScreen(int[] location) {
		location[0] = element.getAbsoluteLeft() - Window.getScrollLeft();
		location[1] = element.getAbsoluteTop() - Window.getScrollTop();
	}

	public View findViewById(int id) {
		View view = ViewFactory.findViewById(getElement(), Context.resources.getIdAsString(id));
		if (view != null) {
			view.setId(id);
		}
		return view;
	}

	public View findViewById(String id) {
		return ViewFactory.findViewById(getElement(), id);
	}

	public void setVisibility(int visibility) {
		if ((visibility & View.INVISIBLE) != 0) {
			element.addClassName(Res.R.style().invisible());
		} else {
			element.removeClassName(Res.R.style().invisible());
		}

		if ((visibility & View.GONE) != 0) {
			element.addClassName(Res.R.style().gone());
		} else {
			element.removeClassName(Res.R.style().gone());
		}
	}

	public int getVisibility() {
		if (element.hasClassName(Res.R.style().invisible())) {
			return View.INVISIBLE;
		}
		if (element.hasClassName(Res.R.style().gone())) {
			return View.GONE;
		}
		return View.VISIBLE;
	}

	public void setEnabled(boolean enabled) {
		if (enabled) {
			element.removeAttribute("disabled");
		} else {
			element.setAttribute("disabled", "disabled");
		}
	}

	public void setOnClickListener(final OnClickListener listener) {
		Event.setEventListener(element, new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				listener.onClick(View.this);
			}
		});
		Event.sinkEvents(element, Event.ONCLICK);
	}

	public static interface OnClickListener {
		abstract void onClick(View v);
	}

	public Element getElement() {
		return element;
	}

	/**
	 * No JS Threads, so run now
	 *
	 * @param action
	 * @return
	 */
	public boolean post(Runnable action) {
		action.run();
		return true;
	}

	public void invalidate() {
		onDraw(null);
	}

	protected void onDraw(Canvas canvas) {

	}

	public void setBackgroundColor(int color) {
		element.getStyle().setProperty("background", Color.getHtmlColor(color));
	}

	@Override
	public boolean equals(Object v) {
		if (!(v instanceof View)) {
			return false;
		}
		return ((View) v).id == id;
	}
}