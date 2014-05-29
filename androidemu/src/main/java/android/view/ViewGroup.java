package android.view;

import com.google.gwt.dom.client.Element;

import java.util.ArrayList;

public class ViewGroup extends View {

	static final String TAG = "ViewGroup";

	ArrayList<View> childViews = new ArrayList<View>();

	public ViewGroup(Element element) {
		super(element);
	}

	public void addView(View v) {
		element.appendChild(v.getElement());
		childViews.add(v);
	}

	public View getChildAt(int index) {
		return childViews.get(index);
	}

	public int getChildCount() {
		return childViews.size();
	}

	public void removeAllViews() {
		element.removeAllChildren();
	}
}
