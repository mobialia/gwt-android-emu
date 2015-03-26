package android.support.v7.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {

	static final String TAG = "ActionBarDrawerToggle";

	DrawerLayout drawerLayout;
	ActionBarActivity activity;
	int drawerImageRes = android.R.drawable.ic_drawer;

	boolean drawerIndicatorEnabled = true;

	public ActionBarDrawerToggle(Activity activity, final DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
		this.drawerLayout = drawerLayout;
		this.activity = (ActionBarActivity) activity;
		this.activity.getSupportActionBar().setHomeAsUpIndicator(drawerImageRes);
	}

	public void onConfigurationChanged(Configuration newConfig) {

	}

	@Override
	public void onDrawerClosed(View drawerView) {
		activity.getSupportActionBar().indicatorImageView.getElement().removeClassName("actionbar-indicator-opened");
	}

	@Override
	public void onDrawerOpened(View drawerView) {
		activity.getSupportActionBar().indicatorImageView.getElement().addClassName("actionbar-indicator-opened");
	}

	public void onDrawerSlide(View drawerView, float slideOffset) {
	}

	public void onDrawerStateChanged(int newState) {
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (drawerLayout.isDrawerOpen(drawerLayout)) {
				drawerLayout.closeDrawer(0);
			} else {
				drawerLayout.openDrawer(0);
			}
			return true;
		}
		return false;
	}

	public void setDrawerIndicatorEnabled(boolean enable) {
		this.drawerIndicatorEnabled = enable;
		if (enable) {
			activity.getSupportActionBar().setHomeAsUpIndicator(drawerImageRes);
		} else {
			activity.getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.actionbar_indicator_back);
		}
	}

	public boolean isDrawerIndicatorEnabled() {
		return drawerIndicatorEnabled;
	}

	public void syncState() {
		if (drawerLayout.isDrawerOpen(drawerLayout)) {
			activity.getSupportActionBar().indicatorImageView.getElement().addClassName("actionbar-indicator-opened");
		} else {
			activity.getSupportActionBar().indicatorImageView.getElement().removeClassName("actionbar-indicator-opened");
		}
	}
}
