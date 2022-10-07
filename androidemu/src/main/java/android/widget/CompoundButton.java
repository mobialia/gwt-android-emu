package android.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Event;

public class CompoundButton extends Button {

	public CompoundButton(Element element) {
		super(element);
	}

	public interface OnCheckedChangeListener {

		void onCheckedChanged(CompoundButton buttonView, boolean isChecked);

	}

	public boolean isChecked() {
		return InputElement.as(element).isChecked();
	}

	public void setChecked(boolean checked) {
		InputElement.as(element).setChecked(checked);
	}

	public void setOnCheckedChangeListener(final CompoundButton.OnCheckedChangeListener listener) {
		Event.setEventListener(element, event -> listener.onCheckedChanged(CompoundButton.this, isChecked()));
		Event.sinkEvents(element, Event.ONCHANGE);
	}

}
