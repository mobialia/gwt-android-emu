package android.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SimpleActivity extends AppCompatActivity {
	public final static String TAG = SimpleActivity.class.getSimpleName();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Android Logging works against the javascript console!
		Log.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);

		// We can use setContentView with HTMLPanels, textResources or widgets,
		// we recomment to use the UiBinder
		setContentView(R.layout.main);

		getSupportActionBar().setTitle(R.string.simple_activity);
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

}
