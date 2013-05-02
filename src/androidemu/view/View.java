package androidemu.view;

import androidemu.Res;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class View {

	public static final int VISIBLE = 0;
	public static final int INVISIBLE = 4;
	public static final int GONE = 8;

	public Element element = null;
	public Widget widget = null;

	public View(Element element) {
		this.element = element;
	}

	public View(Widget widget) {
		this.widget = widget;
	}

	public String getId() {
		return getElement().getId();
	}

	public int getHeight() {
		return element.getClientHeight();
	}

	public int getWidth() {
		return element.getClientWidth();
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

	public static interface OnClickListener {
		abstract void onClick(View v);
	}

	public Element getElement() {
		return (element != null ? element : widget.getElement());
	}
}