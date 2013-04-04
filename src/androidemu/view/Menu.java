package androidemu.view;

import java.util.ArrayList;

public class Menu {

	public ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

	public void add(int groupId, int itemId, int order, String title) {
		MenuItem item = new MenuItem();
		item.setGroupId(groupId);
		item.setItemId(itemId);
		item.setOrder(order);
		item.setTitle(title);
		menuItems.add(item);
	}

}
