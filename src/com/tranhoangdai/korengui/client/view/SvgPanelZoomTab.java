package com.tranhoangdai.korengui.client.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vectomatic.dom.svg.OMNode;

import com.tranhoangdai.korengui.client.model.GeneralModel;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.svg.AbstractElementSvg;
import com.tranhoangdai.korengui.client.view.svg.HostSvg;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;
import com.tranhoangdai.korengui.client.view.svg.SwitchSvg;

public class SvgPanelZoomTab extends SvgPanelAbstractDrawTab {
	
	Switch zoomModel = null;
	Map<String,Host> childModels = new HashMap<String, Host>();
	

	public SvgPanelZoomTab(AbstractPanel _parent, Switch _zoomModel) {
		super(_parent);
		zoomModel = _zoomModel;
	}

	@Override
	public void draw() {
		SwitchSvg zoomSwitchSvg = (SwitchSvg) createSvgElement(SwitchSvg.class, zoomModel);
		
		HostSvg hostSvgClass = new HostSvg();
		List<HostSvg> hostSvgs = createSvgElements(hostSvgClass, childModels.values());
		
		drawZoomSwitchSvg(zoomSwitchSvg);
		drawHosts(hostSvgs);
	}

	private void drawZoomSwitchSvg(SwitchSvg zoomSvg) {	
		calCenter();	
		zoomSvg.formElement();				
		zoomSvg.translateTo((int)center, (int)center);
		svgElement.appendChild((OMNode) zoomSvg);				
	}

	private void drawHosts(List<HostSvg> hostSvgs) {
		for(HostSvg hostSvg: hostSvgs){
			
		}
		
	}

	public boolean hasZoomModel(Switch _zoomModel){
		if(zoomModel.getDpid().equals(_zoomModel.getDpid())){
			return true;
		}
		return false;
	}
	
	public void setChildModels(Map<String,Host> _childModels){
		this.childModels = _childModels;
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
