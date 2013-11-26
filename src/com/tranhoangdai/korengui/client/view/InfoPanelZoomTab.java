package com.tranhoangdai.korengui.client.view;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;
import com.tranhoangdai.korengui.client.view.widget.LinkCellTable;
import com.tranhoangdai.korengui.client.view.widget.NodeCellTable;

public class InfoPanelZoomTab extends VerticalPanel{

	private TabLayoutPanel parent = null;
	private NodeSvg zoomNode = null;
	NodeCellTable cellTableNode = null;
	LinkCellTable cellTableLink = null;

	public InfoPanelZoomTab(TabLayoutPanel _parent) {
		parent = _parent;
		
	}

	

	private void setupCellTables() {
		// table for display nodes information
		Label nodeLabel = new Label("Nodes information");
		add(nodeLabel);

		cellTableNode = new NodeCellTable();
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

	
}
