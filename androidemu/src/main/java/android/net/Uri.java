package android.net;

public class Uri {

	String data;

	public Uri(String data) {
		this.data = data;
	}

	public static Uri parse(String uri) {
		return new Uri(uri);
	}

	public String toString() {
		return data;
	}
}
