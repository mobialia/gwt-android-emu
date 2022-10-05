package android.support.design.widget;

import android.Res;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;

import java.util.ArrayList;

public class Snackbar {
	public static final int LENGTH_INDEFINITE = 0xfffffffe;
	public static final int LENGTH_LONG = 0x00000000;
	public static final int LENGTH_SHORT = 0xffffffff;

	public static Snackbar make(View view, CharSequence text, int duration) {
		return new Snackbar(view, text, duration);
	}

	public static Snackbar make(View view, int resId, int duration) {
		return new Snackbar(view, view.getContext().getString(resId), duration);
	}

	Context ctx;
	String message;
	int duration;

	PopupPanel popupPanel = new PopupPanel();

	ArrayList<String> actions = new ArrayList<>();
	ArrayList<View.OnClickListener> listeners = new ArrayList<>();

	public Snackbar(View view, CharSequence text, int duration) {
		this.ctx = view.getContext();
		message = text.toString();
		this.duration = duration;
	}

	public Snackbar setAction(int resId, View.OnClickListener listener) {
		actions.add(Context.resources.getString(resId));
		listeners.add(listener);
		return this;
	}

	public void show() {
		popupPanel.setStyleName(Res.R.style().snackbar());

		LinearLayout snackbarLayout = new LinearLayout(ctx);
		TextView label = new TextView(ctx);
		label.setText(message);
		label.getElement().addClassName(Res.R.style().snackbarMessage());
		snackbarLayout.addView(label);
		for (int i = 0; i < actions.size(); i++) {
			Button button = new Button(ctx);
			button.setText(actions.get(i));
			button.getElement().addClassName(Res.R.style().snackbarButton());
			button.getElement().addClassName(Res.R.style().controlHighlight());

			final View.OnClickListener listener = listeners.get(i);
			if (listener != null) {
				button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Snackbar.this.dismiss();
						listener.onClick(null); // TODO
					}
				});
			}
			snackbarLayout.addView(button);
		}

		popupPanel.getElement().appendChild(snackbarLayout.getElement());

		popupPanel.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
			public void setPosition(int offsetWidth, int offsetHeight) {
				int left = (Window.getClientWidth() - offsetWidth) / 2;
				int top = Window.getClientHeight() - offsetHeight;
				popupPanel.setPopupPosition(left, top);
			}
		});

		if (duration != LENGTH_INDEFINITE) {
			// Create a new timer that calls hide().
			Timer t = new Timer() {
				public void run() {
					popupPanel.hide();
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
		popupPanel.hide();
	}
}
