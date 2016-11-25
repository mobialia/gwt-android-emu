package android;

/**
 * Non-Android Theme class
 */
public class Theme {
	public static String colorForeground = "#ffffff";
	public static String colorBackground = "rgba(50, 50, 50, 1)";
	public static String colorPrimary = "rgba(40, 40, 40, 1)";
	public static String colorAccent = "#33b5e5";
	public static String colorControlHighlight = "rgba(50, 165, 207, 0.5)";

	public static String getColorForeground() {
		return colorForeground;
	}

	public static void setColorForeground(String colorForeground) {
		Theme.colorForeground = colorForeground;
	}

	public static String getColorBackground() {
		return colorBackground;
	}

	public static void setColorBackground(String colorBackground) {
		Theme.colorBackground = colorBackground;
	}

	public static String getColorPrimary() {
		return colorPrimary;
	}

	public static void setColorPrimary(String colorPrimary) {
		Theme.colorPrimary = colorPrimary;
	}

	public static String getColorAccent() {
		return colorAccent;
	}

	public static void setColorAccent(String colorAccent) {
		Theme.colorAccent = colorAccent;
	}

	public static String getColorControlHighlight() {
		return colorControlHighlight;
	}

	public static void setColorControlHighlight(String colorControlHighlight) {
		Theme.colorControlHighlight = colorControlHighlight;
	}
}
