package android.widget;

import android.content.res.Resources;
import com.google.gwt.dom.client.Element;

import android.view.View;

public class TextView extends View {

	public TextView(Element element) {
		super(element);
	}

	public String getText() {
		return element.getInnerText();
	}

	public void setText(int stringId) {
		setText(Resources.getResourceResolver().getString(stringId));
	}

	public void setText(String string) {
		element.setInnerHTML(string != null ? string.replace("\n", "<br/>") : "");
	}

	public void setTextColor(String color) {
		element.setAttribute("style", "color: " + color + ";");
	}
}
