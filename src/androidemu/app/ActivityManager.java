package androidemu.app;

import java.util.Stack;

import androidemu.content.Intent;

public class ActivityManager {
	public static Stack<Activity> activityStack = new Stack<Activity>();

	public static void startActivity(Intent intent, Integer requestCode) {
		if (!activityStack.empty()) {
			Activity currentActivity = activityStack.peek();
			currentActivity.hideMenu();
			currentActivity.onPause();
		}

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
