package android.widget;

import android.content.Context;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class CheckBox extends CompoundButton {

	public CheckBox(Context context) {
		super(DOM.createInputCheck());
	}

	public CheckBox(Element element) {
		super(element);
	}

}