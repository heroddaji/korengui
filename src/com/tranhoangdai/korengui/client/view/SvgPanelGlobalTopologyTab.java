package com.tranhoangdai.korengui.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.svg.LinkSvg;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;

@SuppressWarnings("unchecked")
public class SvgPanelGlobalTopologyTab extends SvgPanelAbstractDrawTab {

	Map<String,Switch> switchModels = new HashMap<String, Switch>();
	Map<Integer,Link> linkModels = new HashMap<Integer, Link>();

	public SvgPanelGlobalTopologyTab(AbstractPanel _parent) {
		super(_parent);
	}

	@Override
	public void draw() {
		NodeSvg nodeSvgClass = new NodeSvg(null);
		LinkSvg linkSvgClass = new LinkSvg(null);
		
		List<NodeSvg> nodeSvgs =  createSvgElements(nodeSvgClass, switchModels.values());		
		List<LinkSvg> linkSvgs =  createSvgElements(linkSvgClass, linkModels.values());
		
		drawNodeElementsSvg(nodeSvgs);
		drawLinks(linkSvgs,nodeSvgs);
	}

	public Map<String, Switch> getSwitchModels() {
		return switchModels;
	}

	public void setSwitchModels(Map<String, Switch> switchModels) {
		this.switchModels = switchModels;
	}

	public Map<Integer, Link> getLinkModels() {
		return linkModels;
	}

	public void setLinkModels(Map<Integer, Link> linkModels) {
		this.linkModels = linkModels;
	}	

}
