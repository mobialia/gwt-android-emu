/**
 * FILE GENERATED AUTOMATICALLY BY GWT_ANDROID_EMU'S GenerateResources: DO NOT EDIT MANUALLY
 */
package android.demo.res;

import android.demo.R;
import android.view.Menu;
import android.view.MenuItem;

public class Menus {
	public static Menu demo_menu() {
		Menu menu = new Menu();
		MenuItem item0 = menu.add(0, R.id.menu_add, 0, 0);
		item0.setIcon(R.drawable.ic_add);
		item0.setShowAsAction(2);
		MenuItem item1 = menu.add(0, R.id.menu_action1, 0, R.string.menu_option1);
		MenuItem item2 = menu.add(0, R.id.menu_action2, 0, R.string.menu_option2);
		return menu;
	}
}
