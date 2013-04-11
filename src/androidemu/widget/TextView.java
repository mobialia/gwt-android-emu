package androidemu.widget;

import androidemu.view.View;

import com.google.gwt.user.client.Element;

public class TextView extends View {

	public TextView(Element element) {
		super(element);
	}

	public Object getText() {
		return element.getInnerText();
	}

	public void setText(String string) {
		element.setInnerHTML(string.replace("\n", "<br/>"));
	}

}
