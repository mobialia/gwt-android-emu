package androidemu.widget;

import androidemu.view.View;

public abstract class AdapterView<T extends Adapter> {

	public static final int ITEM_VIEW_TYPE_IGNORE = -1;

	public interface OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3);
	}
}
