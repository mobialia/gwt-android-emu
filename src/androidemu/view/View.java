package androidemu.view;

import com.google.gwt.dom.client.Element;

public class View {
	public Element element = null;
	
	public View(Element element) {
		this.element = element;
	}
	
	public String getId() {
		return element.getId();
	}

	public View findViewById(String id) {
		return ViewFactory.findViewById(element, id);
	}

	public static interface OnClickListener {
		abstract void onClick(View v);
	}
}