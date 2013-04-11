package androidemu.app;

import java.util.Stack;

import androidemu.content.Intent;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class ActivityManager {
	public static Stack<Activity> activityStack = new Stack<Activity>();

	static {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// We manage only back press
				if (activityStack.size() > 1) {
					// If is back, must match the previous activity in the stack
					if (event.getValue().equals(activityStack.get(activityStack.size() - 2).getClass().getName())) {
						finish();
					}
				}
			}
		});
	}

	public static void startActivity(Intent intent, Integer requestCode) {
		if (!activityStack.empty()) {
			Activity currentActivity = activityStack.peek();
			currentActivity.hideMenu();
			currentActivity.onPause();
		}

		// Save browser history
		History.newItem(intent.activity.getClass().getName());
		activityStack.push(intent.activity);
		intent.activity.requestCode = requestCode;
		intent.activity.onCreate(null);
		intent.activity.onResume();
		intent.activity.showMenu();
	}

	public static void finish() {
		Activity closingActivity = activityStack.pop();
		closingActivity.hideMenu();
		closingActivity.onPause();
		closingActivity.onDestroy();
		Integer requestCode = closingActivity.requestCode;

		Activity resumedActivity = activityStack.peek();
		if (requestCode != null) {
			resumedActivity.onActivityResult(requestCode, closingActivity.resultCode, closingActivity.resultData);
		}
		resumedActivity.onResume();
		resumedActivity.showMenu();
	}

}
