package com.tranhoangdai.korengui.client;

import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emptyNodes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pathIsSetup(List<NodeLink> paths) {
		// TODO Auto-generated method stub
		
	}

}
