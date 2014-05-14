package android.demo.res;
import android.content.res.ResourceResolver;
import android.content.res.Resources;
import android.view.Menu;
import com.google.gwt.core.client.GWT;

public class R implements ResourceResolver {

	static {
		Resources.setResourceResolver(new R());
	}

	public static final class id {
		public final static int menu1 = 1;
		public final static int menu2 = 2;
	}

	public static final class string {
		public final static int activity1 = 1;
		public final static int activity2 = 2;
		public final static int hello = 3;
		public final static int menu1 = 4;
		public final static int menu2 = 5;
		public final static int alert_dialog = 6;
		public final static int dialog_title = 7;
		public final static int dialog_message = 8;
		public final static int dialog_yes = 9;
		public final static int dialog_no = 10;
		public final static int toast = 11;
		public final static int toast_text = 12;
		public final static int handler = 13;
		public final static int handler_message_received = 14;
		public final static int activity = 15;
		public final static int other = 16;
	}

	public static final class array {
		public final static int array1 = 1;
	}

	public static final class menu {
		public final static int demo_menu = 3;
	}

	public static final Strings stringValues = GWT.create(Strings.class);
	public static final Arrays arrayValues = GWT.create(Arrays.class);
	public static final Layouts layout = new Layouts();
	public static final Raw raw = GWT.create(Raw.class);

	public String getIdAsString(int id) {
		switch(id) {
			case R.id.menu1:
					return "menu1";
			case R.id.menu2:
					return "menu2";
		}
		return null;
	}

	public String getString(int id) {
		switch(id) {
			case R.string.activity1:
				return stringValues.activity1();
			case R.string.activity2:
				return stringValues.activity2();
			case R.string.hello:
				return stringValues.hello();
			case R.string.menu1:
				return stringValues.menu1();
			case R.string.menu2:
				return stringValues.menu2();
			case R.string.alert_dialog:
				return stringValues.alert_dialog();
			case R.string.dialog_title:
				return stringValues.dialog_title();
			case R.string.dialog_message:
				return stringValues.dialog_message();
			case R.string.dialog_yes:
				return stringValues.dialog_yes();
			case R.string.dialog_no:
				return stringValues.dialog_no();
			case R.string.toast:
				return stringValues.toast();
			case R.string.toast_text:
				return stringValues.toast_text();
			case R.string.handler:
				return stringValues.handler();
			case R.string.handler_message_received:
				return stringValues.handler_message_received();
			case R.string.activity:
				return stringValues.activity();
			case R.string.other:
				return stringValues.other();
		}
		return null;
	}

	public String[] getStringArray(int id) {
		switch(id) {
			case R.array.array1:
				return arrayValues.array1();
		}
		return null;
	}

	public Menu getMenu(int id) {
		switch(id) {
			case R.menu.demo_menu:
					return Menus.demo_menu();
		}
		return null;
	}

}
