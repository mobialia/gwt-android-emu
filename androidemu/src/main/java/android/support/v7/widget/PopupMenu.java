package android.support.v7.widget;

import android.Res;
import android.content.Context;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class PopupMenu {

	Context context;
	View anchor;
	Menu menu;
	OnMenuItemClickListener mMenuItemClickListener;
	private LinearLayout menuLayout;

	public PopupMenu(Context context, View anchor) {
		this(context, anchor, Gravity.NO_GRAVITY);
	}

	public PopupMenu(Context context, View anchor, int gravity) {
		this.context = context;
		this.anchor = anchor;
		menu = new Menu();
	}

	public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
		mMenuItemClickListener = listener;
	}

	public Menu getMenu() {
		return menu;
	}

	public MenuInflater getMenuInflater() {
		return new MenuInflater();
	}

	public void show() {
		if (menuLayout == null) {
			menuLayout = new LinearLayout(context);
			menuLayout.getElement().addClassName(Res.R.style().dialog());
			menuLayout.getElement().addClassName(Res.R.style().gone());

			for (final MenuItem item : menu.menuItems) {
				if (item.getTitle() != 0 || item.getIcon() != 0) {
					Button b = new Button(context);
					if (item.getItemId() != 0) {
						b.getElement().setId(context.getResources().getIdAsString(item.getItemId()));
					}
					if (item.getTitle() != 0) {
						b.setText(context.getString(item.getTitle()));
					}
					if (item.getTitleString() != null) {
						b.setText(item.getTitleString());
					}
					b.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dismiss();
							if (mMenuItemClickListener != null) {
								mMenuItemClickListener.onMenuItemClick(item);
							}
						}
					});
					b.getElement().addClassName(Res.R.style().menuItem());
					menuLayout.addView(b);
				}
			}
			if (menuLayout != null && menuLayout.getElement().hasClassName(Res.R.style().gone())) {
				menuLayout.getElement().removeClassName(Res.R.style().gone());
			}
			anchor.getElement().appendChild(menuLayout.getElement());
		}

		if (menuLayout.getElement().hasClassName(Res.R.style().gone())) {
			menuLayout.getElement().removeClassName(Res.R.style().gone());
		}
	}

	public void dismiss() {
		if (menuLayout != null && !menuLayout.getElement().hasClassName(Res.R.style().gone())) {
			menuLayout.getElement().addClassName(Res.R.style().gone());
		}
	}

	/**
	 * NOT STANDARD
	 */
	public void destroy() {
		if (menuLayout != null) {
			menuLayout.getElement().removeFromParent();
		}
	}

	/**
	 * NOT STANDARD, used by the system action bar
	 */
	public void toggle() {
		if (menuLayout.getElement().hasClassName(Res.R.style().gone())) {
			show();
			menuLayout.getElement().getStyle().setProperty("position", "fixed");
			menuLayout.getElement().getStyle().setPropertyPx("left", anchor.getLeft() + anchor.getWidth() - menuLayout.getWidth());
			menuLayout.getElement().getStyle().setPropertyPx("top", anchor.getTop() + anchor.getHeight());
		} else {
			dismiss();
		}
	}

	public interface OnMenuItemClickListener {
		boolean onMenuItemClick(MenuItem item);
	}
}
