package com.tranhoangdai.korengui.client.view;

import java.util.HashMap;
import java.util.Map;

import org.vectomatic.dom.svg.OMSVGImageElement;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;

public class SvgPanelZoomTab extends SvgPanelAbstractDrawTab {

	protected Map<String, Switch> currentNodes = new HashMap<String, Switch>();
	protected Map<Integer, Link> currentLinks = new HashMap<Integer, Link>();
	Switch zoomModel = null;

	public SvgPanelZoomTab(AbstractPanel _parent, Switch _zoomModel) {
		super(_parent);
		zoomModel = _zoomModel;
	}

	@Override
	protected void draw() {
		// TODO Auto-generated method stub

	}

	public boolean hasZoomModel(Switch _zoomModel){
		if(zoomModel.getDpid().equals(_zoomModel.getDpid())){
			return true;
		}
		return false;
	}
	
	//	public void drawZoom() {
	//		drawZoomLayout();
	//		draw(currentNodes, currentLinks);
	//	}
	//
	//	private void drawZoomLayout() {
	//		if (zoomNode == null) {
	//			return;
	//		}
	//
	//		String opacityLevel = "0.3";
	//		float scale = 12;
	//		calCenter();
	//		OMSVGImageElement background = (OMSVGImageElement) zoomNode.getShape().cloneNode(true);
	//		svgElement.appendChild(background);
	//		background.setAttribute("opacity", opacityLevel);
	//		background = SvgUtility.scaleUp(background, center, scale);
	//
	//	}
	//
	//	@Override
	//	public void zoomIn(Node _zoomNode) {
	//		if (zoomNode != null) {
	//			if (zoomNode.equals(_zoomNode)) {
	//				parent.selectTab(this);
	//			}
	//		} else {
	//			setUpZoomNode((ZoomableNode) _zoomNode);
	//			drawZoom();
	//		}
	//	}
	//
	//	public void setUpZoomNode(ZoomableNode _zoomNode) {
	//		zoomNode = _zoomNode;
	//		currentNodes = zoomNode.getChildNodes();
	//		currentLinks = zoomNode.getChildLinks();
	//	}
	//
	//	public Node getZoomNode() {
	//		return zoomNode;
	//	}

}
