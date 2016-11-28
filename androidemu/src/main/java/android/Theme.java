package android;

/**
 * Non-Android Theme class
 */
public class Theme {
	public static String colorForeground = "#ffffff";
	public static String colorBackground = "rgba(50, 50, 50, 1)";
	public static String colorPrimary = "rgba(40, 40, 40, 1)";
	public static String colorAccent = "#33b5e5";
	public static String colorControlHighlight75 = "rgba(50, 165, 207, 0.75)";
	public static String colorControlHighlight50 = "rgba(50, 165, 207, 0.5)";

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

	public static String getColorControlHighlight75() {
		return colorControlHighlight75;
	}

	public static void setColorControlHighlight75(String colorControlHighlight75) {
		Theme.colorControlHighlight75 = colorControlHighlight75;
	}

	public static String getColorControlHighlight50() {
		return colorControlHighlight50;
	}

	public static void setColorControlHighlight50(String colorControlHighlight50) {
		Theme.colorControlHighlight50 = colorControlHighlight50;
	}
}
