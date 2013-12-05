package com.tranhoangdai.korengui.client.view.tab.info;

import com.google.gwt.user.client.ui.Label;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.widget.LinkCellTable;
import com.tranhoangdai.korengui.client.view.widget.SwitchCellTable;

public class InfoPanelGlobalTopologyTab extends InfoPanelAbstractInfoTab {

	SwitchCellTable cellTableNode = null;
	LinkCellTable cellTableLink = null;	

	public InfoPanelGlobalTopologyTab(InfoPanel _parent) {
		super(_parent);
	}

	private void setupCellTables() {
		// table for display nodes information
		Label nodeLabel = new Label("Nodes information");
		add(nodeLabel);
		cellTableNode = new SwitchCellTable();
		add(cellTableNode);

		// add empty lable for nice layout
		Label emptyLabel = new Label("");	

		emptyLabel.setHeight("50px");
		add(emptyLabel);

		// table for display link
		Label linkLabel = new Label("Links information");
		add(linkLabel);

		cellTableLink = new LinkCellTable();
		add(cellTableLink);

	}

	@Override
	public void showInfo() {
		if (cellTableNode == null || cellTableLink == null) {
			setupCellTables();
		}
		cellTableNode.addNodeModels(parent.getTopologySwitchModels());
		cellTableLink.addLinkModels(parent.getTopologyLinkModels());
	}	

	
}
