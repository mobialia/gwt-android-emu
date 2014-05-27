package android.demo.res.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Other extends Composite {

	private static OtherUiBinder uiBinder = GWT.create(OtherUiBinder.class);

	interface OtherUiBinder extends UiBinder<Widget, Other> {
	}

	public Other() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
