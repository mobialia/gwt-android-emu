package androidemu.support.v4.app;


import androidemu.view.View;
import androidemu.widget.Adapter;
import androidemu.widget.ListView;

public class ListFragment extends Fragment {

	Adapter adapter;

	public void onListItemClick(ListView l, View v, int position, long id) {

	}

	public void setListAdapter(Adapter adapter) {
		this.adapter = adapter;
	}

}
