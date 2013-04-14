package androidemu.app;

import androidemu.Res;
import androidemu.content.Context;
import androidemu.content.DialogInterface;
import androidemu.view.View;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AlertDialog extends Dialog implements DialogInterface {

	private Label titleLabel, messageLabel;
	DialogInterface.OnClickListener positiveListener, negativeListener, neutralListener;

	public static class Builder {
		View view;
		boolean cancelable = true;
		String title;
		String message;
		CharSequence items[];
		String positiveLabel, negativeLabel, neutralLabel;
		DialogInterface.OnClickListener itemsListener, positiveListener, negativeListener, neutralListener;	

		public Builder(Context ctx) {

		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		public void setCancelable(boolean cancelable) {
			this.cancelable = cancelable;
		}

		public void setPositiveButton(String label, DialogInterface.OnClickListener listener) {
			this.positiveLabel = label;
			this.positiveListener = listener;
		}

		public void setNegativeButton(String label, DialogInterface.OnClickListener listener) {
			this.negativeLabel = label;
			this.negativeListener = listener;
		}

		public void setNeutralButton(String label, DialogInterface.OnClickListener listener) {
			this.neutralLabel = label;
			this.neutralListener = listener;
		}
		
		public void setItems(CharSequence items[], DialogInterface.OnClickListener listener) {
			this.items = items;
			this.itemsListener = listener;
		}

		public void setView(View view) {
			this.view = view;
		}

		public AlertDialog create() {
			AlertDialog dialog = new AlertDialog(this);
			return dialog;
		}
	}

	public AlertDialog(Builder builder) {
		super(builder.cancelable);

		this.positiveListener = builder.positiveListener;
		this.negativeListener = builder.negativeListener;

		VerticalPanel vp = new VerticalPanel();

		if (builder.title != null) {
			titleLabel = new Label(builder.title);
			titleLabel.setStyleName(Res.R.style().dialogTitle());
			vp.add(titleLabel);
		}

		messageLabel = new Label(builder.message != null ? builder.message : "");
		messageLabel.setStyleName(Res.R.style().dialogMessage());
		vp.add(messageLabel);
		
		if (builder.items != null) {
			// TODO
		}
		
		if (builder.view != null) {
			vp.add(builder.view.asPanel());
		}

		Button okButton = null;
		Button cancelButton = null;
		Button neutralButton = null;

		if (builder.positiveLabel != null) {
			okButton = new Button(builder.positiveLabel);
			okButton.setStyleName(Res.R.style().dialogButton());
			okButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					AlertDialog.this.dismiss();
					if (positiveListener != null) {
						positiveListener.onClick(AlertDialog.this, BUTTON_POSITIVE);
					}
				}
			});
		}

		if (builder.negativeLabel != null) {
			cancelButton = new Button(builder.negativeLabel);
			cancelButton.setStyleName(Res.R.style().dialogButton());
			cancelButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					AlertDialog.this.dismiss();
					if (negativeListener != null) {
						negativeListener.onClick(AlertDialog.this, BUTTON_NEGATIVE);
					}
				}
			});
		}
		
		if (builder.neutralLabel != null) {
			cancelButton = new Button(builder.neutralLabel);
			cancelButton.setStyleName(Res.R.style().dialogButton());
			cancelButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					AlertDialog.this.dismiss();
					if (neutralListener != null) {
						neutralListener.onClick(AlertDialog.this, BUTTON_NEUTRAL);
					}
				}
			});
		}

		if (okButton != null || cancelButton != null || neutralButton != null) {
			FlowPanel buttonsPanel = new FlowPanel();
			buttonsPanel.setStyleName(Res.R.style().dialogButtons());
			if (cancelButton != null) {
				buttonsPanel.add(cancelButton);
			}
			if (neutralButton != null) {
				buttonsPanel.add(neutralButton);
			}
			if (okButton != null) {
				buttonsPanel.add(okButton);
			}
			vp.add(buttonsPanel);
		}
		
		popupPanel.add(vp);
	}

	public void setTitle(String title) {
		if (titleLabel != null) {
			titleLabel.setText(title);
		}
	}
	
	public void setMessage(String message) {
		if (messageLabel != null) {
			messageLabel.setText(message);
		}
	}
}