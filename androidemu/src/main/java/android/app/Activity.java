package android.app;

import android.Res;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewFactory;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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
	public ViewGroup view;
	private LinearLayout menuLayout;

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
		if (view != null) {
			view.setVisibility(View.VISIBLE);
		}
	}

	protected void onPostResume() {
	}

	protected void onPause() {
		if (view != null) {
			view.setVisibility(View.GONE);
		}
	}

	protected void onDestroy() {
		if (menuLayout != null) {
			menuLayout.getElement().removeFromParent();
		}
		if (view != null) {
			view.getElement().removeFromParent();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	public void invalidateOptionsMenu() {
		if (menuLayout != null) {
			menuLayout.getElement().removeFromParent();
			menuLayout = null;
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
		LinearLayout actionBarRight = new LinearLayout(ViewFactory.getElementById(view.getElement(), "MenuItems"));
		if (actionBarRight.getElement() == null) {
			Log.e(TAG, "MenuItems div not found");
			return;
		}
		actionBarRight.removeAllViews();

		if (menu.menuItems.size() > 0) {
			menuLayout = new LinearLayout(this);
			menuLayout.getElement().addClassName(Res.R.style().dialog());
			menuLayout.getElement().addClassName(Res.R.style().gone());

			boolean hasMenuItems = false;

			for (final MenuItem item : menu.menuItems) {
				if (item.getTitle() != 0 || item.getIcon() != 0) {
					if (item.getShowAsAction() == MenuItem.SHOW_AS_ACTION_ALWAYS) {
						ImageButton b = new ImageButton(this);
						if (item.getIcon() != 0) {
							b.setImageResource(item.getIcon());
							b.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									onMenuItemSelected(0, item);
								}
							});
							b.getElement().addClassName(Res.R.style().actionbarButton());
							actionBarRight.addView(b);
						}
					} else {
						Button b = new Button(this);
						if (item.getItemId() != 0) {
							b.getElement().setId(getResources().getIdAsString(item.getItemId()));
						}
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
						menuLayout.addView(b);
						hasMenuItems = true;
					}
				}
			}
			// Add menu and button only if it has elements
			if (hasMenuItems) {
				final ImageButton menuButton = new ImageButton(this);
				menuButton.getElement().setClassName(Res.R.style().actionbarButton());
				menuButton.setImageResource(android.R.drawable.actionbar_menu);
				menuButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						toggleOptionsMenu(menuButton);
					}
				});
				actionBarRight.addView(menuButton);
				view.getElement().appendChild(menuLayout.getElement());
			}
		}
	}

	/**
	 * Used only by the system menu button
	 */
	void toggleOptionsMenu(View view) {
		if (menuLayout.getElement().hasClassName(Res.R.style().gone())) {
			openOptionsMenu();
			menuLayout.getElement().getStyle().setProperty("position", "fixed");
			menuLayout.getElement().getStyle().setPropertyPx("left", view.getLeft() + view.getWidth() - menuLayout.getWidth());
			menuLayout.getElement().getStyle().setPropertyPx("top", view.getTop() + view.getHeight());
		} else {
			closeOptionsMenu();
		}
	}

	public void openOptionsMenu() {
		if (menuLayout != null && menuLayout.getElement().hasClassName(Res.R.style().gone())) {
			menuLayout.getElement().removeClassName(Res.R.style().gone());
		}
	}

	public void closeOptionsMenu() {
		if (menuLayout != null && !menuLayout.getElement().hasClassName(Res.R.style().gone())) {
			menuLayout.getElement().addClassName(Res.R.style().gone());
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

	public void setContentView(int layoutId) {
		setContentView(getResources().getLayout(layoutId));
	}

	public void setContentView(Widget htmlPanel) {
		String id = HTMLPanel.createUniqueId();
		view = new LinearLayout(this);
		DOM.getElementById(ACTIVITY_ID).appendChild(view.getElement());
		view.getElement().setId(id);
		RootPanel.get(id).add(htmlPanel);
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

	public View findViewById(int id) {
		return view.findViewById(id);
	}

	public Intent getIntent() {
		return intent;
	}

	public MenuInflater getMenuInflater() {
		return new MenuInflater();
	}
}
