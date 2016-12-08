package android.demo.res.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SidebarAdapter extends Composite {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	public SidebarAdapter() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	interface MyUiBinder extends UiBinder<Widget, SidebarAdapter> {
	}

}
