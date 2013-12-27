package com.tranhoangdai.korengui.client.controller;

import java.util.Map;

import com.github.gwtbootstrap.client.ui.TabLink;
import com.github.gwtbootstrap.client.ui.TabPane;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.tranhoangdai.korengui.client.Korengui;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.model.util.ModelHelper;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;
import com.tranhoangdai.korengui.client.ui.DrawingPanel;
import com.tranhoangdai.korengui.client.view.svg.AbstractElementSvg;

public class ZoomEventController extends AbstractEventController {

	@Override
	public void handleEvent(Object source) {
		AbstractElementSvg zoomSvg = (AbstractElementSvg) source;
		Switch zoomSwitchModel = (Switch) zoomSvg.getModel();

		Map<String, Host> childHosts = ModelHelper.getChildHostsOfSourceSwitch(zoomSwitchModel, ClientServiceHelper.INSTANCE.getTopologyHosts());
		Map<Integer, Link> linkModels = ModelHelper.getLinksOfSourceSwitch(zoomSwitchModel, ClientServiceHelper.INSTANCE.getTopologyHosts());
		if (childHosts.size() == 0) {
			return;
		}

		//draw now
		TabPanel rightTabPanel1 = Korengui.INSTANCE.getKorengui2().getRightTabPanel1();

		TabLink tabLink = new TabLink();
		tabLink.setText("haha");
		tabLink.setTitle("hhe");
		TabPane tabPane = new TabPane(tabLink.getText());
		tabLink.setTablePane(tabPane);
		tabLink.setCreateTabPane(false);

		DrawingPanel zoomDrawingPanel = new DrawingPanel();

		rightTabPanel1.add(tabLink);
		rightTabPanel1.add(tabPane);
		tabPane.setActive(true);
		tabPane.add(zoomDrawingPanel);

		zoomDrawingPanel.drawZoom(zoomSwitchModel, childHosts, linkModels);

	}

}
