package android.app;

import android.Res;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.ImageButton;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Activity extends Context {

	public static final String TAG = "Activity";

	public static final int RESULT_CANCELED = 0;
	public static final int RESULT_FIRST_USER = 1;
	public static final int RESULT_OK = -1;

	public static final String ACTIVITY_ID = "activity";

	int status = 0;
	int targetStatus = 0;

	String title;
	Widget contentPanel;
	private Element menuPanel;

	Intent intent;
	Integer requestCode;
	int resultCode = RESULT_OK;
	Intent resultData;

	// Data when we return from another activity
	Integer returnRequestCode;
	int returnResultCode;
	Intent returnResultData;

	protected void onCreate(Bundle savedInstanceState) {

	}

	protected void onPostCreate(Bundle savedInstanceState) {
	}

	protected void onResume() {
		if (contentPanel != null) {
			contentPanel.setVisible(true);
		}
	}

	protected void onPostResume() {
	}

	protected void onPause() {
		if (contentPanel != null) {
			contentPanel.setVisible(false);
		}
	}

	protected void onDestroy() {
		if (contentPanel != null) {
			RootPanel.get(ACTIVITY_ID).remove(contentPanel);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}

	public void showMenu(Menu menu) {
		if (menu.menuItems.size() > 0) {
			menuPanel = DOM.createDiv();
			menuPanel.addClassName(Res.R.style().dialog());
			menuPanel.addClassName(Res.R.style().invisible());

			for (final MenuItem item : menu.menuItems) {
				if (item.getIcon() != null) {

				}
				Element b = DOM.createButton();
				b.addClassName(Res.R.style().menuItem());
				b.setInnerHTML(getString(item.getTitle()));
				Event.setEventListener(b, new EventListener() {
					@Override
					public void onBrowserEvent(Event event) {
						closeOptionsMenu();
						onOptionsItemSelected(item);
					}
				});
				Event.sinkEvents(b, Event.ONCLICK);

				menuPanel.appendChild(b);
			}
			contentPanel.getElement().appendChild(menuPanel);
		}
	}

	/**
	 * Used only by the system menu button
	 */
	void toggleOptionsMenu(View view) {
		if (menuPanel != null) {
			if (menuPanel.hasClassName(Res.R.style().invisible())) {
				menuPanel.getStyle().setProperty("position", "fixed");
				menuPanel.getStyle().setPropertyPx("left", view.getLeft() + view.getWidth() - menuPanel.getOffsetWidth());
				menuPanel.getStyle().setPropertyPx("top", view.getTop() + view.getHeight());
				openOptionsMenu();
			} else {
				closeOptionsMenu();
			}
		}
	}

	public void openOptionsMenu() {
		if (menuPanel != null && menuPanel.hasClassName(Res.R.style().invisible())) {
			menuPanel.removeClassName(Res.R.style().invisible());
		}
	}

	public void closeOptionsMenu() {
		if (menuPanel != null && !menuPanel.hasClassName(Res.R.style().invisible())) {
			menuPanel.addClassName(Res.R.style().invisible());
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Context getApplicationContext() {
		// not very correct, but it's a context and at the moment we do not
		// differentiate among contexts
		return this;
	}

	public void finish() {
		ActivityManager.finish(this);
	}

	public void setContentView(TextResource content) {
		contentPanel = new HTMLPanel(content.getText());
		RootPanel.get(ACTIVITY_ID).add(contentPanel);
	}

	public void setContentView(int layoutId) {
		setContentView(Resources.getResourceResolver().getLayout(layoutId));
	}

	public void setContentView(Widget htmlPanel) {
		contentPanel = htmlPanel;
		RootPanel.get(ACTIVITY_ID).add(contentPanel);

		Element backElement = ViewFactory.getElementById(contentPanel.getElement(), "BackButton");
		if (backElement != null) {
			ImageButton backButton = (ImageButton) ViewFactory.createViewFromElement(backElement);
			backButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityManager.back();
				}
			});
		}
		Element menuElement = ViewFactory.getElementById(contentPanel.getElement(), "MenuButton");
		if (menuElement != null) {
			ImageButton menuButton = (ImageButton) ViewFactory.createViewFromElement(menuElement);
			menuButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityManager.toggleOptionsMenu(v);
				}
			});
		}
	}

	protected void onSaveInstanceState(Bundle outState) {
	}

	public void startActivityForResult(Intent intent, int requestCode) {
		ActivityManager.startActivity(intent, requestCode);
	}

	public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
		ActivityManager.startActivity(intent, requestCode);
	}

	public void setResult(int resultCode, Intent resultData) {
		this.resultCode = resultCode;
		this.resultData = resultData;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

	public void onBackPressed() {
		finish();
	}

	public View findViewById(String id) {
		return ViewFactory.findViewById(contentPanel.getElement(), id);
	}

	public Intent getIntent() {
		return intent;
	}

	public MenuInflater getMenuInflater() {
		return new MenuInflater();
	}
}
