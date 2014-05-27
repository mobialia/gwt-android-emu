package android.widget;

import android.content.Context;
import android.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class ImageView extends View {

	public ImageView() {
		super(DOM.createImg());
	}

	public ImageView(Element element) {
		super(element);
	}

	public void setImageResource(int imageId) {
		element.setAttribute("src", "img/" + Context.resources.getDrawable(imageId) + ".png");
	}

}
