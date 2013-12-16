package com.tranhoangdai.korengui.client.view.tab.svg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.AbstractPanel;
import com.tranhoangdai.korengui.client.view.svg.LinkSvg;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;

public class SvgPanelPathFlowTab extends SvgPanelAbstractDrawTab {

	protected Map<Integer, Link> pathModel = new HashMap<Integer, Link>();
	List<NodeSvg> nodeSvgs = null;
	List<LinkSvg> linkSvgs = null;
	List<LinkSvg> pathSvgs = null;

	public SvgPanelPathFlowTab(AbstractPanel _parent) {
		super(_parent);
	}

	@Override
	public void draw() {
		//draw whole topology first
		NodeSvg nodeSvgClass = new NodeSvg(null);
		LinkSvg linkSvgClass = new LinkSvg(null);

		nodeSvgs = createSvgElements(nodeSvgClass, globalSwitchModels.values());
		linkSvgs = createSvgElements(linkSvgClass, globalLinkModels.values());
		pathSvgs = createSvgPathElements();

		drawNodeElementsSvg(nodeSvgs);
		drawLinkSvgs(linkSvgs, nodeSvgs);
		drawPathSvg();
	}

	private List<LinkSvg> createSvgPathElements() {
		List<LinkSvg> thePathSvgs = new ArrayList<LinkSvg>();
		for (Link linkModel : pathModel.values()) {
			LinkSvg linkSvg= new LinkSvg(linkModel);
			linkSvg.setAttribute("stroke", "red");
			linkSvg.setAttribute("stroke-width", "3");			
			thePathSvgs.add(linkSvg);
		}
		return thePathSvgs;
	}

	private void drawPathSvg() {
		for (LinkSvg linkSvg : pathSvgs) {
			linkSvg.findAndMatchMultipleNodes(nodeSvgs);
			svgElement.getElement().insertFirst(linkSvg.getElement());
		}
	}

	public void setPathModel(Map<Integer, Link> _path) {
		//fake, use only first path
		for (Link link : _path.values()) {
			pathModel.put(link.getIntId(), link);
			break;
		}
	}

}
