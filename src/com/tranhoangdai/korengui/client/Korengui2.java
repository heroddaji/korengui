package com.tranhoangdai.korengui.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.Row;
import com.github.gwtbootstrap.client.ui.TabPane;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.view.SvgPanel;

public class Korengui2 extends Composite{

	private static Korengui2UiBinder uiBinder = GWT.create(Korengui2UiBinder.class);

	interface Korengui2UiBinder extends UiBinder<Widget, Korengui2> {
	}

	@UiField
	NavLink topologyBtn;

	@UiField
	TabPanel rightTabPanel1;

	@UiField
	TabPane globalTab;

	@UiField
	Row row;

	public Korengui2() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("topologyBtn")
	void onClick(ClickEvent e){

		Button button = new Button("sdfsd");
		row.add(button);
		//globalTab.add(button);

		SvgPanel svgPanel = SvgPanel.INSTANCE;
		EventBus.INSTANCE.deliverDownloadGlobalTopologyEvent(this);
		//globalTab.add(svgPanel);
		row.add(svgPanel);
	}
}
