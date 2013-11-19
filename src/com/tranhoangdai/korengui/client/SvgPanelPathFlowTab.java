package com.tranhoangdai.korengui.client;

import java.util.Map;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;

public class SvgPanelPathFlowTab extends SvgPanelGeneralDrawTab implements PathFlowNotifier{

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
		for(NodeLink link: paths.values()){
			link.findAndMatchNode(currentNodes);
		}

		draw();
	}

}
