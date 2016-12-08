package android.demo.res;

import android.demo.res.layout.ActivityDrawer;
import android.demo.res.layout.InputsFragment;
import android.demo.res.layout.IntroFragment;
import android.demo.res.layout.NotificationsFragment;
import android.demo.res.layout.OtherFragment;
import android.demo.res.layout.PreferencesFragment;
import android.demo.res.layout.SimpleActivity;
import android.demo.res.layout.Sidebar;
import android.demo.res.layout.SidebarAdapter;
import android.demo.res.layout.SidebarAdapterSeparator;

import com.google.gwt.user.client.ui.Widget;

/**
 * Simulate the Android layouts with the UiBinder, each screens needs a class and a XML
 * For each method in this class GenerateResources will create a R.layout.XXX entry
 */
public class Layouts {

	public Widget activity_drawer() {
		return new ActivityDrawer();
	}

	public Widget simple_activity() {
		return new SimpleActivity();
	}

	public Widget sidebar() {
		return new Sidebar();
	}

	public Widget sidebar_adapter() {
		return new SidebarAdapter();
	}

	public Widget sidebar_adapter_separator() {
		return new SidebarAdapterSeparator();
	}

	public Widget intro_fragment() {
		return new IntroFragment();
	}

	public Widget notifications_fragment() {
		return new NotificationsFragment();
	}

	public Widget inputs_fragment() {
		return new InputsFragment();
	}

	public Widget other_fragment() {
		return new OtherFragment();
	}

	public Widget preferences_fragment() {
		return new PreferencesFragment();
	}

}
