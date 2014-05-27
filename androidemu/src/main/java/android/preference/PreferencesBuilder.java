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

		Element fpE = DOM.createDiv();
		fpE.addClassName(Res.R.style().preferencesElement());

		final Element listBoxE = DOM.createSelect();
		listBoxE.addClassName(Res.R.style().preferencesListBox());
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
		fpE.appendChild(listBoxE);

		Element labelG = DOM.createDiv();
		labelG.addClassName(Res.R.style().preferencesLabel());
		labelG.setInnerHTML(label);
		fpE.appendChild(labelG);

		Element summaryG = DOM.createDiv();
		summaryG.addClassName(Res.R.style().preferencesSummary());
		summaryG.setInnerHTML(summary);
		fpE.appendChild(summaryG);

		panel.appendChild(fpE);
	}

	public static void addBooleanPreference(Element panel, final SharedPreferences sharedPrefs, final String key, int label, int summary,
											boolean defaultValue) {
		addBooleanPreference(panel, sharedPrefs, key, Context.resources.getString(label), Context.resources.getString(summary),
				defaultValue);
	}

	public static void addBooleanPreference(Element panel, final SharedPreferences sharedPrefs, final String key, String label, String summary,
											boolean defaultValue) {
		Boolean value = sharedPrefs.getBoolean(key, defaultValue);

		Element fpE = DOM.createDiv();
		fpE.addClassName(Res.R.style().preferencesElement());

		final Element checkBox = DOM.createInputCheck();
		checkBox.addClassName(Res.R.style().preferencesCheckBox());
		if (value) {
			checkBox.setPropertyString("checked", "true");
		}
		Event.setEventListener(checkBox, new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putBoolean(key, ((InputElement) checkBox).isChecked());
				editor.commit();
			}
		});
		Event.sinkEvents(checkBox, Event.ONCLICK | Event.ONTOUCHEND);
		fpE.appendChild(checkBox);

		Element labelG = DOM.createDiv();
		labelG.addClassName(Res.R.style().preferencesLabel());
		labelG.setInnerHTML(label);
		fpE.appendChild(labelG);

		Element summaryG = DOM.createDiv();
		summaryG.addClassName(Res.R.style().preferencesSummary());
		summaryG.setInnerHTML(summary);
		fpE.appendChild(summaryG);

		panel.appendChild(fpE);
	}
}
