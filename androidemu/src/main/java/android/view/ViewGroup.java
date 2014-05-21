package android.view;

import com.google.gwt.dom.client.Element;

public class ViewGroup extends View {

	public ViewGroup(Element element) {
		super(element);
	}

	public void addView(View v) {
		element.appendChild(v.getElement());
	}

	public void removeAllViews() {
		element.removeAllChildren();
	}
}
