package androidemu.view;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class View {
	public Widget widget;
	
	public View(Widget widget) {
		this.widget = widget; 
	}
	
	public Widget findViewById(String id) {
		return RootPanel.get(id);
	}
}
