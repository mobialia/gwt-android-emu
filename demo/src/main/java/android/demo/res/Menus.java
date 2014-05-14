package android.demo.res;

import android.view.Menu;
import android.view.MenuItem;

public class Menus {
	public static Menu demo_menu() {
		Menu menu = new Menu();
		MenuItem item0 = menu.add(0, R.id.menu1, 0, R.string.menu1);
		MenuItem item1 = menu.add(0, R.id.menu2, 0, R.string.menu2);
		return menu;
	}

}
