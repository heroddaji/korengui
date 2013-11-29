package com.tranhoangdai.korengui.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.svg.HostSvg;
import com.tranhoangdai.korengui.client.view.svg.LinkSvg;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;

@SuppressWarnings("unchecked")
public abstract class SvgPanelAbstractDrawTab extends ScrollPanel {
	
	List<NodeSvg> nodesSvg = new ArrayList<NodeSvg>();
	List<LinkSvg> linksSvg = new ArrayList<LinkSvg>();

	protected OMSVGSVGElement svgElement = null;
	protected AbstractPanel parent = null;
	protected float center = 0;

	public SvgPanelAbstractDrawTab(AbstractPanel _parent) {
		parent = _parent;
		this.setWidth("100%");
		this.setHeight(new Integer(Window.getClientHeight()) + "px");

		svgElement = OMSVGParser.currentDocument().createSVGSVGElement();
		svgElement.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientWidth());
		svgElement.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientHeight());
		this.getElement().appendChild(svgElement.getElement());
	}

	protected void createElements(Class type) {		
		if (type.equals(NodeSvg.class)){
			Map<String, Switch> switches = parent.getTopologySwitchModels();			
			for (Switch switchModel : switches.values()) {

				NodeSvg nodeSvg = new NodeSvg(switchModel);
				nodesSvg.add(nodeSvg);
			}
			
		}

		if (type.equals(LinkSvg.class)) {
			Map<Integer, Link> links = parent.getTopologyLinkModels();			
			for (Link linkModel : links.values()) {

				LinkSvg linkSvg = new LinkSvg(linkModel);
				linksSvg.add(linkSvg);
			}
			
		}
		
	}	

	protected void drawNodes() {
		float radius = SvgPanel.INSTANCE.getOffsetWidth() / 4;
		calCenter();

		float slice = (float) (2 * Math.PI / nodesSvg.size());

		int counter = 1;
		try {
			for (NodeSvg nodeSvg : nodesSvg) {
				int x = (int) (radius * Math.cos(counter * slice) + center);
				int y = (int) (radius * Math.sin(counter * slice) + center);
				nodeSvg.translateTo(x, y);
				++counter;
				svgElement.appendChild(nodeSvg);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * This method must be call after all the nodes have been drawn	 *  
	 * otherwise it cannot setup the coordinate of line
	 * also insert line as first element to make them stay behind the nodes
	 */
	protected void drawLinks() {
		createLinksCoordination(linksSvg, nodesSvg);
		for (LinkSvg linkSvg : linksSvg) {
			svgElement.getElement().insertFirst(linkSvg.getElement());
		}
	}
	
	protected void createLinksCoordination(List<LinkSvg> linksSvg, List<NodeSvg> nodesSvg) {
		for (LinkSvg linkSvg : linksSvg) {
			linkSvg.findAndMatchNode(nodesSvg);
		}
	}

	protected void drawhosts(List<HostSvg> hostsSvg) {

	}

	protected void calCenter() {
		if (SvgPanel.INSTANCE.getOffsetHeight() < SvgPanel.INSTANCE.getOffsetWidth()) {
			center = SvgPanel.INSTANCE.getOffsetHeight() / 2;
		} else {
			center = SvgPanel.INSTANCE.getOffsetWidth() / 2;
		}
	}
	
	protected abstract void draw();

}
