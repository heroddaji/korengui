package com.tranhoangdai.korengui.client;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;

public class PathFlowTab extends VerticalPanel implements PathFlowNotifier {

	CellTable<NodeLink> cellTablePath = null;
	TabLayoutPanel parent = null;
	
	public PathFlowTab(TabLayoutPanel parent) {
		super();
		this.parent = parent;
		setupEventHandlers();
	}

	private void setupEventHandlers() {
		Utility.INSTANCE.addPathFlowAble(this);
	}

	private void setupCellTables() {		
		
		// table for display nodes information
		Label nodeLabel = new Label("Nodes information");
		add(nodeLabel);

		cellTablePath = new CellTable<NodeLink>();
		add(cellTablePath);
		TextColumn<NodeLink> linkSrcColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return object.getSrcSwitch();
			}
		};

		TextColumn<NodeLink> linkDstColumn = new TextColumn<NodeLink>(){

			@Override
			public String getValue(NodeLink object) {
				return object.getDstSwitch();
			}
		};

		cellTablePath.addColumn(linkSrcColumn, "From");
		cellTablePath.addColumn(linkDstColumn, "To");

	}

	@Override
	public void pathIsSetup(List<NodeLink> paths) {
		if(cellTablePath == null){
			
			setupCellTables();
		}
		
		cellTablePath.setRowCount(paths.size());
		cellTablePath.setRowData(0, paths);
		
		//select this tab
		parent.selectTab(this);
	}

	
}
