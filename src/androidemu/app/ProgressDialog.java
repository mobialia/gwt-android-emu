package androidemu.app;

import androidemu.content.Context;
import androidemu.content.DialogInterface;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProgressDialog extends Dialog implements DialogInterface {

	private Label titleLabel, messageLabel;

	public static ProgressDialog show(Context context, String title, String message, boolean indeterminate) {
		return new ProgressDialog(title, message, false);
	}
	
	public static ProgressDialog show(Context context, String title, String message, boolean indeterminate, boolean cancelable) {
		return new ProgressDialog(title, message, cancelable);
	}

	public ProgressDialog(String title, String message, boolean cancelable) {
		popupPanel = new PopupPanel(cancelable);

		VerticalPanel vp = new VerticalPanel();

		if (title != null) {
			titleLabel = new Label(title);
			titleLabel.setStyleName("DialogTitle");
			vp.add(titleLabel);
		}

		if (message != null) {
			messageLabel = new Label(message);
			titleLabel.setStyleName("DialogMessage");
			vp.add(messageLabel);
		}
		
		popupPanel.add(vp);
	}

	public void setTitle(String title) {
		if (messageLabel != null) {
			messageLabel.setText(title);
		}
	}
	
	public void setMessage(String message) {
		if (messageLabel != null) {
			messageLabel.setText(message);
		}
	}
}