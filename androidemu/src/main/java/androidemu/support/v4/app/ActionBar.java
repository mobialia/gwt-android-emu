package androidemu.support.v4.app;

import com.google.gwt.user.client.DOM;

public class ActionBar {

	public void setTitle(String title) {
		DOM.getElementById("ActionBarTitle").setInnerHTML(title);
	}

	public void setDisplayHomeAsUpEnabled(boolean displayHomeAsUpEnabled) {

	}

	public void setHomeButtonEnabled(boolean homeButtonEnabled) {

	}
}
