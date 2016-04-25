package android.app;

import android.Res;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class AlertDialog extends Dialog implements DialogInterface {

	private SimplePanel titleLabelContainer;
	private FlowPanel contentPanel;
	private Label titleLabel;
	private HTML messageLabel;
	DialogInterface.OnClickListener itemsListener, positiveListener, negativeListener, neutralListener;

	public static class Builder {
		Context ctx;
		View view;

		boolean cancelable = true;
		String title;
		String message;
		CharSequence items[];
		String positiveLabel, negativeLabel, neutralLabel;
		DialogInterface.OnClickListener itemsListener, positiveListener, negativeListener, neutralListener;

		public Builder(Context ctx) {
			this.ctx = ctx;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setTitle(int title) {
			setTitle(ctx.getString(title));
		}

		public void setMessage(int message) {
			setMessage(ctx.getString(message));
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public void setCancelable(boolean cancelable) {
			this.cancelable = cancelable;
		}

		public void setPositiveButton(int label, DialogInterface.OnClickListener listener) {
			setPositiveButton(ctx.getString(label), listener);
		}

		public void setPositiveButton(String label, DialogInterface.OnClickListener listener) {
			this.positiveLabel = label;
			this.positiveListener = listener;
		}

		public void setNegativeButton(int label, DialogInterface.OnClickListener listener) {
			setNegativeButton(ctx.getString(label), listener);
		}

		public void setNegativeButton(String label, DialogInterface.OnClickListener listener) {
			this.negativeLabel = label;
			this.negativeListener = listener;
		}

		public void setNeutralButton(int label, DialogInterface.OnClickListener listener) {
			setNeutralButton(ctx.getString(label), listener);
		}

		public void setNeutralButton(String label, DialogInterface.OnClickListener listener) {
			this.neutralLabel = label;
			this.neutralListener = listener;
		}

		public void setItems(int items, DialogInterface.OnClickListener listener) {
			this.items = ctx.getResources().getStringArray(items);
			this.itemsListener = listener;
		}

		public void setItems(CharSequence items[], DialogInterface.OnClickListener listener) {
			this.items = items;
			this.itemsListener = listener;
		}

		public void setView(View view) {
			this.view = view;
		}

		public AlertDialog create() {
			return new AlertDialog(this);
		}
	}

	public AlertDialog(Builder builder) {
		super(builder.cancelable);

		this.itemsListener = builder.itemsListener;
		this.positiveListener = builder.positiveListener;
		this.negativeListener = builder.negativeListener;
		this.neutralListener = builder.neutralListener;

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
			if (builder.view.getElement() != null) {
				HTMLPanel htmlPanel = new HTMLPanel("");
				htmlPanel.getElement().appendChild(builder.view.getElement());
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
			neutralButton = new Button(builder.neutralLabel);
			neutralButton.setStyleName(Res.R.style().dialogButton());
			neutralButton.addClickHandler(new ClickHandler() {
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
			fp.add(buttonsPanel);
		}

		popupPanel.add(fp);
	}

	public void setTitle(int title) {
		setTitle(Context.resources.getString(title));
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

	public void setMessage(int message) {
		setMessage(Context.resources.getString(message));
	}

	public void setMessage(String message) {
		if (messageLabel == null && message != null && !"".equals(message)) {
			messageLabel = new HTML();
			messageLabel.setStyleName(Res.R.style().dialogMessage());
			contentPanel.add(messageLabel);
		}
		if (messageLabel != null) {
			messageLabel.setHTML(message.replace("\n", "<br/>"));
		}
		if (popupPanel.isShowing()) {
			popupPanel.center();
		}
	}
}