package android.view;

import android.content.res.Resources;

public class MenuInflater {

	public void inflate(int menuId, Menu menu) {
		Menu toAdd = Resources.getResourceResolver().getMenu(menuId);
		for (MenuItem item : toAdd.menuItems) {
			menu.add(item);
		}
	}
}
