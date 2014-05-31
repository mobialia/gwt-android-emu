package android.demo.res.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class DemoFragmentActivity extends Composite {

	private static DemoFragmentActivityUiBinder uiBinder = GWT.create(DemoFragmentActivityUiBinder.class);

	interface DemoFragmentActivityUiBinder extends UiBinder<Widget, DemoFragmentActivity> {
	}

	public DemoFragmentActivity() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
