package android.app;

import android.content.Intent;
import android.os.IBinder;

public abstract class Service extends ContextWrapper {

	public static final int START_CONTINUATION_MASK = 0x0000000f;
	public static final int START_FLAG_REDELIVERY = 0x00000001;
	public static final int START_FLAG_RETRY = 0x00000002;
	public static final int START_NOT_STICKY = 0x00000002;
	public static final int START_REDELIVER_INTENT = 0x00000003;
	public static final int START_STICKY = 0x00000001;
	public static final int START_STICKY_COMPATIBILITY = 0x00000000;

	public int status = ServiceManager.STATUS_NEW;

	public abstract IBinder onBind(Intent intent);

	public int onStartCommand(Intent intent, int flags, int startId) {
		return 0;
	}

	public final void startForeground(int id, Notification notification) {

	}

	public final void stopForeground(boolean removeNotification) {

	}

	public final void stopSelf() {

	}

	public void onCreate() {
	}

	public void onDestroy() {
	}

}
