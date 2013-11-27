package com.tranhoangdai.korengui.client.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				links.put(link.getId(), link);
			}
		}
		return links;
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

		String entityClass = jobj.get("entityClass").isString().toString();
		double lastSeen = jobj.get("lastSeen").isNumber().doubleValue();
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

	private Link createLink(JSONObject obj) {
		String srcSwitch = obj.get("src-switch").isString().stringValue();
		int srcPort = (int) obj.get("src-port").isNumber().doubleValue();
		String dstSwitch = obj.get("dst-switch").isString().stringValue();
		int dstPort = (int) obj.get("dst-port").isNumber().doubleValue();
		String type = obj.get("type").isString().stringValue();
		String direction = obj.get("direction").isString().stringValue();

		Link link = new Link(srcSwitch, srcPort, dstSwitch, dstPort);
		link.setType(type);
		link.setDirection(direction);
		return link;
	}

	private Attributes createAttributes(JSONObject jobj) {
		Attributes attributes = new Attributes();
		attributes.setFastWildcards(jobj.get("FastWildcards").isNumber().doubleValue());
		attributes.setSupportsNxRole(jobj.get("supportsNxRole").isBoolean().booleanValue());
		attributes.setSupportsOfppFlood(jobj.get("supportsOfppFlood").isBoolean().booleanValue());
		attributes.setSupportsOfppTable(jobj.get("supportsOfppTable").isBoolean().booleanValue());
		return attributes;
	}

	private Description createDescription(JSONObject jobj) {
		Description desc = new Description();
		desc.setDatapath(jobj.get("datapath").isString().stringValue());
		desc.setHardware(jobj.get("hardware").isString().stringValue());
		desc.setManufacturer(jobj.get("manufacturer").isString().stringValue());
		desc.setSerialNum(jobj.get("serialNum").isString().stringValue());
		desc.setSoftware(jobj.get("software").isString().stringValue());
		return desc;
	}

	private List<Port> createPorts(JSONArray array) {
		List<Port> ports = new ArrayList<Port>();
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.get(i).isObject();
				Port port = new Port();
				port.setPortNumber(obj.get("portNumber").isNumber().doubleValue());
				port.setHardwareAddress(obj.get("hardwareAddress").isString().stringValue());
				port.setName(obj.get("name").isString().stringValue());
				port.setConfig(obj.get("config").isNumber().doubleValue());
				port.setState(obj.get("state").isNumber().doubleValue());
				port.setCurrentFeatures(obj.get("currentFeatures").isNumber().doubleValue());
				port.setAdvertisedFeatures(obj.get("advertisedFeatures").isNumber().doubleValue());
				port.setSupportedFeatures(obj.get("supportedFeatures").isNumber().doubleValue());
				port.setPeerFeatures(obj.get("peerFeatures").isNumber().doubleValue());
				ports.add(port);
			}
		}
		return ports;
	}

	private List<AttachmentPoint> createAttachmentPoints(JSONArray array) {
		List<AttachmentPoint> points = new ArrayList<AttachmentPoint>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.get(i).isObject();
			AttachmentPoint point = new AttachmentPoint();
			//point.setErrorStatus(obj.get("errorStatus").isString().stringValue());
			point.setPort((int) obj.get("port").isNumber().doubleValue());
			point.setSwitchDPID(obj.get("switchDPID").isString().stringValue());
			points.add(point);
		}
		return points;
	}

}
