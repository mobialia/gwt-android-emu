package androidemu.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public class CompoundButton extends Button {

	public CompoundButton(Element element) {
		super(element);
	}

	public static interface OnCheckedChangeListener {

		abstract void onCheckedChanged(CompoundButton buttonView, boolean isChecked);

	}

	public boolean isChecked() {
		return InputElement.as(element).isChecked();
	}

	public void setChecked(boolean checked) {
		InputElement.as(element).setChecked(checked);
	}

	public void setOnCheckedChangeListener(final CompoundButton.OnCheckedChangeListener listener) {
		Event.setEventListener(element, new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				listener.onCheckedChanged(CompoundButton.this, isChecked());
			}
		});
		Event.sinkEvents(element, Event.ONCHANGE);
	}

}
