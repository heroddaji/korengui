package com.tranhoangdai.korengui.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.TabPane;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TablePanel extends Composite {

	private static InfoPanelUiBinder uiBinder = GWT.create(InfoPanelUiBinder.class);

	interface InfoPanelUiBinder extends UiBinder<Widget, TablePanel> {
	}

	List<TabPane> tabs = new ArrayList<TabPane>();

	@UiField
	InfoPane globalInfoPane;

	@UiField
	TabPanel panel;
	@UiField
	TabPane firstTabPane;


	public TablePanel() {
		initWidget(uiBinder.createAndBindUi(this));
		tabs.add(firstTabPane);
	}


	public void showGlobalTopology() {
		globalInfoPane.showGlobalTopology();

	}

}
