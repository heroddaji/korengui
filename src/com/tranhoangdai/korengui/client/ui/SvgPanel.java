package com.tranhoangdai.korengui.client.ui;

import java.util.Map;

import com.github.gwtbootstrap.client.ui.TabPane;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;

public class SvgPanel extends Composite {

	private static SvgPanelUiBinder uiBinder = GWT.create(SvgPanelUiBinder.class);

	interface SvgPanelUiBinder extends UiBinder<Widget, SvgPanel> {
	}

	@UiField
	TabPanel panel;
	@UiField
	DrawingPane globalDrawingPane;

	public SvgPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void showGlobalTopology() {
		globalDrawingPane.drawGlobalTopology();
	}

	public void showZoomTopology(Switch  zoomModel, Map<String, Host>childHosts, Map<Integer, Link> childLinks) {
		TabPane zoomPane = new TabPane("Zoom:"+ zoomModel.getDpid());
		zoomPane.setActive(true);

		DrawingPane zoomDrawingPane = new DrawingPane();
		zoomDrawingPane.drawZoom(zoomModel, childHosts, childLinks);

		panel.add(zoomPane);
		zoomPane.add(zoomDrawingPane);

	}

}
