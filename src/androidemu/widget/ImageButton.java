package androidemu.widget;

import com.google.gwt.dom.client.Element;

public class ImageButton extends Button {

	public ImageButton(Element element) {
		super(element);
	}

	public void setImageResource(String image) {
		element.setAttribute("src", image);
	}

}
