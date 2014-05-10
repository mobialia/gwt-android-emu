package android.support.v4.app;


import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

public class ListFragment extends Fragment {

	Adapter adapter;

	public void onListItemClick(ListView l, View v, int position, long id) {

	}

	public void setListAdapter(Adapter adapter) {
		this.adapter = adapter;
	}

}
