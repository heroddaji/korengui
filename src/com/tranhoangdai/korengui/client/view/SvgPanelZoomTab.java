package com.tranhoangdai.korengui.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vectomatic.dom.svg.OMNode;

import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.svg.HostSvg;
import com.tranhoangdai.korengui.client.view.svg.LinkSvg;
import com.tranhoangdai.korengui.client.view.svg.SwitchSvg;
import com.tranhoangdai.korengui.client.view.svg.util.SvgTransformationHelper;

public class SvgPanelZoomTab extends SvgPanelAbstractDrawTab {
	
	Switch zoomModel = null;
	Map<String,Host> childModels = new HashMap<String, Host>();
	Map<Integer,Link> linkModels = new HashMap<Integer, Link>();
	

	public SvgPanelZoomTab(AbstractPanel _parent, Switch _zoomModel) {
		super(_parent);
		zoomModel = _zoomModel;
	}

	@Override
	public void draw() {
		SwitchSvg zoomSwitchSvg = (SwitchSvg) createSvgElement(SwitchSvg.class, zoomModel);
		
		HostSvg hostSvgClass = new HostSvg();
		LinkSvg linkSvgClass = new LinkSvg();
		List<HostSvg> hostSvgs = createSvgElements(hostSvgClass, childModels.values());
		List<LinkSvg> linkSvgs = createSvgElements(linkSvgClass, linkModels.values());
		
		drawZoomSwitchSvg(zoomSwitchSvg);
		drawNodeElementsSvg(hostSvgs);
		drawZoomLinksSvg(zoomSwitchSvg,hostSvgs,linkSvgs);
		
	}

	private void drawZoomLinksSvg(SwitchSvg zoomSwitchSvg, List<HostSvg> hostSvgs, List<LinkSvg> linkSvgs) {
		
		
	}

	private void drawZoomSwitchSvg(SwitchSvg zoomSvg) {	
		calCenter();	
		zoomSvg.formElement();
		SvgTransformationHelper.translateTo(zoomSvg, (int)center, (int)center);		
		svgElement.appendChild((OMNode) zoomSvg);				
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


	public void setLinkModels(Map<Integer, Link> linkModels) {
		this.linkModels = linkModels;
	}	

}
