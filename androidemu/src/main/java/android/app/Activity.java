package android.app;

import android.Res;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewFactory;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.DOM;
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
	private Element menuElement;

	Intent intent;
	Integer requestCode;
	int resultCode = RESULT_OK;
	Intent resultData;

	// Data when we return from another activity
	Integer returnRequestCode;
	int returnResultCode;
	Intent returnResultData;

	Menu menu;

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
		if (menuElement != null) {
			menuElement.removeFromParent();
		}
		if (contentPanel != null) {
			RootPanel.get(ACTIVITY_ID).remove(contentPanel);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	public void invalidateOptionsMenu() {
		if (menuElement != null) {
			menuElement.removeFromParent();
			menuElement = null;
		}
		menu = new Menu();
		onCreateOptionsMenu(menu);
		onPrepareOptionsMenu(menu);
		createMenu();
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return onOptionsItemSelected(item);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	private void createMenu() {
		Element actionsElement = ViewFactory.getElementById(contentPanel.getElement(), "MenuActions");
		if (actionsElement == null) {
			Log.e(TAG, "MenuActions div not found");
			return;
		}
		actionsElement.removeAllChildren();

		if (menu.menuItems.size() > 0) {
			menuElement = DOM.createDiv();
			menuElement.addClassName(Res.R.style().dialog());
			menuElement.addClassName(Res.R.style().gone());

			for (final MenuItem item : menu.menuItems) {
				if (item.getTitle() != 0 || item.getIcon() != null) {
					if (item.getShowAsAction() == MenuItem.SHOW_AS_ACTION_ALWAYS) {
						ImageButton b = new ImageButton();
						if (item.getIcon() != null) {
							b.setImageResource("img/" + item.getIcon() + ".png");
							b.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									onMenuItemSelected(0, item);
								}
							});
							b.getElement().addClassName(Res.R.style().actionbarButton());
							actionsElement.appendChild(b.getElement());
						}
					} else {
						Button b = new Button();
						if (item.getTitle() != 0) {
							b.setText(getString(item.getTitle()));
						}
						b.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								closeOptionsMenu();
								onMenuItemSelected(0, item);
							}
						});
						b.getElement().addClassName(Res.R.style().menuItem());
						menuElement.appendChild(b.getElement());
					}
				}
			}
			// Add menu and button only if it has elements
			if (menuElement.hasChildNodes()) {
				final ImageButton menuButton = new ImageButton();
				menuButton.getElement().setClassName(Res.R.style().actionbarButton());
				menuButton.setImageResource("img/actionbar_menu.png");
				menuButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						toggleOptionsMenu(menuButton);
					}
				});
				actionsElement.appendChild(menuButton.getElement());
				actionsElement.appendChild(menuElement);
			}
		}
	}

	/**
	 * Used only by the system menu button
	 */
	void toggleOptionsMenu(View view) {
		if (menuElement.hasClassName(Res.R.style().gone())) {
			openOptionsMenu();
			menuElement.getStyle().setProperty("position", "fixed");
			menuElement.getStyle().setPropertyPx("left", view.getLeft() + view.getWidth() - menuElement.getOffsetWidth());
			menuElement.getStyle().setPropertyPx("top", view.getTop() + view.getHeight());
		} else {
			closeOptionsMenu();
		}
	}

	public void openOptionsMenu() {
		if (menuElement != null && menuElement.hasClassName(Res.R.style().gone())) {
			menuElement.removeClassName(Res.R.style().gone());
		}
	}

	public void closeOptionsMenu() {
		if (menuElement != null && !menuElement.hasClassName(Res.R.style().gone())) {
			menuElement.addClassName(Res.R.style().gone());
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
