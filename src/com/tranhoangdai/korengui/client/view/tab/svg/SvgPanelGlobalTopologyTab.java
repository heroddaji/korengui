package com.tranhoangdai.korengui.client.view.tab.svg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.AbstractPanel;
import com.tranhoangdai.korengui.client.view.svg.LinkSvg;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;

@SuppressWarnings("unchecked")
public class SvgPanelGlobalTopologyTab extends SvgPanelAbstractDrawTab {

	public SvgPanelGlobalTopologyTab(AbstractPanel _parent) {
		super(_parent);
	}

	@Override
	public void draw() {
		NodeSvg nodeSvgClass = new NodeSvg(null);
		LinkSvg linkSvgClass = new LinkSvg(null);

		List<NodeSvg> nodeSvgs =  createSvgElements(nodeSvgClass, globalSwitchModels.values());
		List<LinkSvg> linkSvgs =  createSvgElements(linkSvgClass, globalLinkModels.values());

		drawNodeElementsSvg(nodeSvgs);
		drawLinkSvgs(linkSvgs,nodeSvgs);
	}



}
