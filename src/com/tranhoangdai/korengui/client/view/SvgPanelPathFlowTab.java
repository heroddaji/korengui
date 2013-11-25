package com.tranhoangdai.korengui.client.view;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.helper.SvgUtility;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;
import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.model.Link;

public class SvgPanelPathFlowTab extends SvgPanelAbstractDrawTab implements PathFlowNotifier{
	
	protected Map<String, Node> currentNodes = new HashMap<String, Node>();
	protected Map<Integer, Link> currentLinks = new HashMap<Integer, Link>();

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
	public void pathIsSetup(Map<Integer,Link> paths) {
		//clone the global nodes		
		setNodesAndLinks(Utility.INSTANCE.getGlobalNodes(), paths);
		
		//readjust link
		for(Link link: currentLinks.values()){
			link.findAndMatchNode(currentNodes);
		}

		draw(currentNodes,currentLinks);
	}
	public void setNodesAndLinks(Map<String, Node> nodes, Map<Integer, Link> links) {
		for (Node node : nodes.values()) {
			Node cloneNode = SvgUtility.cloneNode(node);
			this.currentNodes.put(cloneNode.getDpid(), cloneNode);
		}
		for (Link link : links.values()) {
			Link cloneLink = new Link(link);
			cloneLink.findAndMatchNode(currentNodes);
			cloneLink.adjust();
			this.currentLinks.put(cloneLink.getId(), cloneLink);
		}
	}

}
