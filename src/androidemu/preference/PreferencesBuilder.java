package androidemu.preference;

import androidemu.MobialiaUtil;
import androidemu.Res;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;

public class PreferencesBuilder {

	public static void addGroupLabel(Panel panel, String labelText) {
		Label label = new Label(labelText);
		label.setStyleName(Res.R.style().dialogTitle());
		panel.add(label);
	}

	public static void addListPreference(Panel panel, final SharedPreferences sharedPrefs, final String key, String label, final String[] values,
			String[] labels, String defaultValue) {
		String value = sharedPrefs.getString(key, defaultValue);

		panel.add(new Label(label));
		ListBox listBox = new ListBox();
		for (String text : labels) {
			listBox.addItem(text);
		}
		int selectedPosition = MobialiaUtil.arrayPosition(values, value);
		listBox.setItemSelected(selectedPosition, true);
		listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putString(key, values[((ListBox) event.getSource()).getSelectedIndex()]);
				editor.commit();
			}
		});
		panel.add(listBox);
	}

	public static void addBooleanPreference(Panel panel, final SharedPreferences sharedPrefs, final String key, String label, boolean defaultValue) {
		Boolean value = sharedPrefs.getBoolean(key, defaultValue);

		CheckBox checkBox = new CheckBox(label);
		checkBox.setValue(value);
		checkBox.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putBoolean(key, ((CheckBox) event.getSource()).getValue());
				editor.commit();
			}
		});
		panel.add(checkBox);
	}
}
