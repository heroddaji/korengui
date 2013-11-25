package com.tranhoangdai.korengui.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;
import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.model.NodeLink;
import com.tranhoangdai.korengui.client.view.widget.LinkCellTable;
import com.tranhoangdai.korengui.client.view.widget.NodeCellTable;

public class InfoPanelGeneralInfoTab extends VerticalPanel implements TopologyNotifier {

	NodeCellTable cellTableNode = null;
	LinkCellTable cellTableLink = null;
	TabLayoutPanel parent = null;
	
	public InfoPanelGeneralInfoTab(TabLayoutPanel parent){
		super();
		this.parent = parent;
		Utility.INSTANCE.addTopologyAble(this);
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

	@Override
	public void finishDownload(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		
		if(cellTableNode == null || cellTableLink == null){
			setupCellTables();
		}		
		cellTableNode.addNodeMap(nodes);	
		cellTableLink.addLinkMap(links);
		
	}

}
