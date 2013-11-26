package com.tranhoangdai.korengui.client.view;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Node;

public class SvgPanelPathFlowTab extends SvgPanelAbstractDrawTab {
	
	protected Map<String, Node> currentNodes = new HashMap<String, Node>();
	protected Map<Integer, Link> currentLinks = new HashMap<Integer, Link>();

	public SvgPanelPathFlowTab(TabLayoutPanel parent) {
		super(parent);
		//Utility.INSTANCE.addPathFlowAble(this);
	}

	

}
