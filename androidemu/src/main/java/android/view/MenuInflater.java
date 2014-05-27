package android.view;

import android.content.Context;

public class MenuInflater {

	public void inflate(int menuId, Menu menu) {
		Menu toAdd = Context.resources.getMenu(menuId);
		for (MenuItem item : toAdd.menuItems) {
			menu.add(item);
		}
	}
}
