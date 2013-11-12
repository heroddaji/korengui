package com.tranhoangdai.korengui.client;

import org.vectomatic.dom.svg.OMSVGImageElement;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.SvgUtility;
import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;

public class SvgPanelZoomTab extends SvgPanelGeneralDrawTab implements ZoomNotifier {

	ZoomableNode zoomNode = null;	
	public SvgPanelZoomTab(TabLayoutPanel parent) {
		super(parent);
		Utility.INSTANCE.addZoomAble(this);
		
		this.setHeight(String.valueOf(Window.getClientHeight()) + "px");
		this.setWidth("100%");

	}

	public void draw() {
		drawZoomLayout();
		drawNodes(currentNodes);
		drawLinks(currentLinks);
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
	public void zoomIn(Node zoomNode) {
		setZoomNode((ZoomableNode) zoomNode);
		draw();
	}

	public void setZoomNode(ZoomableNode _zoomNode) {
		zoomNode = _zoomNode;
		currentNodes = zoomNode.getChildNodes();
		currentLinks = zoomNode.getChildLinks();
	}

}
