package androidemu.util;


public class Log {

	private static native void log(String message) /*-{
		console.log(message);
	}-*/;

	private static native void error(String message) /*-{
		console.error(message);
	}-*/;
	
	public static void d(String tag, String message) {
		log(tag + ": " + message);
	}
	
	public static void i(String tag, String message) {
		log(tag + ": " + message);
	}
	
	public static void e(String tag, String message) {
		error(tag + ": " + message);
	}
}
