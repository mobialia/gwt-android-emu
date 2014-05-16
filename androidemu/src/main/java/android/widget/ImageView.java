package android.widget;

import android.view.View;

import com.google.gwt.dom.client.Element;

public class ImageView extends View {

	public ImageView(Element element) {
		super(element);
	}

	public void setImageResource(String image) {
		element.setAttribute("src", image);
	}

}
