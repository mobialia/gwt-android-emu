package org.json;

import com.google.gwt.json.client.JSONParser;

/**
 * Proxy to the GWT class
 */
public class JSONArray {

	com.google.gwt.json.client.JSONArray jsonArray;

	public JSONArray(String json) throws JSONException {
		try {
			jsonArray = JSONParser.parseStrict(json).isArray();
		} catch (Exception e) {
			throw new JSONException(e);
		}
	}

	public int length() {
		return jsonArray.size();
	}

	public JSONObject getJSONObject(int index) throws JSONException {
		return new JSONObject(jsonArray.get(index).toString());
	}

}
