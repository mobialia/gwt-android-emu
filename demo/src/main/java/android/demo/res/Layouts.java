package android.demo.res;

import android.demo.res.layout.DemoFragment;
import android.demo.res.layout.DemoFragment2;
import android.demo.res.layout.DemoFragmentActivity;
import android.demo.res.layout.Main;

/**
 * Simulate the Android layouts with the UiBinder, each screens needs a class and a XML
 * For each method in this class GenerateResources will create a R.layout.XXX entry
 */
public class Layouts {

	public Main main() {
		return new Main();
	}

	public DemoFragmentActivity demo_fragment_activity() {
		return new DemoFragmentActivity();
	}

	public DemoFragment demo_fragment() {
		return new DemoFragment();
	}

	public DemoFragment2 demo_fragment2() {
		return new DemoFragment2();
	}

}
