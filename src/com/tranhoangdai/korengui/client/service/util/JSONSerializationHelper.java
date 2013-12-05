package com.tranhoangdai.korengui.client.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.bcel.generic.GETSTATIC;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.tranhoangdai.korengui.client.model.AttachmentPoint;
import com.tranhoangdai.korengui.client.model.Attributes;
import com.tranhoangdai.korengui.client.model.Description;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Port;
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
		Map<String, Host> hosts = new HashMap<String, Host>();
		JSONValue value = JSONParser.parseStrict(jsonValue);
		JSONArray array = value.isArray();
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject jobj = array.get(i).isObject();
				Host host = createHost(jobj);
				hosts.put(host.getMac().get(0), host);
			}
		}

		return hosts;
	}

	public Map<Integer, Link> createLinks(String jsonValue) {
		Map<Integer, Link> links = new HashMap<Integer, Link>();
		JSONValue value = JSONParser.parseStrict(jsonValue);
		JSONArray array = value.isArray();
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.get(i).isObject();
				Link link = createLink(obj);
				links.put(link.getIntId(), link);
			}
		}
		return links;
	}

	private Switch createSwitch(JSONObject jobj) {
		System.out.println(jobj);
		Switch newSwitch = new Switch();

		String dpid = getJSONStringValue(jobj, "dpid");
		String harole = getJSONStringValue(jobj, "harole");
		double connectedSince = getJSONNumberValue(jobj, "connectionSince");
		String inetAddress = getJSONStringValue(jobj, "inetAddress");
		double buffers = getJSONNumberValue(jobj, "buffers");
		double actions = getJSONNumberValue(jobj, "actions");
		double capabilities = getJSONNumberValue(jobj, "capabilities");
		Attributes attributes = createAttributes(jobj.get("attributes"));
		Description desc = createDescription(jobj.get("description"));
		List<Port> ports = createPorts(jobj.get("ports").isArray());

		newSwitch.setDpid(dpid);
		newSwitch.setHarole(harole);
		newSwitch.setInetAddress(inetAddress);
		newSwitch.setAction(actions);
		newSwitch.setBuffers(buffers);
		newSwitch.setCapabilities(capabilities);
		newSwitch.setConnectedSince(connectedSince);
		newSwitch.setAttributes(attributes);
		newSwitch.setDescription(desc);
		newSwitch.setPorts(ports);

		return newSwitch;

	}

	private Host createHost(JSONObject jobj) {
		Host host = new Host();

		String entityClass = getJSONStringValue(jobj, "entityClass");
		double lastSeen = getJSONNumberValue(jobj, "lastSeen");
		host.setEntityClass(entityClass);
		host.setLastSeen(lastSeen);

		// host mac
		List<String> values = new ArrayList<String>();
		JSONArray macarray = jobj.get("mac").isArray();
		if (macarray != null) {
			for (int i = 0; i < macarray.size(); i++) {
				String mac = macarray.get(i).isString().stringValue();
				values.add(mac);
			}
			host.setMac(values);
		}

		// host ipv4
		values = new ArrayList<String>();
		JSONArray ipv4array = jobj.get("ipv4").isArray();
		if (ipv4array != null) {
			for (int i = 0; i < ipv4array.size(); i++) {
				String ipv4 = ipv4array.get(i).isString().stringValue();
				values.add(ipv4);
			}
			host.setIpv4(values);
		}

		// host vlan
		values = new ArrayList<String>();
		JSONArray vlanarray = jobj.get("vlan").isArray();
		if (vlanarray != null) {
			for (int i = 0; i < vlanarray.size(); i++) {
				String vlan = vlanarray.get(i).isString().stringValue();
				values.add(vlan);
			}
			host.setVlan(values);
		}

		List<AttachmentPoint> attachmentPoints = createAttachmentPoints(jobj.get("attachmentPoint").isArray());
		host.setAttachmentPoints(attachmentPoints);
		return host;

	}

	private Link createLink(JSONObject jobj) {
		String srcSwitch = getJSONStringValue(jobj, "src-switch");
		int srcPort = (int)getJSONNumberValue(jobj, "src-port");
		String dstSwitch =  getJSONStringValue(jobj, "dst-switch");
		int dstPort = (int) getJSONNumberValue(jobj, "dst-port");
		String type =  getJSONStringValue(jobj, "type");
		String direction = getJSONStringValue(jobj, "direction");

		Link link = new Link(srcSwitch, srcPort, dstSwitch, dstPort);
		link.setType(type);
		link.setDirection(direction);
		return link;
	}

	private Attributes createAttributes(JSONValue value) {
		Attributes attributes = new Attributes(); 
		if(value == null){
			return attributes;
		}
		JSONObject jobj = value.isObject();		
		attributes.setFastWildcards(getJSONNumberValue(jobj, "FastWildcards") );
		attributes.setSupportsNxRole(getJSONBooleanValue(jobj, "supportNxRole"));
		attributes.setSupportsOfppFlood(getJSONBooleanValue(jobj, "supportsOfppFlood"));
		attributes.setSupportsOfppTable(getJSONBooleanValue(jobj, "supportsOfppTable"));
		return attributes;
	}

	private Description createDescription(JSONValue value) {
		Description desc = new Description();
		if(value == null){
			return desc;
		}
		
		JSONObject jobj = value.isObject();
		
		desc.setDatapath(getJSONStringValue(jobj, "datapath"));
		desc.setHardware(getJSONStringValue(jobj, "hardware"));
		desc.setManufacturer(getJSONStringValue(jobj, "manufacturer"));
		desc.setSerialNum(getJSONStringValue(jobj, "serialNum"));
		desc.setSoftware(getJSONStringValue(jobj, "software"));
		return desc;
	}

	private List<Port> createPorts(JSONArray array) {
		List<Port> ports = new ArrayList<Port>();
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject jobj = array.get(i).isObject();
				Port port = new Port();
				port.setPortNumber(getJSONNumberValue(jobj, "portNumber"));
				port.setHardwareAddress(getJSONStringValue(jobj, "hardwareAddress"));
				port.setName(getJSONStringValue(jobj, "name"));
				port.setConfig(getJSONNumberValue(jobj, "config"));
				port.setState(getJSONNumberValue(jobj, "state"));
				port.setCurrentFeatures(getJSONNumberValue(jobj, "currentFeatures"));
				port.setAdvertisedFeatures(getJSONNumberValue(jobj, "advertisedFeatures"));
				port.setSupportedFeatures(getJSONNumberValue(jobj, "supportedFeatures"));
				port.setPeerFeatures(getJSONNumberValue(jobj, "peerFeatures"));
				ports.add(port);
			}
		}
		return ports;
	}

	private List<AttachmentPoint> createAttachmentPoints(JSONArray array) {
		List<AttachmentPoint> points = new ArrayList<AttachmentPoint>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jobj = array.get(i).isObject();
			AttachmentPoint point = new AttachmentPoint();
			//point.setErrorStatus(obj.get("errorStatus").isString().stringValue());
			point.setPort((int)getJSONNumberValue(jobj, "port"));
			point.setSwitchDPID(getJSONStringValue(jobj, "switchDPID"));
			points.add(point);
		}
		return points;
	}
	
	private String getJSONStringValue(JSONObject jobj, String key){
		String value = "";
		if(jobj.get(key) == null){
			return value;
		}
		
		if(jobj.get(key).isString() != null){
			value = jobj.get(key).isString().stringValue();
		}
		return value;
	}
	
	private double getJSONNumberValue(JSONObject jobj, String key){
		double value = 0.0;
		
		if(jobj.get(key) == null){
			return value;
		}
		if(jobj.get(key).isString() != null){
			value = jobj.get(key).isNumber().doubleValue();
		}
		return value;
	}
	private boolean getJSONBooleanValue(JSONObject jobj, String key){
		boolean value = true;
		
		if(jobj.get(key) == null){
			return value;
		}
		
		if(jobj.get(key).isString() != null){
			value = jobj.get(key).isBoolean().booleanValue();
		}
		return value;
	}
}
