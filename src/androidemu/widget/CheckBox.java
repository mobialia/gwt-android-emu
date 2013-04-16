package androidemu.widget;

import androidemu.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;

public class CheckBox extends View {

	public CheckBox(Element element) {
		super(element);
	}

	public boolean isChecked() {
		return InputElement.as(element).isChecked();
	}

	public void setChecked(boolean checked) {
		InputElement.as(element).setChecked(checked);
	}
}