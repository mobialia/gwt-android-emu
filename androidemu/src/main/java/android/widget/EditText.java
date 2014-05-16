package android.widget;

import android.content.res.Resources;
import android.view.View;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;

public class EditText extends View {

	public EditText(Element element) {
		super(element);
	}

	public void setText(int stringId) {
		setText(Resources.getResourceResolver().getString(stringId));
	}

	public void setText(String text) {
		InputElement.as(element).setValue(text);
	}

	public String getText() {
		return InputElement.as(element).getValue();
	}

}
