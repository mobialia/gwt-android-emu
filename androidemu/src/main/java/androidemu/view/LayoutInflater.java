package androidemu.view;

import androidemu.content.Context;

import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class LayoutInflater {

	public static LayoutInflater from(Context context) {
		return new LayoutInflater();
	}

	public View inflate(TextResource layout, Context ctx) {
		return new View((new HTML(layout.getText())).getElement());
	}

	public View inflate(Widget widget, Context ctx) {
		return new View(widget);
	}
}
