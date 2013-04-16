package androidemu.app;

import androidemu.Res;
import androidemu.content.Context;
import androidemu.content.Intent;
import androidemu.os.Bundle;
import androidemu.util.Log;
import androidemu.view.Menu;
import androidemu.view.MenuItem;
import androidemu.view.View;
import androidemu.view.ViewFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Activity extends Context implements EntryPoint {

	public static final String TAG = "Activity";

	public static final int RESULT_CANCELED = 0;
	public static final int RESULT_FIRST_USER = 1;
	public static final int RESULT_OK = -1;

	int status = 0;
	int targetStatus = 0;

	String title;
	Menu menu;
	HTMLPanel contentPanel;
	private PopupPanel menuPanel;

	Integer requestCode;
	int resultCode = RESULT_OK;
	Intent resultData;

	// Data when we return from another activity
	Integer returnRequestCode;
	int returnResultCode;
	Intent returnResultData;

	public void onModuleLoad() {
		if (RootPanel.get("BackButton") != null) {
			Button.wrap(RootPanel.get("BackButton").getElement()).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ActivityManager.back();
				}
			});
		}
		if (RootPanel.get("MenuButton") != null) {
			Button.wrap(RootPanel.get("MenuButton").getElement()).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ActivityManager.openOptionsMenu();
				}
			});
		}
		Res.R.style().ensureInjected();
		startActivity(new Intent(this, this));
	}

	protected void onCreate(Bundle savedInstanceState) {

	}

	protected void onResume() {
		if (contentPanel != null) {
			contentPanel.setVisible(true);
		}
	}

	protected void onPause() {
		if (contentPanel != null) {
			contentPanel.setVisible(false);
		}
	}

	protected void onDestroy() {
		if (contentPanel != null) {
			RootPanel.get("activity_div").remove(contentPanel);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}

	void createMenu() {
		if (menu == null) {
			menu = new Menu();
			onCreateOptionsMenu(menu);

			if (menu.menuItems.size() > 0) {
				menuPanel = new PopupPanel();
				menuPanel.setStyleName(Res.R.style().dialog());
				FlowPanel fp = new FlowPanel();

				for (final MenuItem item : menu.menuItems) {
					Button b = new Button();
					b.setText(item.getTitle());
					b.setStyleName(Res.R.style().menuItem());
					b.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							hideMenu();
							onOptionsItemSelected(item);
						}
					});
					fp.add(b);
				}
				menuPanel.add(fp);
				menuPanel.setAutoHideEnabled(true);
			}
		}
	}

	void hideMenu() {
		if (menuPanel != null && menuPanel.isShowing()) {
			menuPanel.hide();
		}
	}

	public void openOptionsMenu() {
		if (menuPanel != null) {
			menuPanel.center();
			menuPanel.show();
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Context getApplicationContext() {
		return null;
	}

	public void finish() {
		ActivityManager.finish(this);
	}

	public void setContentView(TextResource content) {
		contentPanel = new HTMLPanel(content.getText());
		RootPanel.get("activity_div").add(contentPanel);
	}

	public void setContentView(Widget widget) {
		contentPanel = new HTMLPanel("");
		contentPanel.getElement().appendChild(widget.getElement());
		RootPanel.get("activity_div").add(contentPanel);
	}

	public void setContentView(HTMLPanel htmlPanel) {
		contentPanel = htmlPanel;
		RootPanel.get("activity_div").add(contentPanel);
	}

	protected void onSaveInstanceState(Bundle outState) {
	}

	public void startActivityForResult(Intent intent, int requestCode) {
		ActivityManager.startActivity(intent, requestCode);
	}

	public void setResult(int resultCode, Intent resultData) {
		this.resultCode = resultCode;
		this.resultData = resultData;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

	// TODO Call this method on Browser Back Press
	public void onBackPressed() {

	}

	public View findViewById(String id) {
		return ViewFactory.findViewById(contentPanel.getElement(), id);
	}

	public Intent getIntent() {
		return null;
	}
}
