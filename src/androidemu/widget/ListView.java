package androidemu.widget;

import androidemu.widget.AdapterView.OnItemClickListener;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ListView extends Widget {

	RootPanel panel;
	Adapter adapter;
	OnItemClickListener listener;

	public ListView(RootPanel panel) {
		this.panel = panel;
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

	// TODO wrap is a better method name
	public static ListView createFromId(String id) {
		return new ListView(RootPanel.get(id));
	}
}
