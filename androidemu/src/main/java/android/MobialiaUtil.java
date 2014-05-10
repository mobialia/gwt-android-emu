package android;

public class MobialiaUtil {

	public static int arrayPosition(String array[], String value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				return i;
			}
		}
		return 0;
	}

	public static String ifNullBlank(String in) {
		return (in == null ? "" : in);
	}
}
