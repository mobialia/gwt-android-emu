package androidemu.demo.res;

import androidemu.demo.res.layout.Main;
import androidemu.demo.res.layout.Other;

public class Layouts {

	/**
	 * We simluate the Andorid layouts with the UiBinder, each screens needs a
	 * class and a XML
	 * 
	 * @return
	 */
	public Main main() {
		return new Main();
	}

	public Other other() {
		return new Other();
	}

}
