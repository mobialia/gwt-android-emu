package android.widget;

import android.content.Context;
import android.view.View;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;

public class Spinner extends View {

	BaseAdapter adapter;

	public Spinner(Context ctx) {
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
		return (Math.max(i, 0));
	}

	public void setOnItemSelectedListener(final AdapterView.OnItemSelectedListener listener) {
		Event.setEventListener(element, event -> {
			int i = getSelectedItemPosition();
			listener.onItemSelected(null, Spinner.this, i, i);
		});
		Event.sinkEvents(element, Event.ONCHANGE);
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
