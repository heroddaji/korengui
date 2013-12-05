package com.tranhoangdai.korengui.client.view.tab.svg;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.AbstractPanel;

public class SvgPanelPathFlowTab extends SvgPanelAbstractDrawTab {
	
	protected Map<String, Switch> currentNodes = new HashMap<String, Switch>();
	protected Map<Integer, Link> currentLinks = new HashMap<Integer, Link>();

	public SvgPanelPathFlowTab(AbstractPanel _parent) {
		super(_parent);
	}

	@Override
	protected void draw() {
		// TODO Auto-generated method stub
		
	}

	

}
