package android.widget;

import android.content.Context;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class ImageButton extends ImageView {

	public ImageButton(Context context) {
		super(DOM.createElement("input"));
		element.setAttribute("type", "image");
	}

	public ImageButton(Element element) {
		super(element);
	}

}
