package com.tranhoangdai.korengui.client;

import java.util.HashMap;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.VisualNode;
import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;

public class SvgPanelTab extends ScrollPanel implements ZoomNotifier {

	OMSVGSVGElement svgElement = null;
	private Map<String, Node> currentNodes = new HashMap<String, Node>();
	private Map<Integer, NodeLink> currentLinks = new HashMap<Integer, NodeLink>();

	public SvgPanelTab() {
		super();

		svgElement = OMSVGParser.currentDocument().createSVGSVGElement();
		svgElement.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientWidth());
		svgElement.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientHeight());

		this.setWidth(new Integer(Window.getClientWidth()) + "px");
		this.getElement().appendChild(svgElement.getElement());
	}

	public void setNodesAndLinks(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		currentNodes = nodes;
		currentLinks = links;
	}

	public void draw() {
		drawNodes(currentNodes);
		drawLinks(currentLinks);
	}

	private void drawNodes(Map<String, Node> nodes) {
		float radius = this.getOffsetWidth() / 4;
		float center = 0;
		if (this.getOffsetHeight() < this.getOffsetWidth()) {
			center = this.getOffsetHeight() / 2;
		} else {
			center = this.getOffsetWidth() / 2;
		}
		float slice = (float) (2 * Math.PI / nodes.size());

		int counter = 1;
		try {

			for (Node node : nodes.values()) {
				int x = (int) (radius * Math.cos(counter * slice) + center);
				int y = (int) (radius * Math.sin(counter * slice) + center);
				((VisualNode) node).translateTo(x, y);
				++counter;
				svgElement.appendChild(node.getGroupShape());
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void drawLinks(Map<Integer, NodeLink> links) {
		for (NodeLink link : links.values()) {
			link.adjust();
			svgElement.getNode().insertFirst(link.getShape().getNode());
		}
	}

	@Override
	public void zoomIn(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		// TODO Auto-generated method stub

	}

	@Override
	public void zoomOut() {
		// TODO Auto-generated method stub

	}

}
