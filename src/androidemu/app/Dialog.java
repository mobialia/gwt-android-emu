package androidemu.app;

import androidemu.content.DialogInterface;

import com.google.gwt.user.client.ui.PopupPanel;

public class Dialog implements DialogInterface {

	PopupPanel popupPanel;
	
	public Dialog() {
		
	}

	public boolean isShowing() {
		return popupPanel.isShowing();
	}
	
	@Override
	public void cancel() {
		popupPanel.hide();
	}

	@Override
	public void dismiss() {
		popupPanel.hide();
	}

	public void show() {
		popupPanel.center();
		popupPanel.show();
	}
}
