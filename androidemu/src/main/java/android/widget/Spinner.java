package android.widget;

import android.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.DOM;

public class Spinner extends View {

	BaseAdapter adapter;

	public Spinner() {
		super(DOM.createSelect());
	}

	public Spinner(Element element) {
		super(element);
	}

	public void setSelection(int index) {
		SelectElement.as(element).setSelectedIndex(index);
	}

	public int getSelectedItemPosition() {
		int i = SelectElement.as(element).getSelectedIndex();
		return (i < 0 ? 0 : i);
	}

	public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
	}

	public void setAdapter(BaseAdapter adapter) {
		this.adapter = adapter;
		element.removeAllChildren();
		for (int i = 0; i < adapter.getCount(); i++) {
			Element optionsElement = DOM.createOption();
			optionsElement.setInnerHTML(String.valueOf(adapter.getItem(i)));
			element.appendChild(optionsElement);
		}
	}

	public BaseAdapter getAdapter() {
		return adapter;
	}
}
