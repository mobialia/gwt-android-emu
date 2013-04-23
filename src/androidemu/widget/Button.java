package androidemu.widget;

import androidemu.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public class Button extends View {

	public Button(Element element) {
		super(element);
	}

	public void setOnClickListener(final OnClickListener listener) {
		Event.setEventListener(element, new EventListener() {
			@Override
			public void onBrowserEvent(Event event) {
				listener.onClick(Button.this);
			}
		});
		Event.sinkEvents(element, Event.ONCLICK);
	}

}
