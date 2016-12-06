package android.preference;

import android.MobialiaUtil;
import android.Res;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public class PreferencesBuilder {

	public static void addGroupLabel(Element panel, int labelText) {
		addGroupLabel(panel, Context.resources.getString(labelText));
	}

	public static void addGroupLabel(Element panel, String labelText) {
		Element labelE = DOM.createDiv();
		labelE.setInnerHTML(labelText);
		labelE.addClassName(Res.R.style().dialogTitle());
		panel.appendChild(labelE);
	}

	public static void addListPreference(Element panel, final SharedPreferences sharedPrefs, final String key, int label, int summary,
										 final int values, int labels, String defaultValue) {
		addListPreference(panel, sharedPrefs, key, Context.resources.getString(label), Context.resources.getString(summary),
				Context.resources.getStringArray(values), Context.resources.getStringArray(labels), defaultValue);
	}

	public static void addListPreference(Element panel, final SharedPreferences sharedPrefs, final String key, String label, String summary,
										 final String[] values, String[] labels, String defaultValue) {
		String value = sharedPrefs.getString(key, defaultValue);

		Element inputLabel = DOM.createLabel();
		inputLabel.addClassName(Res.R.style().preferencesElement());

		final Element listBoxE = DOM.createSelect();
		listBoxE.addClassName(Res.R.style().preferencesListBox());
		listBoxE.addClassName(Res.R.style().materialSelect());
		int selectedPosition = MobialiaUtil.arrayPosition(values, value);
		int i = 0;
		for (String text : labels) {
			Element optionE = DOM.createOption();
			optionE.setInnerHTML(text);
			if (i++ == selectedPosition) {
				optionE.setPropertyString("selected", "selected");
			}
			listBoxE.appendChild(optionE);
		}
		Event.setEventListener(listBoxE, new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putString(key, values[((SelectElement) listBoxE).getSelectedIndex()]);
				editor.commit();
			}
		});
		Event.sinkEvents(listBoxE, Event.ONCHANGE);
		inputLabel.appendChild(listBoxE);

		Element labelG = DOM.createDiv();
		labelG.addClassName(Res.R.style().preferencesLabel());
		labelG.setInnerHTML(label);
		inputLabel.appendChild(labelG);

		Element summaryG = DOM.createDiv();
		summaryG.addClassName(Res.R.style().preferencesSummary());
		summaryG.setInnerHTML(summary);
		inputLabel.appendChild(summaryG);

		panel.appendChild(inputLabel);
	}

	public static void addBooleanPreference(Element panel, final SharedPreferences sharedPrefs, final String key, int label, int summary,
											boolean defaultValue) {
		addBooleanPreference(panel, sharedPrefs, key, Context.resources.getString(label), Context.resources.getString(summary),
				defaultValue);
	}

	public static void addBooleanPreference(Element panel, final SharedPreferences sharedPrefs, final String key, String label, String summary,
											boolean defaultValue) {
		Boolean value = sharedPrefs.getBoolean(key, defaultValue);

		Element inputLabel = DOM.createLabel();
		inputLabel.addClassName(Res.R.style().materialLabel());
		inputLabel.addClassName(Res.R.style().preferencesElement());

		final Element checkBox = DOM.createInputCheck();
		if (value) {
			checkBox.setPropertyString("checked", "true");
		}
		inputLabel.appendChild(checkBox);
		final Element checkboxSymbol = DOM.createDiv();
		checkboxSymbol.addClassName(Res.R.style().materialCheckbox());
		checkboxSymbol.addClassName(Res.R.style().preferencesCheckBox());
		inputLabel.appendChild(checkboxSymbol);

		Element labelG = DOM.createDiv();
		labelG.addClassName(Res.R.style().preferencesLabel());
		labelG.setInnerHTML(label);
		inputLabel.appendChild(labelG);

		Element summaryG = DOM.createDiv();
		summaryG.addClassName(Res.R.style().preferencesSummary());
		summaryG.setInnerHTML(summary);
		inputLabel.appendChild(summaryG);

		Event.setEventListener(inputLabel, new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putBoolean(key, ((InputElement) checkBox).isChecked());
				editor.commit();
			}
		});
		Event.sinkEvents(inputLabel, Event.ONCLICK | Event.ONTOUCHEND);

		panel.appendChild(inputLabel);
	}
}
