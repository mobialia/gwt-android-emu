package androidemu.app;

import java.util.Stack;

import androidemu.content.Intent;
import androidemu.util.Log;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class ActivityManager {
	public final static String TAG = "ActivityManager";

	public final static int STATUS_NEW = 0;
	public final static int STATUS_CREATED = 1;
	public final static int STATUS_RESUMED = 2;
	public final static int STATUS_PAUSED = 3;
	public final static int STATUS_DESTROYED = 4;

	public static Stack<Activity> activityStack = new Stack<Activity>();
	static Button backButton, menuButton;

	// static {
	// History.addValueChangeHandler(new ValueChangeHandler<String>() {
	// @Override
	// public void onValueChange(ValueChangeEvent<String> event) {
	// // TODO We manage only back press
	// if (activityStack.size() > 1) {
	// // If is back, must match the previous activity in the stack
	// if (event.getValue().equals(activityStack.get(activityStack.size() -
	// 2).getClass().getName())) {
	// finish(activityStack.peek());
	// }
	// }
	// }
	// });
	// }

	public static void setup() {
		if (RootPanel.get("BackButton") != null) {
			backButton = Button.wrap(RootPanel.get("BackButton").getElement());
			backButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ActivityManager.back();
				}
			});
		}
		if (RootPanel.get("MenuButton") != null) {
			menuButton = Button.wrap(RootPanel.get("MenuButton").getElement());
			menuButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ActivityManager.openOptionsMenu();
				}
			});
		}
	}

	public static void startActivity(Intent intent, Integer requestCode) {
		Log.d(TAG, "startActivity " + intent.activity.getClass().getName());
		activityStack.push(intent.activity);
		intent.activity.requestCode = requestCode;
		checkActivityStackDeferred();
	}

	public static void finish(final Activity activity) {
		Log.d(TAG, "finish " + activity.getClass().getName());
		activity.targetStatus = STATUS_DESTROYED;
		checkActivityStackDeferred();
	}

	public static void back() {
		activityStack.peek().onBackPressed();
		if (activityStack.size() > 1) {
			finish(activityStack.peek());
		}
	}

	public static void openOptionsMenu() {
		activityStack.peek().openOptionsMenu();
	}

	private static void advanceStatus(Activity activity) {
		while (activity.status < activity.targetStatus) {
			switch (activity.status) {
			case STATUS_NEW:
				activity.onCreate(null);
				activity.status = STATUS_CREATED;
				break;
			case STATUS_CREATED:
				// Add to browser history
				// History.newItem(activity.getClass().getName());

				activity.onResume();
				activity.createMenu();
				checkButtonsVisibility(activity);
				activity.status = STATUS_RESUMED;
				break;
			case STATUS_RESUMED:
				activity.hideMenu();
				activity.onPause();
				activity.status = STATUS_PAUSED;
				break;
			case STATUS_PAUSED:
				activity.onDestroy();
				activity.status = STATUS_DESTROYED;
				activityStack.remove(activity);
			}
		}
		while (activity.status > activity.targetStatus) {
			switch (activity.status) {
			case STATUS_PAUSED:
				if (activity.returnRequestCode != null) {
					activity.onActivityResult(activity.returnRequestCode, activity.returnResultCode, activity.returnResultData);
				}
				activity.onResume();
				checkButtonsVisibility(activity);
				activity.status = STATUS_RESUMED;
				break;
			}
		}
	}

	private static void checkButtonsVisibility(Activity activity) {
		if (menuButton != null) {
			menuButton.setVisible(activity.menu != null && activity.menu.menuItems.size() > 0);
		}
	}

	private static void checkActivityStackDeferred() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				try {
					checkActivityStack();
				} catch (Exception e) {
					Log.e(TAG, e.getMessage());
				}
			}
		});
	}

	private static void checkActivityStack() {
		// Finish Activities (and store result codes in caller activities)
		for (int i = 0; i < activityStack.size(); i++) {
			Activity act = activityStack.get(i);
			if (act.targetStatus == STATUS_DESTROYED) {
				if (i - 1 >= 0) {
					// Save return data in caller activity
					Activity callerActivity = activityStack.get(i - 1);
					callerActivity.returnRequestCode = act.requestCode;
					callerActivity.returnResultCode = act.resultCode;
					callerActivity.returnResultData = act.resultData;
				}
				// TODO deleted element while transversing the list
				advanceStatus(act);
			}
		}

		// Pause all activities not shown
		for (int i = 0; i < activityStack.size() - 1; i++) {
			Activity act = activityStack.get(i);
			if (act.status != STATUS_PAUSED) {
				act.targetStatus = STATUS_PAUSED;
				advanceStatus(act);
			}
		}

		// Start activity to show
		Activity activityToShow = activityStack.peek();
		if (activityToShow.status != STATUS_RESUMED) {
			activityToShow.targetStatus = STATUS_RESUMED;
			advanceStatus(activityToShow);
		}
	}

}