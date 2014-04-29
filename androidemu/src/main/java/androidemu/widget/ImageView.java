package androidemu.widget;

import com.google.gwt.dom.client.Element;

import androidemu.view.View;

public class ImageView extends View {

	public ImageView(Element element) {
		super(element);
	}

	public void setImageResource(String image) {
		element.setAttribute("src", image);
	}

}
