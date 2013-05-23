package androidemu.demo.res.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Main extends Composite {

	private static IntroUiBinder uiBinder = GWT.create(IntroUiBinder.class);

	interface IntroUiBinder extends UiBinder<Widget, Main> {
	}

	public Main() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
