package android.support.v7.app;

import android.content.res.Resources;
import com.google.gwt.user.client.DOM;

public class ActionBar {

	public void setTitle(int title) {
		setTitle(Resources.getResourceResolver().getString(title));
	}

	public void setTitle(String title) {
		DOM.getElementById("ActionBarTitle").setInnerHTML(title);
	}

	public void setDisplayHomeAsUpEnabled(boolean displayHomeAsUpEnabled) {

	}

	public void setHomeButtonEnabled(boolean homeButtonEnabled) {

	}
}
