package android.widget;

import android.content.Context;
import android.graphics.Color;
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
		setText(Context.resources.getString(stringId));
	}

	public void setText(String string) {
		element.setInnerHTML(string != null ? string.replace("\n", "<br/>") : "");
	}

	public void setTextColor(int color) {
		element.getStyle().setProperty("color", Color.getHtmlColor(color));
	}

	public native int getLineHeight()
	/*-{
		return parseInt(document.defaultView.getComputedStyle(this.@android.view.View::element,null).getPropertyValue("line-height"));
    }-*/;
}
