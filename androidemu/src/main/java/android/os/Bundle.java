package android.os;

import java.util.HashMap;

public class Bundle {

	HashMap<String, String> values = new HashMap<>();

	public void put(String key, String value) {
		values.put(key, value);
	}

	public void putString(String key, String value) {
		values.put(key, value);
	}

	public void putBoolean(String key, boolean value) {
		values.put(key, String.valueOf(value));
	}

	public void putInt(String key, int value) {
		values.put(key, String.valueOf(value));
	}

	public void putLong(String key, long value) {
		values.put(key, String.valueOf(value));
	}

	public String get(String key) {
		return values.get(key);
	}

	public String getString(String key) {
		return values.get(key);
	}

	public String getString(String key, String defaultValue) {
		return values.getOrDefault(key, defaultValue);
	}

	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(values.get(key));
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return values.containsKey(key) ? Boolean.parseBoolean(values.get(key)) : defaultValue;
	}

	public int getInt(String key) {
		return Integer.parseInt(values.get(key));
	}

	public int getInt(String key, int defaultValue) {
		return values.containsKey(key) ? Integer.parseInt(values.get(key)) : defaultValue;
	}

	public long getLong(String key) {
		return Long.parseLong(values.get(key));
	}

	public long getLong(String key, long defaultValue) {
		return values.containsKey(key) ? Long.parseLong(values.get(key)) : defaultValue;
	}

	public boolean containsKey(String key) {
		return values.containsKey(key);
	}
}
