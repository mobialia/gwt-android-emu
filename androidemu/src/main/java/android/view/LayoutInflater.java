package android.view;

import android.content.res.Resources;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import android.content.Context;

public class LayoutInflater {

	public static LayoutInflater from(Context context) {
		return new LayoutInflater();
	}

	public View inflate(TextResource layout, Context ctx) {
		return new View((new HTML(layout.getText())).getElement());
	}

	public View inflate(int resId, Context ctx) {
		return new View(Resources.getResourceResolver().getLayout(resId));
	}

	public View inflate(Widget widget, Context ctx) {
		return new View(widget);
	}

	public View inflate(int resId, ViewGroup container, boolean something) {
		return new View(Resources.getResourceResolver().getLayout(resId));
	}

	public View inflate(Widget widget, ViewGroup container, boolean something) {
		return new View(widget);
	}
}
