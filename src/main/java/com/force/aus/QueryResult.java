package com.force.aus;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * returned as a result of a REST API query.
 * 
 * @author tsellers
 *
 */
public class QueryResult extends RestResponse {

	private JSONObject json;
	private List<SObject> records;
	private int totalSize;
	private boolean done;
	
	
	@Override
	public void processResponseContent() throws JSONException {

		json = new JSONObject(responseContent);	
		JSONArray array = json.getJSONArray("records");
		totalSize = json.getInt("totalSize");
		done = json.getBoolean("done");
	
		records = new ArrayList<SObject>();
		for(int i=0 ; i<array.length() ; i++) {
			records.add(new SObject(array.getJSONObject(i).toString()));
		}
	}

	public int getTotalSize() {
		return totalSize;
	}
	public boolean isDone() {
		return done;
	}
	public List<SObject> getRecords() {
		return records;
	}
	
	public String getString(String key) throws JSONException {
		return json.getString(key);
	}
	
}
