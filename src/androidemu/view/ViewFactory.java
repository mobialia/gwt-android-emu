package androidemu.view;

import androidemu.widget.Button;
import androidemu.widget.CheckBox;
import androidemu.widget.EditText;
import androidemu.widget.ImageButton;
import androidemu.widget.ListView;
import androidemu.widget.RadioButton;
import androidemu.widget.ScrollView;
import androidemu.widget.Spinner;
import androidemu.widget.TextView;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

public class ViewFactory {

	public static View createViewFromElement(Element element) {
		if (element == null) {
			Window.alert("View not found");
			return null;
		}

		if (element.getNodeName() == "DIV") {
			String className = element.getAttribute("class").toUpperCase();
			if (className != null) {
				if (className.indexOf("LISTVIEW") >= 0) {
					return new ListView(element);
				} else if (className.indexOf("SCROLLVIEW") >= 0) {
					return new ScrollView(element);
				}
				return new TextView(element);
			}
		} else if (element.getNodeName() == "INPUT") {
			String type = element.getAttribute("type").toUpperCase();
			if (type.equals("TEXT")) {
				return new EditText(element);
			} else if (type.equals("BUTTON")) {
				return new Button(element);
			} else if (type.equals("RADIO")) {
				return new RadioButton(element);
			} else if (type.equals("CHECKBOX")) {
				return new CheckBox(element);
			} else if (type.equals("IMAGE")) {
				return new ImageButton(element);
			}
		} else if (element.getNodeName() == "SELECT") {
			return new Spinner(element);
		}

		return new View(element);
	}

}
