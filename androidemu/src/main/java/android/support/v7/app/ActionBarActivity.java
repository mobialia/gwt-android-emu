package android.support.v7.app;

import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ActionBarActivity extends FragmentActivity {

	ActionBar actionBar;

	public ActionBarActivity() {
		actionBar = new ActionBar(this);
	}

	public ActionBar getSupportActionBar() {
		return actionBar;
	}

	public void setContentView(Widget htmlPanel) {
		String id = HTMLPanel.createUniqueId();
		view = new LinearLayout(this);
		view.addView(actionBar.view);

		DOM.getElementById(ACTIVITY_ID).appendChild(view.getElement());
		view.getElement().setId(id);
		RootPanel.get(id).add(htmlPanel);
	}
}
