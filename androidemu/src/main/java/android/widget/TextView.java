package android.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.Spanned;
import android.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class TextView extends View {

	public TextView(Context context) {
		super(DOM.createDiv());
	}

	public TextView(Element element) {
		super(element);
	}

	public String getText() {
		return element.getInnerText();
	}

	public void setText(int stringId) {
		setText(Context.resources.getString(stringId));
	}

	public void setText(CharSequence text) {
		if (text == null) {
			element.setInnerHTML("");
		} else if (text instanceof Spanned) {
			element.setInnerHTML(text.toString());
		} else {
			element.setInnerHTML(text.toString().replace("\n", "<br/>"));
		}
	}

	public void setTextColor(int color) {
		element.getStyle().setProperty("color", Color.getHtmlColor(color));
	}

	public native int getLineHeight()
	/*-{
		return parseInt(document.defaultView.getComputedStyle(this.@android.view.View::element,null).getPropertyValue("line-height"));
    }-*/;
}
