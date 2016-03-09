package android.org.json;

import com.google.gwt.json.client.JSONParser;

/**
 * Proxy to the GWT class
 */
public class JSONObject {

	com.google.gwt.json.client.JSONObject jsonObject;

	public JSONObject(String json) {
		jsonObject = JSONParser.parseStrict(json).isObject();
	}

	public String getString(String name) {
		return jsonObject.get(name).toString();
	}

	public int getInt(String name) {
		return Integer.valueOf(jsonObject.get(name).toString());
	}
}
