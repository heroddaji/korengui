package com.tranhoangdai.korengui.client.view;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;

public class SvgPanelGlobalTopologyTab extends SvgPanelAbstractDrawTab {

	protected Map<String, Switch> currentNodes = new HashMap<String, Switch>();
	protected Map<Integer, Link> currentLinks = new HashMap<Integer, Link>();

	public SvgPanelGlobalTopologyTab(TabLayoutPanel parent) {
		super(parent);
		//Utility.INSTANCE.addTopologyAble(this);
	}

//	public void setNodesAndLinks(Map<String, Node> nodes, Map<Integer, Link> links) {
//		for (Node node : nodes.values()) {
//			Node cloneNode = SvgUtility.cloneNode(node);
//			this.currentNodes.put(cloneNode.getDpid(), cloneNode);
//		}
//		for (Link link : links.values()) {
//			Link cloneLink = new Link(link);
//			cloneLink.findAndMatchNode(currentNodes);
//			cloneLink.adjust();
//			this.currentLinks.put(cloneLink.getId(), cloneLink);
//		}
//	}
//
//	@Override
//	public void finishDownload(Map<String, Node> nodes, Map<Integer, Link> links) {
//		if (currentNodes.size() > 0 && currentLinks.size() > 0) {
//			parent.selectTab(this);
//		} else {
//			setNodesAndLinks(nodes, links);
//			draw(currentNodes,currentLinks);
//		}
//
//	}
}
