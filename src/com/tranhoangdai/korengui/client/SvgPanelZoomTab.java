package com.tranhoangdai.korengui.client;

import java.util.HashMap;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGImageElement;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;
import com.tranhoangdai.korengui.client.model.helper.SvgUtility;
import com.tranhoangdai.korengui.client.model.link.NodeLink;
import com.tranhoangdai.korengui.client.model.node.Node;
import com.tranhoangdai.korengui.client.model.node.zoom.ZoomableNode;

public class SvgPanelZoomTab extends SvgPanelAbstractDrawTab implements ZoomNotifier {

	protected Map<String, Node> currentNodes = new HashMap<String, Node>();
	protected Map<Integer, NodeLink> currentLinks = new HashMap<Integer, NodeLink>();
	ZoomableNode zoomNode = null;

	public SvgPanelZoomTab(TabLayoutPanel parent) {
		super(parent);
		Utility.INSTANCE.addZoomAble(this);		
	}

	public void drawZoom() {
		drawZoomLayout();
		draw(currentNodes, currentLinks);
	}

	private void drawZoomLayout() {
		if (zoomNode == null) {
			return;
		}

		String opacityLevel = "0.3";
		float scale = 12;
		calCenter();
		OMSVGImageElement background = (OMSVGImageElement) zoomNode.getShape().cloneNode(true);
		svgElement.appendChild(background);
		background.setAttribute("opacity", opacityLevel);
		background = SvgUtility.scaleUp(background, center, scale);

	}

	@Override
	public void zoomIn(Node _zoomNode) {
		if (zoomNode != null) {
			if (zoomNode.equals(_zoomNode)) {
				parent.selectTab(this);
			}
		} else {
			setUpZoomNode((ZoomableNode) _zoomNode);
			drawZoom();
		}
	}

	public void setUpZoomNode(ZoomableNode _zoomNode) {
		zoomNode = _zoomNode;
		currentNodes = zoomNode.getChildNodes();
		currentLinks = zoomNode.getChildLinks();
	}

	public Node getZoomNode() {
		return zoomNode;
	}

}
