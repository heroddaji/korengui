package com.tranhoangdai.korengui.client.controller;

import java.util.Map;

import com.github.gwtbootstrap.client.ui.TabLink;
import com.github.gwtbootstrap.client.ui.TabPane;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.tranhoangdai.korengui.client.Korengui;
import com.tranhoangdai.korengui.client.Korengui2;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.model.util.ModelHelper;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;
import com.tranhoangdai.korengui.client.ui.DrawingPane;
import com.tranhoangdai.korengui.client.ui.SvgPanel;
import com.tranhoangdai.korengui.client.ui.TablePanel;
import com.tranhoangdai.korengui.client.ui.svg.AbstractElementSvg;

public class ZoomEventController extends AbstractEventController {

	@Override
	public void handleEvent(Object source) {
		AbstractElementSvg zoomSvg = (AbstractElementSvg) source;
		Switch zoomSwitchModel = (Switch) zoomSvg.getModel();

		Map<String, Host> childHosts = ModelHelper.getChildHostsOfSourceSwitch(zoomSwitchModel, ClientServiceHelper.INSTANCE.getTopologyHosts());
		Map<Integer, Link> childLinks = ModelHelper.getLinksOfSourceSwitch(zoomSwitchModel, ClientServiceHelper.INSTANCE.getTopologyHosts());
		if (childHosts.size() == 0) {
			return;
		}

		//draw now
		SvgPanel rightTabPanel1 = Korengui.INSTANCE.getKorengui2().getRightTabPanel1();
		rightTabPanel1.showZoomTopology(zoomSwitchModel,childHosts,childLinks);

		TablePanel tablePanel = Korengui.INSTANCE.getKorengui2().getRightTabPanel2();
		tablePanel.showZoomTopology(zoomSwitchModel,childHosts,childLinks);

	}

}
