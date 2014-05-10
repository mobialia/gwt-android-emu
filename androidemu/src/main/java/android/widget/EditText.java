package android.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;

import android.view.View;

public class EditText extends View {

	public EditText(Element element) {
		super(element);
	}

	public void setText(String text) {
		InputElement.as(element).setValue(text);
	}

	public String getText() {
		return InputElement.as(element).getValue();
	}

}
