/**
 * FILE GENERATED AUTOMATICALLY BY GWT_ANDROID_EMU'S GenerateResources: DO NOT EDIT MANUALLY
 */
package android.demo.res;

import android.view.Menu;
import android.view.MenuItem;
import android.demo.R;

public class Menus {
	public static Menu demo_menu() {
		Menu menu = new Menu();
		MenuItem item0 = menu.add(0, R.id.action1, 0, 0);
		item0.setIcon(R.drawable.ic_add);
		item0.setShowAsAction(2);
		MenuItem item1 = menu.add(0, R.id.menu1, 0, R.string.menu1);
		MenuItem item2 = menu.add(0, R.id.menu2, 0, R.string.menu2);
		return menu;
	}
	public static Menu fragment_menu() {
		Menu menu = new Menu();
		MenuItem item0 = menu.add(0, R.id.menu_launch_fragment, 0, R.string.menu_launch_fragment);
		return menu;
	}
}
