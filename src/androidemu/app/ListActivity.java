package androidemu.app;

import androidemu.widget.ListAdapter;
import androidemu.widget.ListView;

public class ListActivity extends Activity {

	ListView listView;
	ListAdapter adapter;
	Integer position;

	/**
	 * Get the ListAdapter associated with this activity's ListView.
	 */
	public ListAdapter getListAdapter() {
		return adapter;
	}

	/**
	 * Get the activity's list view widget.
	 */
	public ListView getListView() {
		return listView;
	}

	/**
	 * Get the cursor row ID of the currently selected list item.
	 */
	public long getSelectedItemId() {
		if (position != null) {
			return adapter.getItemId(position);
		}
		return 0;
	}

	/**
	 * Get the position of the currently selected list item.
	 */
	public int getSelectedItemPosition() {
		if (position != null) {
			return position;
		}
		return 0;
	}

	/**
	 * Updates the screen state (current list and other views) when the content
	 * changes.
	 */
	public void onContentChanged() {

	}

	/**
	 * Provide the cursor for the list view.
	 */
	public void setListAdapter(ListAdapter adapter) {
		this.adapter = adapter;
	}

	/**
	 * Set the currently selected list item to the specified position with the
	 * adapter's data
	 */
	public void setSelection(int position) {
		this.position = position;
	}
}