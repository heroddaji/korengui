package com.tranhoangdai.korengui.client.view;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.helper.SvgUtility;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;
import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.model.NodeLink;

public class SvgPanelPathFlowTab extends SvgPanelAbstractDrawTab implements PathFlowNotifier{
	
	protected Map<String, Node> currentNodes = new HashMap<String, Node>();
	protected Map<Integer, NodeLink> currentLinks = new HashMap<Integer, NodeLink>();

	public SvgPanelPathFlowTab(TabLayoutPanel parent) {
		super(parent);
		Utility.INSTANCE.addPathFlowAble(this);
	}

	@Override
	public void addStartNode(Node startNode) {				
	}

	@Override
	public void addEndNode(Node endNode) {
	}

	@Override
	public void emptyNodes() {
	}

	@Override
	public void pathIsSetup(Map<Integer,NodeLink> paths) {
		//clone the global nodes		
		setNodesAndLinks(Utility.INSTANCE.getGlobalNodes(), paths);
		
		//readjust link
		for(NodeLink link: currentLinks.values()){
			link.findAndMatchNode(currentNodes);
		}

		draw(currentNodes,currentLinks);
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

}
