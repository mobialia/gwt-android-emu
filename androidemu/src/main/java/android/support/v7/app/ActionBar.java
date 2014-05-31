package android.support.v7.app;

import android.Res;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBar {
	static final String TAG = "ActionBar";

	Activity activity;
	public ImageView indicatorImageView;
	int indicatorImageRes = android.R.drawable.actionbar_indicator_back;
	boolean displayHomeAsUpEnabled = false;

	public ActionBar(Activity activity) {
		this.activity = activity;
	}

	public void setTitle(int title) {
		setTitle(Context.resources.getString(title));
	}

	public void setTitle(String title) {
		((TextView) activity.view.findViewById("ActionBarTitle")).setText(title);
	}

	public void setDisplayHomeAsUpEnabled(boolean displayHomeAsUpEnabled) {
		if (this.displayHomeAsUpEnabled == displayHomeAsUpEnabled) {
			return;
		}
		this.displayHomeAsUpEnabled = displayHomeAsUpEnabled;

		if (displayHomeAsUpEnabled) {
			indicatorImageView = new ImageView(activity);
			indicatorImageView.setImageResource(indicatorImageRes);
			indicatorImageView.element.addClassName(Res.R.style().actionbarHomeAsUp());

			LinearLayout actionBarLeft = new LinearLayout(ViewFactory.getElementById(activity.view.getElement(), "ActionBarLeft"));
			if (actionBarLeft.getElement() == null) {
				Log.e(TAG, "ActionBarLeft div not found");
				return;
			}
			actionBarLeft.element.getParentElement().appendChild(indicatorImageView.getElement());
		} else {
			if (indicatorImageView != null) {
				indicatorImageView.getElement().removeFromParent();
				indicatorImageView = null;
			}
		}
	}

	public void setHomeButtonEnabled(boolean homeButtonEnabled) {

	}

	public void setHomeAsUpIndicator(int resId) {
		this.indicatorImageRes = resId;
		if (indicatorImageView != null) {
			indicatorImageView.setImageResource(indicatorImageRes);
		}
	}
}
