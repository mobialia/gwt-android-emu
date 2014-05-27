package android.demo.res;

import android.demo.res.layout.Main;
import android.demo.res.layout.Other;

/**
 * Simulate the Android layouts with the UiBinder, each screens needs a class and a XML
 * For each method in this class GenerateResources will create a R.layout.XXX entry
 */
public class Layouts {

	public Main main() {
		return new Main();
	}

	public Other other() {
		return new Other();
	}

}
