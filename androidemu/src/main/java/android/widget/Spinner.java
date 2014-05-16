package android.widget;

import android.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SelectElement;

public class Spinner extends View {

	public Spinner(Element element) {
		super(element);
	}

	public void setSelection(int index) {
		SelectElement.as(element).setSelectedIndex(index);
	}

	public int getSelectedItemPosition() {
		return SelectElement.as(element).getSelectedIndex();
	}

	public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
	}

}
