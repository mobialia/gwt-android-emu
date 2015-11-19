package android.app;

import android.AndroidManifest;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class ServiceManager {
	public final static String TAG = "ServiceManager";

	public final static int STATUS_NEW = 0;
	public final static int STATUS_CREATED = 1;

	public static void startService(Intent intent) {
		Service service = AndroidManifest.instance.getService(intent.intentClass);
		if (service == null) {
			Log.e(TAG, "Service class not defined in Manifest");
			return;
		}
		if (service.status == STATUS_NEW) {
			Log.d(TAG, "Start service " + service.getClass().getName());
			service.onCreate();
			service.status = STATUS_CREATED;
		}
	}

	public static boolean bindService(Intent intent, ServiceConnection conn, int flags) {
		Service service = AndroidManifest.instance.getService(intent.intentClass);
		IBinder ibinder = service.onBind(intent);
		conn.onServiceConnected(null, ibinder);
		return true;
	}

	public static void finish(final Service service) {
		Log.d(TAG, "Finish service " + service.getClass().getName());
		if (service.status != STATUS_CREATED) {
			Log.d(TAG, "Not finishing service " + service.getClass().getName() + " because it's already finished");
			return;
		}
		service.stopSelf();
		service.onDestroy(); // TODO must destroy service class???
		service.status = STATUS_NEW;
	}
}