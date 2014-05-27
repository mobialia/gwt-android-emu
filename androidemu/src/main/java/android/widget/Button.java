package android.widget;

import android.content.Context;
import android.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class Button extends View {

	public Button() {
		super(DOM.createButton());
	}

	public Button(Element element) {
		super(element);
	}

	public void setText(int stringId) {
		setText(Context.resources.getString(stringId));
	}

	public void setText(String string) {
		element.setInnerHTML(string != null ? string.replace("\n", "<br/>") : "");
	}
}
