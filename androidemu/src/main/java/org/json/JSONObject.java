package org.json;

import com.google.gwt.json.client.JSONParser;

/**
 * Proxy to the GWT class
 */
public class JSONObject {

	com.google.gwt.json.client.JSONObject jsonObject;

	public JSONObject(String json) throws JSONException {
		try {
			jsonObject = JSONParser.parseStrict(json).isObject();
		} catch (Exception e) {
			throw new JSONException(e);
		}
	}

	public String getString(String name) {
		return jsonObject.get(name).toString().replaceAll("^\"|\"$", "");
	}

	public int getInt(String name) {
		return Integer.valueOf(jsonObject.get(name).toString().replaceAll("^\"|\"$", ""));
	}

	public long getLong(String name) {
		return Long.valueOf(jsonObject.get(name).toString().replaceAll("^\"|\"$", ""));
	}
}
