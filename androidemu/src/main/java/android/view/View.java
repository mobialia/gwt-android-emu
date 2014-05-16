package android.view;

import android.Res;
import android.content.res.Resources;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Widget;

public class View {
	static final String TAG = "View";
	public static final int VISIBLE = 0;
	public static final int INVISIBLE = 4;
	public static final int GONE = 8;

	public Element element = null;
	public Widget widget = null;

	public View(Element element) {
		this.element = element;
	}

	public View(Widget widget) {
		this.element = widget.getElement();
	}

	public String getId() {
		return getElement().getId();
	}

	/**
	 * In Android is relative, but here we implement it against the root of the
	 * document
	 */
	public int getTop() {
		return element.getAbsoluteTop();
	}

	/**
	 * In Android is relative, but here we implement it against the root of the
	 * document
	 */
	public int getLeft() {
		return element.getAbsoluteLeft();
	}

	public int getHeight() {
		return element.getClientHeight();
	}

	public int getWidth() {
		return element.getClientWidth();
	}

	public View findViewById(int id) {
		return ViewFactory.findViewById(getElement(), Resources.getResourceResolver().getIdAsString(id));
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
		return (element != null ? element : widget.getElement());
	}
}