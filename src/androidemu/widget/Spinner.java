package androidemu.widget;

import com.google.gwt.user.client.ui.ListBox;

public class Spinner extends ListBox {

	public void setSelection(int index) {
		setSelectedIndex(index);
	}

	public int getSelectedItemPosition() {
		return getSelectedIndex();
	}

}
