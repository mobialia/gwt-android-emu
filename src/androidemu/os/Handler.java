package androidemu.os;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Timer;

public class Handler {

	public void handleMessage(Message msg) {

	}

	public void sendEmptyMessage(int what) {
		sendMessage(new Message(what));
	}

	public void sendMessage(final Message msg) {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				handleMessage(msg);
			}
		});
	}

	public void sendMessageAtTime(final Message msg, long uptimeMillis) {

		Timer timer = new Timer() {
			public void run() {
				handleMessage(msg);
			}
		};

		// Execute the timer to expire 2 seconds in the future
		timer.schedule((int) (uptimeMillis - SystemClock.uptimeMillis()));
	}

}
