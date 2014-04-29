package androidemu.widget;

import com.google.gwt.dom.client.Element;

import androidemu.view.View;

public class TextView extends View {

	public TextView(Element element) {
		super(element);
	}

	public String getText() {
		return element.getInnerText();
	}

	public void setText(String string) {
		element.setInnerHTML(string != null ? string.replace("\n", "<br/>") : "");
	}

	public void setTextColor(String color) {
		element.setAttribute("style", "color: " + color + ";");
	}
}
