package android.view;

import android.Res;
import android.content.res.Resources;
import android.graphics.Color;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * In Android is relative, but here we implement it against the root of the
	 * document
	 */
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

	public View findViewById(int id) {
		View view = ViewFactory.findViewById(getElement(), Resources.getResourceResolver().getIdAsString(id));
		view.setId(id);
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

	/**
	 * View is always redrawed, no invalidate at the moment
	 */
	public void invalidate() {

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