package android.support.v7.app;

import android.Res;
import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gwt.dom.client.Style;

public class ActionBar {
	static final String TAG = "ActionBar";

	Activity activity;
	public ImageView indicatorImageView;
	int indicatorImageRes = android.R.drawable.actionbar_indicator_back;
	boolean displayHomeAsUpEnabled = false;

	LinearLayout view;
	LinearLayout actionBarHome;
	LinearLayout actionBarRight;

	TextView titleView;

	MenuItem homeItem;

	public ActionBar(Activity activity) {
		this.activity = activity;

		view = new LinearLayout(activity);
		view.getElement().setId("ActionBar");
		view.getElement().addClassName(Res.R.style().actionbar());

		actionBarHome = new LinearLayout(activity);
		view.addView(actionBarHome);

		actionBarRight = new LinearLayout(activity);
		actionBarRight.getElement().setId("ActionBarRight");
		actionBarRight.getElement().getStyle().setFloat(Style.Float.RIGHT);
		view.addView(actionBarRight);

		homeItem = new MenuItem();
		homeItem.setIcon(Context.icon);
		homeItem.setItemId(android.R.id.home);

		actionBarHome.getElement().addClassName(Res.R.style().actionbarHome());
		actionBarHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onHomeAction();
			}
		});

		ImageView img = new ImageView(activity);
		img.getElement().addClassName(Res.R.style().actionbarIcon());
		img.setImageResource(homeItem.getIcon());
		actionBarHome.addView(img);

		titleView = new TextView(activity);
		titleView.getElement().addClassName(Res.R.style().actionbarTitle());
		actionBarHome.addView(titleView);
	}

	private void onHomeAction() {
		activity.onMenuItemSelected(0, homeItem);
	}

	public void setTitle(int title) {
		setTitle(Context.resources.getString(title));
	}

	public void setTitle(String title) {
		titleView.setText(title);
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
			view.addView(indicatorImageView);
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
