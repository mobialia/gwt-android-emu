package androidemu.widget;

import androidemu.view.View;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;

public class Button extends View {

	public Button(Element element) {
		super(element);
	}

	public void setOnClickListener(final OnClickListener listener) {
		com.google.gwt.user.client.ui.Button.wrap(element).addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				listener.onClick(Button.this);
			}
		});
	}

}
