package androidemu.app;

import androidemu.Res;
import androidemu.content.Context;
import androidemu.content.DialogInterface;
import androidemu.view.View;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class AlertDialog extends Dialog implements DialogInterface {

	private SimplePanel titleLabelContainer;
	private FlowPanel contentPanel;
	private Label titleLabel, messageLabel;
	DialogInterface.OnClickListener itemsListener, positiveListener, negativeListener, neutralListener;

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

		this.itemsListener = builder.itemsListener;
		this.positiveListener = builder.positiveListener;
		this.negativeListener = builder.negativeListener;

		FlowPanel fp = new FlowPanel();

		titleLabelContainer = new SimplePanel();
		fp.add(titleLabelContainer);

		contentPanel = new FlowPanel();
		fp.add(contentPanel);

		setTitle(builder.title);
		setMessage(builder.message);

		if (builder.items != null) {
			int count = 0;
			for (CharSequence item : builder.items) {
				final int countFinal = count;
				count++;

				Button button = new Button(item.toString());
				button.setStyleName(Res.R.style().dialogItem());
				button.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						AlertDialog.this.dismiss();
						if (itemsListener != null) {
							itemsListener.onClick(AlertDialog.this, countFinal);
						}
					}
				});
				contentPanel.add(button);
			}
		}

		if (builder.view != null) {
			if (builder.view.widget != null) {
				contentPanel.add(builder.view.widget);
			} else if (builder.view.element != null) {
				HTMLPanel htmlPanel = new HTMLPanel("");
				htmlPanel.getElement().appendChild(builder.view.element);
				contentPanel.add(htmlPanel);
			}
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
				SimplePanel panel = new SimplePanel();
				panel.setStyleName(Res.R.style().dialogButtonContainer());
				panel.add(cancelButton);
				buttonsPanel.add(panel);
			}
			if (neutralButton != null) {
				SimplePanel panel = new SimplePanel();
				panel.setStyleName(Res.R.style().dialogButtonContainer());
				panel.add(neutralButton);
				buttonsPanel.add(panel);
			}
			if (okButton != null) {
				SimplePanel panel = new SimplePanel();
				panel.setStyleName(Res.R.style().dialogButtonContainer());
				panel.add(okButton);
				buttonsPanel.add(panel);
			}
			fp.add(buttonsPanel);
		}

		popupPanel.add(fp);
	}

	public void setTitle(String title) {
		if (titleLabel == null && title != null && !"".equals(title)) {
			titleLabel = new Label();
			titleLabel.setStyleName(Res.R.style().dialogTitle());
			titleLabelContainer.add(titleLabel);
		}
		if (titleLabel != null) {
			titleLabel.setText(title);
		}
		if (popupPanel.isShowing()) {
			popupPanel.center();
		}
	}

	public void setMessage(String message) {
		if (messageLabel == null && message != null && !"".equals(message)) {
			messageLabel = new Label();
			messageLabel.setStyleName(Res.R.style().dialogMessage());
			contentPanel.add(messageLabel);
		}
		if (messageLabel != null) {
			messageLabel.setText(message);
		}
		if (popupPanel.isShowing()) {
			popupPanel.center();
		}
	}
}