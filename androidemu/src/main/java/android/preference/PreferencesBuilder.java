package android.preference;

import android.content.res.Resources;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;

import android.MobialiaUtil;
import android.Res;

public class PreferencesBuilder {

	public static void addGroupLabel(Panel panel, int labelText) {
		addGroupLabel(panel, Resources.getResourceResolver().getString(labelText));
	}

	public static void addGroupLabel(Panel panel, String labelText) {
		Label label = new Label(labelText);
		label.setStyleName(Res.R.style().dialogTitle());
		panel.add(label);
	}

	public static void addListPreference(Panel panel, final SharedPreferences sharedPrefs, final String key, int label, int summary,
										 final int values, int labels, String defaultValue) {
		addListPreference(panel, sharedPrefs, key, Resources.getResourceResolver().getString(label), Resources.getResourceResolver().getString(summary),
				Resources.getResourceResolver().getStringArray(values), Resources.getResourceResolver().getStringArray(labels), defaultValue);
	}

	public static void addListPreference(Panel panel, final SharedPreferences sharedPrefs, final String key, String label, String summary,
										 final String[] values, String[] labels, String defaultValue) {
		String value = sharedPrefs.getString(key, defaultValue);

		FlowPanel fp = new FlowPanel();
		fp.setStyleName(Res.R.style().preferencesElement());

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
		listBox.setStyleName(Res.R.style().preferencesListBox());
		fp.add(listBox);

		Label labelG = new Label(label);
		labelG.setStyleName(Res.R.style().preferencesLabel());
		fp.add(labelG);
		Label summaryG = new Label(summary);
		summaryG.setStyleName(Res.R.style().preferencesSummary());
		fp.add(summaryG);

		panel.add(fp);
	}

	public static void addBooleanPreference(Panel panel, final SharedPreferences sharedPrefs, final String key, int label, int summary,
											boolean defaultValue) {
		addBooleanPreference(panel, sharedPrefs, key, Resources.getResourceResolver().getString(label), Resources.getResourceResolver().getString(summary),
				defaultValue);
	}

	public static void addBooleanPreference(Panel panel, final SharedPreferences sharedPrefs, final String key, String label, String summary,
											boolean defaultValue) {
		Boolean value = sharedPrefs.getBoolean(key, defaultValue);

		FlowPanel fp = new FlowPanel();
		fp.setStyleName(Res.R.style().preferencesElement());

		CheckBox checkBox = new CheckBox();
		checkBox.setValue(value);
		checkBox.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putBoolean(key, ((CheckBox) event.getSource()).getValue());
				editor.commit();
			}
		});
		checkBox.setStyleName(Res.R.style().preferencesCheckBox());
		fp.add(checkBox);

		Label labelG = new Label(label);
		labelG.setStyleName(Res.R.style().preferencesLabel());
		fp.add(labelG);
		Label summaryG = new Label(summary);
		summaryG.setStyleName(Res.R.style().preferencesSummary());
		fp.add(summaryG);

		panel.add(fp);
	}
}
