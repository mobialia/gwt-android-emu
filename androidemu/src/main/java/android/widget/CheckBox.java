package android.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class CheckBox extends CompoundButton {

	public CheckBox() {
		super(DOM.createInputCheck());
	}

	public CheckBox(Element element) {
		super(element);
	}

}