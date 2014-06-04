package android.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.gwt.core.client.GWT;

public class MainActivity extends ActionBarActivity implements OnClickListener, DialogInterface.OnClickListener {
	public final static String TAG = "IntroActivity";

	// Android Widgets are mapped from HTML elements, we cannot create Android
	// Widgets from code, but we can access them via findViewById
	Button alertButton, toastButton, activityButton, handlerButton;

	// Handler works!
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Toast.makeText(getApplicationContext(), R.string.handler_message_received, Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Android Logging works against the javascript console!
		Log.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);

		// We can use setContentView with HTMLPanels, textResources or widgets,
		// we recomment to use the UiBinder
		setContentView(R.layout.main);

		getSupportActionBar().setTitle(R.string.activity1);


		// ID (numeric or String) must match the id element in the HTML
		alertButton = ((Button) findViewById(R.id.AlertButton));
		alertButton.setOnClickListener(this);

		toastButton = ((Button) findViewById(R.id.ToastButton));
		toastButton.setOnClickListener(this);

		handlerButton = ((Button) findViewById(R.id.HandlerButton));
		handlerButton.setOnClickListener(this);

		activityButton = ((Button) findViewById(R.id.ActivityButton));
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

		getMenuInflater().inflate(R.menu.demo_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action1:
				Toast.makeText(getApplicationContext(), R.string.action1, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menu1:
				Toast.makeText(getApplicationContext(), R.string.menu1, Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menu2:
				Toast.makeText(getApplicationContext(), R.string.menu2, Toast.LENGTH_SHORT).show();
				return true;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		if (v == alertButton) {
			// AlertDialog usage sample
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.dialog_title);
			builder.setMessage(R.string.dialog_message);
			builder.setPositiveButton(R.string.dialog_yes, this);
			builder.setNegativeButton(R.string.dialog_no, this);
			builder.create().show();
		} else if (v == toastButton) {
			// Toast usage sample, gravities and durations work!
			Toast toast = Toast.makeText(getApplicationContext(), R.string.toast_text, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else if (v == handlerButton) {
			handler.sendEmptyMessage(0);
		} else if (v == activityButton) {
			// A small difference, as GWT does not has runtime reflection, we
			// must pass a created class to the intent (we can use GWT.create())
			Intent intent = new Intent(getApplicationContext(), GWT.create(DemoFragmentActivity.class));
			startActivity(intent);
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {

	}

}
