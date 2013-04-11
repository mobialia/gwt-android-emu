package androidemu.view;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;

public class View {
	public Element element;
	
	public View(Element element) {
		this.element = element;
	}
	
	public View findViewById(String id) {
		return ViewFactory.findViewById(id);
	}

	public Panel asPanel() {
		return new HTMLPanel(element.getInnerHTML());
	}
}