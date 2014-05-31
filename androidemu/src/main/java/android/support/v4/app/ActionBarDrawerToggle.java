package android.support.v4.app;


import android.Res;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {

	static final String TAG = "ActionBarDrawerToggle";

	DrawerLayout drawerLayout;
	int drawerImageRes;
	ImageView drawerImageView;

	public ActionBarDrawerToggle(Activity activity, final DrawerLayout drawerLayout, int drawerImageRes, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
		this.drawerLayout = drawerLayout;
		this.drawerImageRes = drawerImageRes;

		drawerImageView = new ImageView(activity);
		drawerImageView.setImageResource(drawerImageRes);
		drawerImageView.element.addClassName(Res.R.style().actionbarDrawer());

		LinearLayout actionBarLeft = new LinearLayout(ViewFactory.getElementById(activity.view.getElement(), "ActionBarLeft"));
		if (actionBarLeft.getElement() == null) {
			Log.e(TAG, "ActionBarLeft div not found");
			return;
		}
		actionBarLeft.element.getParentElement().appendChild(drawerImageView.getElement());
	}

	public void onConfigurationChanged(Configuration newConfig) {

	}

	public void onDrawerClosed(View drawerView) {
	}

	public void onDrawerOpened(View drawerView) {
	}

	public void onDrawerSlide(View drawerView, float slideOffset) {

	}

	public void onDrawerStateChanged(int newState) {

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (drawerLayout.isDrawerOpen(drawerLayout)) {
				drawerLayout.closeDrawer(0);
				drawerImageView.getElement().removeClassName("drawer-toggle-opened");
			} else {
				drawerLayout.openDrawer(0);
				drawerImageView.getElement().addClassName("drawer-toggle-opened");
			}
		}

		return false;
	}

	public void setDrawerIndicatorEnabled(boolean enable) {

	}

	public boolean isDrawerIndicatorEnabled() {
		return true;
	}

	public void syncState() {

	}
}
