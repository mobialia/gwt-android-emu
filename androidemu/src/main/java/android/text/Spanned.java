package android.text;

public class Spanned implements CharSequence {

	String value;

	public Spanned(String source) {
		value = source;
	}

	public int length() {
		return value.length();
	}

	public char charAt(int index) {
		return value.charAt(index);
	}

	public CharSequence subSequence(int start, int end) {
		return value.subSequence(start, end);
	}

	public String toString() {
		return value;
	}
}
