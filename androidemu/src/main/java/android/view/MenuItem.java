package android.view;

public class MenuItem {
	public static final int SHOW_AS_ACTION_NEVER = 0;
	public static final int SHOW_AS_ACTION_IF_ROOM = 1;
	public static final int SHOW_AS_ACTION_ALWAYS = 2;
	public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
	public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;

	int groupId;
	int itemId;
	int order;
	int title;
	int showAsAction;
	String icon;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public int getShowAsAction() {
		return showAsAction;
	}

	public void setShowAsAction(int showAsAction) {
		this.showAsAction = showAsAction;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
