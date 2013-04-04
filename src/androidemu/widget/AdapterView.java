package androidemu.widget;

import androidemu.view.View;

public class AdapterView<T extends Adapter> {

	public interface OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3);
	}
}
