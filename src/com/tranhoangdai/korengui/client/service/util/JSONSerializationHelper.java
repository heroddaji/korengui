package com.tranhoangdai.korengui.client.service.util;

import com.google.gwt.json.client.JSONObject;
import com.tranhoangdai.korengui.client.model.Switch;

/**
 * 
 * @author dai-network-lab
 * 
 *         This class creates node and link from JSon data
 */
public class JSONSerializationHelper {

	public void createNode(JSONObject jobj) {
		Switch activeNode = null;
		Switch tempNode = null;

		String nodeId = jobj.get("dpid").isString().stringValue();
		
	}

}
