/** FILE GENERATED AUTOMATICALLY BY GWT_ANDROID_EMU'S GenerateResources: DO NOT EDIT MANUALLY */
package android.demo.res;

import android.content.res.BaseResourceResolver;
import android.view.Menu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class ResourceResolver extends BaseResourceResolver {

	public static final Strings strings = GWT.create(Strings.class);
	public static final Arrays arrays = GWT.create(Arrays.class);
	public static final Layouts layouts = new Layouts();

	public static String getIdAsString(int id) {
		switch(id) {
			case R.id.menu1:
					return "menu1";
			case R.id.menu2:
					return "menu2";
		}
		return null;
	}
	public static String getString(int id) {
		switch(id) {
			case R.string.activity1:
				return strings.activity1();
			case R.string.activity2:
				return strings.activity2();
			case R.string.hello:
				return strings.hello();
			case R.string.menu1:
				return strings.menu1();
			case R.string.menu2:
				return strings.menu2();
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
			case R.string.handler:
				return strings.handler();
			case R.string.handler_message_received:
				return strings.handler_message_received();
			case R.string.activity:
				return strings.activity();
			case R.string.other:
				return strings.other();
		}
		return null;
	}
	public static String[] getStringArray(int id) {
		switch(id) {
			case R.array.array1:
				return arrays.array1();
		}
		return null;
	}
	public static Menu getMenu(int id) {
		switch(id) {
			case R.menu.demo_menu:
					return Menus.demo_menu();
		}
		return null;
	}
	public static Widget getLayout(int id) {
		switch(id) {
			case R.layout.main:
				return layouts.main();
			case R.layout.other:
				return layouts.other();
		}
		return null;
	}
}
