package com.tranhoangdai.korengui.client;

import java.util.HashMap;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.VisualNode;
import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;

public class SvgPanelGeneralDrawTab extends ScrollPanel implements TopologyNotifier {

	protected OMSVGSVGElement svgElement = null;

	protected Map<String, Node> currentNodes = new HashMap<String, Node>();
	protected Map<Integer, NodeLink> currentLinks = new HashMap<Integer, NodeLink>();
	protected TabLayoutPanel parent = null;
	protected float center = 0;

	public SvgPanelGeneralDrawTab(TabLayoutPanel parent) {
		super();
		this.parent = parent;
		Utility.INSTANCE.addTopologyAble(this);

		this.setWidth("100%");
		this.setHeight(new Integer(Window.getClientHeight()) + "px");

		svgElement = OMSVGParser.currentDocument().createSVGSVGElement();
		svgElement.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientWidth());
		svgElement.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientHeight());
		this.getElement().appendChild(svgElement.getElement());
	}

	public void setNodesAndLinks(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		for (Node node : nodes.values()) {
			try {
				Node newNode = (Node) node.clone();
				currentNodes.put(newNode.getDpid(), newNode);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		for (NodeLink link : links.values()) {
			try {
				NodeLink newLink = (NodeLink) link.clone();
				currentLinks.put(newLink.getId(), newLink);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

	}

	public void draw() {
		drawNodes(currentNodes);
		drawLinks(currentLinks);
	}

	protected void drawNodes(Map<String, Node> nodes) {
		float radius = SvgPanel.INSTANCE.getOffsetWidth() / 4;
		calCenter();

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

	protected void drawLinks(Map<Integer, NodeLink> links) {
		for (NodeLink link : links.values()) {

			link.adjust();
			svgElement.getNode().insertFirst(link.getShape().getNode());
		}
	}

	protected void calCenter() {
		if (SvgPanel.INSTANCE.getOffsetHeight() < SvgPanel.INSTANCE.getOffsetWidth()) {
			center = SvgPanel.INSTANCE.getOffsetHeight() / 2;
		} else {
			center = SvgPanel.INSTANCE.getOffsetWidth() / 2;
		}
	}

	@Override
	public void finishDownload(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		setNodesAndLinks(nodes, links);
		draw();

	}
}
