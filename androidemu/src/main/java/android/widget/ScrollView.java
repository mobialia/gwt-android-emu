package android.widget;

import android.view.View;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;

public class ScrollView extends View {

	public ScrollView(Element element) {
		super(element);
	}

	public void scrollTo(int x, int y) {
		DivElement.as(element).setScrollLeft(x);
		DivElement.as(element).setScrollTop(y);
	}

	public int getScrollX() {
		return DivElement.as(element).getScrollLeft();
	}

	public int getScrollY() {
		return DivElement.as(element).getScrollTop();
	}

	public boolean fullScroll(int direction) {
		if (direction == View.FOCUS_UP) {
			DivElement.as(element).setScrollTop(0);
		} else if (direction == View.FOCUS_DOWN) {
			DivElement.as(element).setScrollTop(9999999);
		}
		return true;
	}
}
