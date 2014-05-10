package android.widget;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;

import android.view.View;

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
}
