package android.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class SimpleActivity extends AppCompatActivity {
	public final static String TAG = SimpleActivity.class.getSimpleName();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Android Logging works against the javascript console!
		Log.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);

		// We can use setContentView with HTMLPanels, textResources or widgets,
		// we recomment to use the UiBinder
		setContentView(R.layout.simple_activity);

		getSupportActionBar().setTitle(R.string.simple_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (item.getItemId() == android.R.id.home) {
			finish();
		}

		return super.onOptionsItemSelected(item);
	}

}
