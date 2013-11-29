package com.tranhoangdai.korengui.client.view;

import java.util.ArrayList;
import java.util.List;

import com.tranhoangdai.korengui.client.view.svg.LinkSvg;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;

@SuppressWarnings("unchecked")
public class SvgPanelGlobalTopologyTab extends SvgPanelAbstractDrawTab {
	

	public SvgPanelGlobalTopologyTab(AbstractPanel _parent) {
		super(_parent);
	}

	@Override
	public void draw() {

		if (nodesSvg.size() == 0) {
			createElements(NodeSvg.class);			
		}
		if (linksSvg.size() == 0) {
			createElements(LinkSvg.class);			
		}
		
		drawNodes();
		drawLinks();

	}	

}
