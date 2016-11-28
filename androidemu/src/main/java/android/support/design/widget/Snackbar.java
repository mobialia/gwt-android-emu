package android.support.design.widget;

import android.Res;
import android.content.Context;
import android.view.View;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

import java.util.ArrayList;

public class Snackbar {

	public static final int LENGTH_INDEFINITE = 0xfffffffe;
	public static final int LENGTH_LONG = 0x00000000;
	public static final int LENGTH_SHORT = 0xffffffff;

	public static Snackbar make(View view, CharSequence text, int duration) {
		return new Snackbar(view, text, duration);
	}

	String message;
	int duration;

	PopupPanel panel = new PopupPanel();

	ArrayList<String> actions = new ArrayList<>();
	ArrayList<View.OnClickListener> listeners = new ArrayList<>();

	public Snackbar(View view, CharSequence text, int duration) {
		message = text.toString();
		this.duration = duration;
	}

	public Snackbar setAction(int resId, View.OnClickListener listener) {
		actions.add(Context.resources.getString(resId));
		listeners.add(listener);
		return this;
	}

	public void show() {
		panel.setStyleName(Res.R.style().snackbar());

		FlowPanel fp = new FlowPanel();
		HTML label = new HTML(message.replace("\n", "<br/>"));
		label.setStyleName(Res.R.style().snackbarMessage());
		fp.add(label);
		for (int i = 0; i < actions.size(); i++) {
			Button button = new Button(actions.get(i));
			button.addStyleName(Res.R.style().snackbarButton());
			button.addStyleName(Res.R.style().controlHighlight());

			final View.OnClickListener listener = listeners.get(i);
			if (listener != null) {
				button.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Snackbar.this.dismiss();
						listener.onClick(null); // TODO
					}
				});
			}
			fp.add(button);
		}

		panel.setWidget(fp);

		panel.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
			public void setPosition(int offsetWidth, int offsetHeight) {
				int left = (Window.getClientWidth() - offsetWidth) / 2;
				int top = Window.getClientHeight() - offsetHeight;
				panel.setPopupPosition(left, top);
			}
		});

		if (duration != LENGTH_INDEFINITE) {
			// Create a new timer that calls hide().
			Timer t = new Timer() {
				public void run() {
					panel.hide();
				}
			};

			if (duration == LENGTH_SHORT) {
				t.schedule(2500);
			} else {
				t.schedule(4000);
			}
		}
	}

	public void dismiss() {
		panel.hide();
	}
}
