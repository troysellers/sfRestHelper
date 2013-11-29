package com.force.aus;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Returned as a list from either a query result or from a describe.
 * If from a describe call it will contain a URLs attribute 
 * 
 * @author tsellers
 *
 */
public class SObject extends RestResponse {


	private JSONObject urls;	
	
	public SObject() {}
	
	public SObject(String content) throws JSONException{
		super(content);
	}
	
	@Override
	protected void processResponseContent() {
		
		try {
			json = new JSONObject(responseContent);
			if(json.has("urls"))
				urls = json.getJSONObject("urls");
		} catch (JSONException je) {
			je.printStackTrace();
			throw new RuntimeException();
		}
	}
	/**
	 * Will return the JSONObject representation of data or metadata.
	 * 
	 * @return
	 */
	public JSONObject getJSONObject() {
		return json;
	}
	/**
	 * Will need to be tested for null.
	 * 
	 * @return
	 */
	public JSONObject getURLS() {
		return urls;
	}
}
