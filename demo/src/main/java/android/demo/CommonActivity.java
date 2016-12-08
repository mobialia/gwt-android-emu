package android.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Activity with a drawer
 */
public class CommonActivity extends AppCompatActivity {

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private boolean showBack = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_drawer);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		drawerLayout = (DrawerLayout) findViewById(R.id.root);
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {

			public void onDrawerOpened(View view) {
				super.onDrawerOpened(view);
			}

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			}
		};
		// Set the drawer toggle as the DrawerListener
		drawerLayout.setDrawerListener(drawerToggle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		drawerToggle.syncState();
	}

	public void onFragmentResumed(String title, boolean showBack) {
		if (title != null) {
			getSupportActionBar().setTitle(title);
		}

		this.showBack = showBack;
		if (showBack) {
			closeDrawer();
			drawerToggle.setDrawerIndicatorEnabled(false);
		} else {
			drawerToggle.setDrawerIndicatorEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (showBack) {
			if (item.getItemId() == android.R.id.home) {
				onBackPressed();
				return true;
			}
		} else if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void closeDrawer() {
		drawerLayout.closeDrawer(GravityCompat.START);
	}

	public String getActiveFragmentTag() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
			return null;
		}
		return getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
	}

	public void openFragment(Fragment fragment, String tag) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.Fragment, fragment, tag);
		fragmentTransaction.addToBackStack(tag);
		fragmentTransaction.commit();
	}

	@Override
	public void onBackPressed() {
		if (drawerLayout.isDrawerOpen(null)) {
			drawerLayout.closeDrawer(GravityCompat.START);
			return;
		}
		super.onBackPressed();
	}
}
