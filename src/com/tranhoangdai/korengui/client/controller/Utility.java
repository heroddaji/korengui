package com.tranhoangdai.korengui.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tranhoangdai.korengui.client.controller.Utility.ActionState;
import com.tranhoangdai.korengui.client.interf.GuiEventNotifier;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.TopologyService;
import com.tranhoangdai.korengui.client.service.TopologyServiceAsync;

/**
 * This class handles interaction between nodes model and GUI
 */
@Deprecated
public class Utility {

	public enum ActionState {
		NOTHING, GLOBAL, ZOOM, FLOW
	}

	public static Utility INSTANCE = GWT.create(Utility.class);
	public ActionState state = ActionState.NOTHING;
	public List<Switch> zoomStack = new ArrayList<Switch>();
	public Map<String, Switch> globalNodes = new HashMap<String, Switch>();
	public Map<Integer, Link> globalLinks = new HashMap<Integer, Link>();

	public List<TopologyNotifier> topologyNotifiers = new ArrayList<TopologyNotifier>();
	public List<PathFlowNotifier> pathFlowNotifiers = new ArrayList<PathFlowNotifier>();
	public List<GuiEventNotifier> guiEventNotifiers = new ArrayList<GuiEventNotifier>();
	public List<ZoomNotifier> zoomNotifiers = new ArrayList<ZoomNotifier>();

	public Switch pathFlowNode1 = null;
	public Switch pathFlowNode2 = null;

	public void setPathFlowConnection(Switch node) {

		if (state != ActionState.FLOW) {
			return;
		}

		if (pathFlowNode1 == null) {
			pathFlowNode1 = node;
			notifyGuiEvent(ActionState.FLOW, node);
			notifyAddPathFlowStartNode();
		} else {
			pathFlowNode2 = node;
		}

		if (pathFlowNode1.equals(pathFlowNode2)) {
			Window.alert("Error: please choose different nodes!");
			clearPathFlow();
		}

		// add endnode if everything is checked
		if (pathFlowNode1 != null && pathFlowNode2 != null) {
			notifyAddPathFlowEndNode();
			pathFlowNode1 = null;
			pathFlowNode2 = null;
		}

	}

	public void clearPathFlow() {
		state = ActionState.NOTHING;
		pathFlowNode1 = null;
		pathFlowNode2 = null;
	}

	public void notifyGuiEvent(ActionState state, Object data) {
		if (state == ActionState.GLOBAL) {
			for (GuiEventNotifier tn : guiEventNotifiers) {
				tn.eventGlobalTopology();
			}
		}

		if (state == ActionState.FLOW) {
			for (GuiEventNotifier tn : guiEventNotifiers) {
				tn.eventGetPathFlow((Switch) data);
			}
		}

		if (state == ActionState.ZOOM) {
			for (GuiEventNotifier tn : guiEventNotifiers) {
				Switch node = (Switch) data;

			}
		}
	}

	public void notifyFinishDownloadGlobalTopology() {
		notifyGuiEvent(ActionState.GLOBAL, null);

		for (TopologyNotifier tn : topologyNotifiers) {
			tn.finishDownload(globalNodes, globalLinks);
		}
	}

	public void notifyAddPathFlowStartNode() {
		for (PathFlowNotifier pn : pathFlowNotifiers) {
			pn.addStartNode(pathFlowNode1);
		}
	}

	public void notifyAddPathFlowEndNode() {
		for (PathFlowNotifier pn : pathFlowNotifiers) {
			pn.addEndNode(pathFlowNode2);
		}
	}

	public void notifyClearFlow() {
		for (PathFlowNotifier pn : pathFlowNotifiers) {
			pn.emptyNodes();
		}
	}

	

	public void notifyFinishDownloadPathFlow(Map<Integer, Link> paths) {
		for (PathFlowNotifier pn : pathFlowNotifiers) {
			pn.pathIsSetup(paths);
		}

		pathFlowNode1 = null;
		pathFlowNode2 = null;
		state = ActionState.NOTHING;
	}

	public void downloadPathFlow(String nodeId1, String nodeId2) {
		TopologyServiceAsync topo = GWT.create(TopologyService.class);

		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				JSONValue value = JSONParser.parseStrict(result);
				JSONArray array = value.isArray();
				Map<Integer, Link> paths = new HashMap<Integer, Link>();
				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject obj = array.get(i).isObject();
						String srcIp = obj.get("src-switch").isString().stringValue();
						int srcport = (int) obj.get("src-port").isNumber().doubleValue();
						String dstIp = obj.get("dst-switch").isString().stringValue();
						int dstport = (int) obj.get("dst-port").isNumber().doubleValue();
						Link link = new Link(srcIp, srcport, dstIp, dstport);
						paths.put(link.getId(), link);
					}

					notifyFinishDownloadPathFlow(paths);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("failed to load links");

			}
		};

		topo.getPathFlow(nodeId1, nodeId2, callback);
	}

	public void notifyGuiWantToZoomToNode(Switch zoomNode) {

		if (getState() == ActionState.ZOOM) {
			zoomStack.add(zoomNode);

			notifyGuiEvent(ActionState.ZOOM, zoomNode);

			
		}
	}

	public ActionState getState() {
		return state;
	}

	public void setZoomStack(List<Switch> zoomStack) {
		this.zoomStack = zoomStack;
	}

	public Map<String, Switch> getGlobalNodes() {
		return globalNodes;
	}

	public void setGlobalNodes(Map<String, Switch> activeNodes) {
		globalNodes = activeNodes;
	}

	public Map<Integer, Link> getGlobalLinks() {
		return globalLinks;
	}

	public void setGlobalinks(Map<Integer, Link> activeLinks) {
		globalLinks = activeLinks;
	}

	public void addTopologyAble(TopologyNotifier topologyAble) {
		topologyNotifiers.add(topologyAble);
	}

	public void addPathFlowAble(PathFlowNotifier pathFlowAble) {
		pathFlowNotifiers.add(pathFlowAble);
	}

	public void addZoomAble(ZoomNotifier zoomAble) {
		zoomNotifiers.add(zoomAble);
	}

	public void addGuiEventAble(GuiEventNotifier guiEventAble) {
		guiEventNotifiers.add(guiEventAble);
	}

	public void setState(ActionState state) {
		
		
	}

}
