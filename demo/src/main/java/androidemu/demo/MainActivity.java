package androidemu.demo;

import androidemu.app.Activity;
import androidemu.app.AlertDialog;
import androidemu.content.DialogInterface;
import androidemu.content.Intent;
import androidemu.demo.res.R;
import androidemu.os.Bundle;
import androidemu.os.Handler;
import androidemu.os.Message;
import androidemu.util.Log;
import androidemu.view.Gravity;
import androidemu.view.Menu;
import androidemu.view.MenuItem;
import androidemu.view.View;
import androidemu.view.View.OnClickListener;
import androidemu.widget.Button;
import androidemu.widget.Toast;

import com.google.gwt.core.client.GWT;

public class MainActivity extends Activity implements OnClickListener, DialogInterface.OnClickListener {
	public final static String TAG = "IntroActivity";

	public final static int MENU1 = 1;
	public final static int MENU2 = 2;

	// Android Widgets are mapped from HTML elements, we cannot create Android
	// Widgets from code, but we can access them via findViewById
	Button alertButton, toastButton, activityButton, handlerButton;

	// Handler works!
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Toast.makeText(getApplicationContext(), R.string.handler_message_received(), Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Android Logging works against the javascript console!
		Log.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);

		// We can use setContentView with HTMLPanels, textResources or widgets,
		// we recomment to use the UiBinder
		setContentView(R.layout.main());
		// A small difference: resources must be accessed with methods, ending
		// with (). See the class R for more resource simulation info

		// Resurces IDs are now Strings, must match the id element in the HTML
		alertButton = ((Button) findViewById("AlertButton"));
		alertButton.setOnClickListener(this);

		toastButton = ((Button) findViewById("ToastButton"));
		toastButton.setOnClickListener(this);

		handlerButton = ((Button) findViewById("HandlerButton"));
		handlerButton.setOnClickListener(this);

		activityButton = ((Button) findViewById("ActivityButton"));
		activityButton.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		// Androidemu simulates the Activity life cycle
		Log.d(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		// Menu works, but we cannot set images for menu items
		menu.add(0, MENU1, 0, R.string.menu1());
		menu.add(0, MENU2, 0, R.string.menu2());
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU1:
			Toast.makeText(getApplicationContext(), R.string.menu1(), Toast.LENGTH_SHORT).show();
			return true;
		case MENU2:
			Toast.makeText(getApplicationContext(), R.string.menu2(), Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		if (v == alertButton) {
			// AlertDialog usage sample
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.dialog_title());
			builder.setMessage(R.string.dialog_message());
			builder.setPositiveButton(R.string.dialog_yes(), this);
			builder.setNegativeButton(R.string.dialog_no(), this);
			builder.create().show();
		} else if (v == toastButton) {
			// Toast usage sample, gravities and durations work!
			Toast toast = Toast.makeText(getApplicationContext(), R.string.toast_text(), Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else if (v == handlerButton) {
			handler.sendEmptyMessage(0);
		} else if (v == activityButton) {
			// A small difference, as GWT does not has runtime reflection, we
			// must pass a created class to the intent (we can use GWT.create())
			Intent intent = new Intent(getApplicationContext(), GWT.create(OtherActivity.class));
			startActivity(intent);
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {

	}

}
