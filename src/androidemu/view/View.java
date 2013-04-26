package androidemu.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class View {
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

	public View findViewById(String id) {
		return ViewFactory.findViewById(getElement(), id);
	}

	public static interface OnClickListener {
		abstract void onClick(View v);
	}

	public Element getElement() {
		return (element != null ? element : widget.getElement());
	}
}