package com.tranhoangdai.korengui.client.service.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.tranhoangdai.korengui.client.model.Attributes;
import com.tranhoangdai.korengui.client.model.Description;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;

/**
 * 
 * @author dai-network-lab
 * 
 *         This class creates node and link from JSon data
 */
public class JSONSerializationHelper {

	public static JSONSerializationHelper INSTANCE = GWT.create(JSONSerializationHelper.class);

	private JSONSerializationHelper() {
	}

	public Map<String, Switch> createSwitches(String jsonValue) {
		Map<String, Switch> switches = new HashMap<String, Switch>();
		JSONValue value = JSONParser.parseStrict(jsonValue);
		JSONArray array = value.isArray();
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject jobj = array.get(i).isObject();
				Switch newSwitch = createSwitch(jobj);
				switches.put(newSwitch.getDpid(), newSwitch);
			}
		}
		return switches;
	}

	public Map<String, Host> createHosts(String jsonValue) {
		JSONValue value = JSONParser.parseStrict(jsonValue);
		return null;
	}

	public Map<Integer, Link> createLinks(String jsonValue) {
		JSONValue value = JSONParser.parseStrict(jsonValue);
		JSONArray array = value.isArray();
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.get(i).isObject();
				String srcIp = obj.get("src-switch").isString().stringValue();
				int srcport = (int) obj.get("src-port").isNumber().doubleValue();
				String dstIp = obj.get("dst-switch").isString().stringValue();
				int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
				Link link = new Link(srcIp, srcport, dstIp, dstport);

			}
		}
		return null;
	}

	private Switch createSwitch(JSONObject jobj) {
		System.out.println(jobj);
		Switch newSwitch = new Switch();
		
		String dpid = jobj.get("dpid").isString().stringValue();
		String harole = jobj.get("harole").isString().stringValue();
		double connectedSince = jobj.get("connectedSince").isNumber().doubleValue();
		String inetAddress = jobj.get("inetAddress").isString().stringValue();
		double buffers = jobj.get("buffers").isNumber().doubleValue();
		double actions = jobj.get("actions").isNumber().doubleValue();
		double capabilities = jobj.get("capabilities").isNumber().doubleValue();
		Attributes attributes = createAttributes(jobj.get("attributes").isObject());
		Description desc = createDescription(jobj.get("description").isObject());
		
		newSwitch.setDpid(dpid);
		newSwitch.setHarole(harole);
		newSwitch.setInetAddress(inetAddress);
		newSwitch.setAction(actions);
		newSwitch.setBuffers(buffers);
		newSwitch.setCapabilities(capabilities);
		newSwitch.setConnectedSince(connectedSince);
		newSwitch.setAttributes(attributes);
		newSwitch.setDescription(desc);		
		
		return newSwitch;

	}

	private Switch createHost(JSONObject jobj) {
		return null;

	}

	private Switch createLink(JSONObject jobj) {
		return null;

	}
	
	private Attributes createAttributes(JSONObject jobj){
		Attributes attributes = new Attributes();
		attributes.setFastWildcards(jobj.get("FastWildcards").isNumber().doubleValue());
		attributes.setSupportsNxRole(jobj.get("supportsNxRole").isBoolean().booleanValue());
		attributes.setSupportsOfppFlood(jobj.get("supportsOfppFlood").isBoolean().booleanValue());
		attributes.setSupportsOfppTable(jobj.get("supportsOfppTable").isBoolean().booleanValue());
		return attributes;
	}
	private Description createDescription(JSONObject jobj){
		Description desc = new Description();
		desc.setDatapath(jobj.get("datapath").isString().stringValue());
		desc.setHardware(jobj.get("hardware").isString().stringValue());
		desc.setManufacturer(jobj.get("manufacturer").isString().stringValue());
		desc.setSerialNum(jobj.get("serialNum").isString().stringValue());
		desc.setSoftware(jobj.get("software").isString().stringValue());
		return desc;
	}

}
