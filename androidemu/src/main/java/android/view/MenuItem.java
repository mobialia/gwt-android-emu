package android.view;

public class MenuItem {

	int groupId;
	String itemId;
	int order;
	String title;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getItemId() {
		return itemId;
	}

    public void setItemId(int itemId) {
        this.itemId = String.valueOf(itemId);
    }

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

    public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
