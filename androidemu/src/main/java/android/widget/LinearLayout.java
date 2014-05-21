package android.widget;

import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class LinearLayout extends ViewGroup {

	public LinearLayout() {
		super(DOM.createDiv());
	}

	public LinearLayout(Element element) {
		super(element);
	}

}
