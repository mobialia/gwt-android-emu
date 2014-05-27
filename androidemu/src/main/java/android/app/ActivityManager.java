package android.app;

import android.content.Intent;
import android.util.Log;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;

import java.util.Stack;

public class ActivityManager {
	public final static String TAG = "ActivityManager";

	public final static int STATUS_NEW = 0;
	public final static int STATUS_CREATED = 1;
	public final static int STATUS_RESUMED = 2;
	public final static int STATUS_PAUSED = 3;
	public final static int STATUS_DESTROYED = 4;

	public static Stack<Activity> activityStack = new Stack<Activity>();

	public static void setup() {
		if (DOM.getElementById("show-while-loading") != null) {
			DOM.getElementById("show-while-loading").setAttribute("style", "display: none");
		}
	}

	public static void startActivity(Intent intent, Integer requestCode) {
		if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			// Open URL
			Window.Location.assign(intent.getData().toString());
		} else {
			Log.d(TAG, "Start activity " + intent.activity.getClass().getName());
			intent.activity.intent = intent;
			activityStack.push(intent.activity);
			intent.activity.requestCode = requestCode;
			checkActivityStackDeferred();
		}
	}

	public static void finish(final Activity activity) {
		if (activityStack.size() <= 1) {
			Log.d(TAG, "Not finishing activity " + activity.getClass().getName() + " because it's the last one");
			return;
		}
		Log.d(TAG, "Finish activity " + activity.getClass().getName());
		activity.targetStatus = STATUS_DESTROYED;
		checkActivityStackDeferred();
	}

	public static void back() {
		activityStack.peek().onBackPressed();
	}

	private static void advanceStatus(Activity activity) {
		while (activity.status < activity.targetStatus) {
			switch (activity.status) {
				case STATUS_NEW:
					activity.onCreate(null);
					activity.onPostCreate(null);
					activity.status = STATUS_CREATED;
					break;
				case STATUS_CREATED:
					activity.onResume();
					activity.invalidateOptionsMenu();
					activity.onPostResume();
					activity.status = STATUS_RESUMED;
					break;
				case STATUS_RESUMED:
					activity.closeOptionsMenu();
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
					activity.status = STATUS_RESUMED;
					break;
			}
		}
	}

	private static void checkActivityStackDeferred() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				try {
					checkActivityStack();
				} catch (Throwable e) {
					Log.e(TAG, e.getMessage());

					StringBuffer stb = new StringBuffer();
					stb.append(e.getMessage()).append("\n\nStack trace:\n");
					for (int i = 0; i < e.getStackTrace().length; i++) {
						stb.append(e.getStackTrace()[i].toString()).append("\n");
					}
					Log.e(TAG, stb.toString());
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