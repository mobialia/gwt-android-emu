package android.support.v4.app;


import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {

	Adapter adapter;
    ListView listView;

	public void onListItemClick(ListView l, View v, int position, long id) {

	}

	public void setListAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        listView = ((ListView) mView.findViewById(android.R.id.list));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemClick(listView, view, position, id);
            }
        });
        adapter.notifyDataSetChanged();
	}

}
