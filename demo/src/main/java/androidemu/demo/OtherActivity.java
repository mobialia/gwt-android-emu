package androidemu.demo;

import androidemu.app.Activity;
import androidemu.demo.res.R;
import androidemu.os.Bundle;
import androidemu.util.Log;

public class OtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.other());
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();
	};

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	};

}
