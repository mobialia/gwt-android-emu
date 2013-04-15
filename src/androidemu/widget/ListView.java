package androidemu.widget;

import androidemu.view.View;
import androidemu.widget.AdapterView.OnItemClickListener;

import com.google.gwt.dom.client.Element;

public class ListView extends View {

	Adapter adapter;
	OnItemClickListener listener;

	public ListView(Element element) {
		super(element);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
	}

	public void setSelection(int index) {
		// TODO
	}
}
