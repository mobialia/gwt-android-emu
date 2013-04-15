package androidemu.widget;

import androidemu.view.View;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.TextBox;

public class EditText extends View {

	public EditText(Element element) {
		super(element);
	}

	public void setText(String text) {
		TextBox.wrap(element).setText(text);
	}

	public Object getText() {
		return TextBox.wrap(element).getText();
	}
}
