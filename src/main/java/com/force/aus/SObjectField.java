package com.force.aus;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Describe information for an sObjectField
 * 
 * TODO - implement ReferenceTo fields
 * 
 * @author tsellers
 * 
 */
public class SObjectField extends RestResponse {

	private List<PicklistValue> picklistValues;

	public SObjectField(String content) throws JSONException {
		super(content);
	}

	@Override
	protected void processResponseContent() {
		try {

			json = new JSONObject(responseContent);

			JSONArray jsonPicklistValues = json.getJSONArray("picklistValues");
			picklistValues = new ArrayList<PicklistValue>();
			for (int i = 0; i < jsonPicklistValues.length(); i++) {
				JSONObject picklistValue = jsonPicklistValues.getJSONObject(i);
				picklistValues.add(new PicklistValue(picklistValue.toString()));
			}

		} catch (JSONException je) {
			je.printStackTrace();
			throw new RuntimeException();
		}

	}

	public List<PicklistValue> getPicklistValues() {
		return picklistValues;
	}

	public void setPicklistValues(List<PicklistValue> picklistValues) {
		this.picklistValues = picklistValues;
	}

}
