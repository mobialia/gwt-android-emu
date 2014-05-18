package android.widget;

import android.content.res.Resources;
import android.view.View;

import com.google.gwt.dom.client.Element;

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

	public native int getLineHeight()
	/*-{
		return parseInt(document.defaultView.getComputedStyle(this.@android.view.View::element,null).getPropertyValue("line-height"));
    }-*/;
}
