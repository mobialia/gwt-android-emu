package androidemu.widget;

import androidemu.database.DataSetObserver;
import androidemu.view.View;
import androidemu.widget.AdapterView.OnItemClickListener;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public class ListView extends View {

	Adapter mAdapter;
	OnItemClickListener listener;
	DataSetObserver mDataSetObserver;

	public ListView(Element element) {
		super(element);

		mDataSetObserver = new DataSetObserver() {
			@Override
			public void onChanged() {
				ListView.this.draw();
			}

			@Override
			public void onInvalidated() {
				ListView.this.draw();
			}
		};
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	public void setAdapter(Adapter adapter) {

		if (mAdapter != null && mDataSetObserver != null) {
			mAdapter.unregisterDataSetObserver(mDataSetObserver);
		}

		this.mAdapter = adapter;
		mAdapter.registerDataSetObserver(mDataSetObserver);
	}

	public void setSelection(int index) {
		// TODO
	}

	private void draw() {
		while (element.getFirstChild() != null) {
			element.removeChild(element.getFirstChild());
		}

		for (int i = 0; i < mAdapter.getCount(); i++) {
			final View v = mAdapter.getView(i, null, null);
			final int index = i;
			final long id = mAdapter.getItemId(i);

			Event.setEventListener(v.getElement(), new EventListener() {
				@Override
				public void onBrowserEvent(Event event) {
					if (listener != null) {
						listener.onItemClick(null, v, index, id);
					}
				}
			});
			Event.sinkEvents(v.getElement(), Event.ONCLICK);

			element.appendChild(v.getElement());
		}
	}
}
