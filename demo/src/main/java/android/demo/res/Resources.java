/**
 * FILE GENERATED AUTOMATICALLY BY GWT_ANDROID_EMU'S GenerateResources: DO NOT EDIT MANUALLY
 */
package android.demo.res;

import android.view.Menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import android.demo.R;

public class Resources extends android.content.res.Resources {

	public static final Strings strings = GWT.create(Strings.class);
	public static final Arrays arrays = GWT.create(Arrays.class);
	public static final Layouts layouts = new Layouts();

	public String getIdAsString(int id) {
		switch (id) {
			case R.id.HandlerButton:
				return "HandlerButton";
			case R.id.SidebarIcon:
				return "SidebarIcon";
			case R.id.SidebarText:
				return "SidebarText";
			case R.id.root:
				return "root";
			case R.id.Fragment:
				return "Fragment";
			case R.id.DrawerOverLay:
				return "DrawerOverLay";
			case R.id.Drawer:
				return "Drawer";
			case R.id.EditText1:
				return "EditText1";
			case R.id.Spinner:
				return "Spinner";
			case R.id.SeekSortAscending:
				return "SeekSortAscending";
			case R.id.RadioButton1:
				return "RadioButton1";
			case R.id.RadioButton2:
				return "RadioButton2";
			case R.id.AlertButton:
				return "AlertButton";
			case R.id.ToastButton:
				return "ToastButton";
			case R.id.ActivityButton:
				return "ActivityButton";
			case R.id.SnackBarButton:
				return "SnackBarButton";
			case R.id.list:
				return "list";
			case R.id.action1:
				return "action1";
			case R.id.menu1:
				return "menu1";
			case R.id.menu2:
				return "menu2";
			case R.id.menu_launch_fragment:
				return "menu_launch_fragment";
		}
		return super.getIdAsString(id);
	}
	public String getString(int id) {
		switch (id) {
			case R.string.intro_title:
				return strings.intro_title();
			case R.string.intro_header:
				return strings.intro_header();
			case R.string.intro_text:
				return strings.intro_text();
			case R.string.intro_text_2:
				return strings.intro_text_2();
			case R.string.drawer_open:
				return strings.drawer_open();
			case R.string.drawer_close:
				return strings.drawer_close();
			case R.string.sidebar_intro:
				return strings.sidebar_intro();
			case R.string.sidebar_notifications:
				return strings.sidebar_notifications();
			case R.string.sidebar_inputs:
				return strings.sidebar_inputs();
			case R.string.sidebar_other:
				return strings.sidebar_other();
			case R.string.sidebar_preferences:
				return strings.sidebar_preferences();
			case R.string.notifications:
				return strings.notifications();
			case R.string.alert_dialog:
				return strings.alert_dialog();
			case R.string.dialog_title:
				return strings.dialog_title();
			case R.string.dialog_message:
				return strings.dialog_message();
			case R.string.dialog_yes:
				return strings.dialog_yes();
			case R.string.dialog_no:
				return strings.dialog_no();
			case R.string.toast:
				return strings.toast();
			case R.string.toast_text:
				return strings.toast_text();
			case R.string.snackbar:
				return strings.snackbar();
			case R.string.snackbar_text:
				return strings.snackbar_text();
			case R.string.snackbar_action:
				return strings.snackbar_action();
			case R.string.inputs:
				return strings.inputs();
			case R.string.inputs_edittext:
				return strings.inputs_edittext();
			case R.string.inputs_checkbox:
				return strings.inputs_checkbox();
			case R.string.inputs_radiobutton1:
				return strings.inputs_radiobutton1();
			case R.string.inputs_radiobutton2:
				return strings.inputs_radiobutton2();
			case R.string.other:
				return strings.other();
			case R.string.handler:
				return strings.handler();
			case R.string.handler_message_received:
				return strings.handler_message_received();
			case R.string.preferences_group:
				return strings.preferences_group();
			case R.string.preference_boolean:
				return strings.preference_boolean();
			case R.string.preference_boolean_summary:
				return strings.preference_boolean_summary();
			case R.string.preference_list:
				return strings.preference_list();
			case R.string.preference_list_summary:
				return strings.preference_list_summary();
			case R.string.action1:
				return strings.action1();
			case R.string.menu1:
				return strings.menu1();
			case R.string.menu2:
				return strings.menu2();
			case R.string.activity:
				return strings.activity();
			case R.string.simple_activity:
				return strings.simple_activity();
			case R.string.menu_launch_fragment:
				return strings.menu_launch_fragment();
		}
		return super.getString(id);
	}
	public String[] getStringArray(int id) {
		switch (id) {
			case R.array.array1:
				return arrays.array1();
		}
		return super.getStringArray(id);
	}
	public Menu getMenu(int id) {
		switch (id) {
			case R.menu.demo_menu:
				return Menus.demo_menu();
			case R.menu.fragment_menu:
				return Menus.fragment_menu();
		}
		return super.getMenu(id);
	}
	public int getColor(int id) {
		switch (id) {
		}
		return super.getColor(id);
	}
	public String getDrawable(int id) {
		switch (id) {
			case R.drawable.ic_add:
				return "ic_add.svg";
			case R.drawable.ic_notifications:
				return "ic_notifications.svg";
			case R.drawable.ic_indicator_back:
				return "ic_indicator_back.svg";
			case R.drawable.ic_drawer:
				return "ic_drawer.svg";
			case R.drawable.icon:
				return "icon.png";
			case R.drawable.ic_other:
				return "ic_other.svg";
			case R.drawable.ic_preferences:
				return "ic_preferences.svg";
			case R.drawable.ic_inputs:
				return "ic_inputs.svg";
			case R.drawable.ic_intro:
				return "ic_intro.svg";
			case R.drawable.ic_spinner_vector:
				return "ic_spinner_vector.svg";
			case R.drawable.ic_menu:
				return "ic_menu.svg";
		}
		return super.getDrawable(id);
	}
	public Widget getLayout(int id) {
		switch (id) {
			case R.layout.activity_drawer:
				return layouts.activity_drawer();
			case R.layout.simple_activity:
				return layouts.simple_activity();
			case R.layout.sidebar:
				return layouts.sidebar();
			case R.layout.sidebar_adapter:
				return layouts.sidebar_adapter();
			case R.layout.sidebar_adapter_separator:
				return layouts.sidebar_adapter_separator();
			case R.layout.intro_fragment:
				return layouts.intro_fragment();
			case R.layout.notifications_fragment:
				return layouts.notifications_fragment();
			case R.layout.inputs_fragment:
				return layouts.inputs_fragment();
			case R.layout.other_fragment:
				return layouts.other_fragment();
			case R.layout.preferences_fragment:
				return layouts.preferences_fragment();
		}
		return super.getLayout(id);
	}
}
