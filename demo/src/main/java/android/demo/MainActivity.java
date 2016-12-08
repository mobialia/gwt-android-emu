package android.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends CommonActivity {
	public final static String TAG = CommonActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			FragmentTransaction sidebarFragmentTransaction = getSupportFragmentManager().beginTransaction();
			Fragment sidebarFragment = new SidebarFragment();
			sidebarFragmentTransaction.replace(R.id.Drawer, sidebarFragment, "sidebar");
			sidebarFragmentTransaction.commit();

			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			Fragment fragment = new IntroFragment();
			fragmentTransaction.replace(R.id.Fragment, fragment, "intro");
			fragmentTransaction.commit();
		}
	}

	public void onIntroAction() {
		if (!"intro".equals(getActiveFragmentTag())) {
			openFragment(new IntroFragment(), "intro");
		}
	}

	public void onNotificationsAction() {
		if (!"notifications".equals(getActiveFragmentTag())) {
			openFragment(new NotificationsFragment(), "notifications");
		}
	}

	public void onMenusAction() {
		if (!"menus".equals(getActiveFragmentTag())) {
			openFragment(new MenusFragment(), "menus");
		}
	}

	public void onInputsAction() {
		if (!"inputs".equals(getActiveFragmentTag())) {
			openFragment(new InputsFragment(), "inputs");
		}
	}

	public void onOtherAction() {
		if (!"other".equals(getActiveFragmentTag())) {
			openFragment(new OtherFragment(), "other");
		}
	}

	public void onPreferencesAction() {
		if (!"preferences".equals(getActiveFragmentTag())) {
			openFragment(new PreferencesFragment(), "preferences");
		}
	}
}