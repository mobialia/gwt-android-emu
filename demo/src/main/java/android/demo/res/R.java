package android.demo.res;

import com.google.gwt.core.client.GWT;

public class R {

	// Strings and Arrays are simulated with the GWT Constants interface
	// There is an utility "ConvertStrings" in AndroidEmu to convert any Android
	// XML file to Strings.java, Strings.properties, Arrays.java and
	// Arrays.properties
	public static final Strings string = GWT.create(Strings.class);

	public static final Arrays array = GWT.create(Arrays.class);

	// Layouts are more complex, we use the UIBinder
	public static final Layouts layout = new Layouts();

	public static final Raw raw = GWT.create(Raw.class);

}
