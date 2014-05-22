package android.content;

import com.google.gwt.storage.client.Storage;

public class SharedPreferences {

	Storage storage;

	public SharedPreferences() {
		storage = Storage.getLocalStorageIfSupported();
	}

	public String getString(String key, String defaultValue) {
		if (storage == null) {
			return defaultValue;
		}
		String out = storage.getItem(key);
		if (out == null) {
			return defaultValue;
		}
		return out;
	}

	public int getInt(String key, int defaultValue) {
		if (storage == null) {
			return defaultValue;
		}
		String out = storage.getItem(key);
		if (out == null) {
			return defaultValue;
		}
		return Integer.valueOf(out);
	}

	public long getLong(String key, long defaultValue) {
		if (storage == null) {
			return defaultValue;
		}
		String out = storage.getItem(key);
		if (out == null) {
			return defaultValue;
		}
		return Long.valueOf(out);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		if (storage == null) {
			return defaultValue;
		}
		String out = storage.getItem(key);
		if (out == null) {
			return defaultValue;
		}
		return Boolean.valueOf(out);
	}

	public Editor edit() {
		return new Editor(this);
	}

	public class Editor {

		SharedPreferences prefs;

		public Editor(SharedPreferences prefs) {
			this.prefs = prefs;
		}

		public void putString(String key, String value) {
			if (prefs.storage != null) {
				prefs.storage.setItem(key, value);
			}
		}

		public void putInt(String key, int value) {
			if (prefs.storage != null) {
				prefs.storage.setItem(key, String.valueOf(value));
			}
		}

		public void putLong(String key, long value) {
			if (prefs.storage != null) {
				prefs.storage.setItem(key, String.valueOf(value));
			}
		}

		public void putBoolean(String key, boolean value) {
			if (prefs.storage != null) {
				prefs.storage.setItem(key, String.valueOf(value));
			}
		}

		public void commit() {

		}
	}

}
