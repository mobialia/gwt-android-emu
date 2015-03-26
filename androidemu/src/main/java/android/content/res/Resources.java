package android.content.res;


import android.view.Menu;

import com.google.gwt.user.client.ui.Widget;

public class Resources {
	static final String TAG = "Resources";

	public String getIdAsString(int id) {
		switch (id) {
			case android.R.id.list:
				return "list";
		}
		return null;
	}

	public String getString(int id) {
		return null;
	}

	public String[] getStringArray(int id) {
		return null;
	}

	public CharSequence getText(int id) {
		return getString(id);
	}

	public Menu getMenu(int id) {
		return null;
	}

	public int getColor(int id) {
		return 0;
	}

	public String getDrawable(int id) {
		switch (id) {
			case android.R.drawable.actionbar_menu:
				return "actionbar_menu";
			case android.R.drawable.actionbar_indicator_back:
				return "actionbar_indicator_back";
			case android.R.drawable.ic_drawer:
				return "ic_drawer";
		}
		return null;
	}

	public Widget getLayout(int id) {
		return null;
	}

}
