package android.demo.res.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class DemoFragment extends Composite {

	private static DemoFragmentUiBinder uiBinder = GWT.create(DemoFragmentUiBinder.class);

	interface DemoFragmentUiBinder extends UiBinder<Widget, DemoFragment> {
	}

	public DemoFragment() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
