package com.tranhoangdai.korengui.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;
import com.tranhoangdai.korengui.client.model.helper.SvgUtility;
import com.tranhoangdai.korengui.client.model.link.NodeLink;
import com.tranhoangdai.korengui.client.model.node.Node;

public class SvgPanelGlobalTopologyTab extends SvgPanelAbstractDrawTab implements TopologyNotifier {

	protected Map<String, Node> currentNodes = new HashMap<String, Node>();
	protected Map<Integer, NodeLink> currentLinks = new HashMap<Integer, NodeLink>();

	public SvgPanelGlobalTopologyTab(TabLayoutPanel parent) {
		super(parent);
		Utility.INSTANCE.addTopologyAble(this);
	}

	public void setNodesAndLinks(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		for (Node node : nodes.values()) {
			Node cloneNode = SvgUtility.cloneNode(node);
			this.currentNodes.put(cloneNode.getDpid(), cloneNode);
		}
		for (NodeLink link : links.values()) {
			NodeLink cloneLink = new NodeLink(link);
			cloneLink.findAndMatchNode(currentNodes);
			cloneLink.adjust();
			this.currentLinks.put(cloneLink.getId(), cloneLink);
		}
	}

	@Override
	public void finishDownload(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		if (currentNodes.size() > 0 && currentLinks.size() > 0) {
			parent.selectTab(this);
		} else {
			setNodesAndLinks(nodes, links);
			draw(currentNodes,currentLinks);
		}

	}
}
