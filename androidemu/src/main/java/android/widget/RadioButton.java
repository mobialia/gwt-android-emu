package android.widget;

import android.content.Context;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class RadioButton extends CompoundButton {

	public RadioButton(Context context) {
		super(DOM.createElement("input"));
		element.setAttribute("type", "radio");
	}

	public RadioButton(Element element) {
		super(element);
	}

}
