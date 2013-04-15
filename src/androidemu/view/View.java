package androidemu.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Window;

public class View {
	public Element element = null;
	
	public View(Element element) {
		this.element = element;
	}
	
	public String getId() {
		return element.getId();
	}

	private Element getElementById(Element element, String id) {
		if (id.equals(element.getId())) {
			return element;
		}
		Element child = element.getFirstChildElement();
		if (child != null) {
			Element out = getElementById(child, id);
			if (out != null) {
				return out;
			}
		}
		Element next = element.getNextSiblingElement();
		if (next != null) {
			Element out = getElementById(next, id);
			if (out != null) {
				return out;
			}
		}
		return null;
	}

	public View findViewById(String id) {
		Element elementFound = getElementById(element, id);

		if (elementFound == null) {
			Window.alert("View not found: " + id);
			return null;
		}
		return ViewFactory.createViewFromElement(elementFound);
	}

	public static interface OnClickListener {
		abstract void onClick(View v);
	}
}