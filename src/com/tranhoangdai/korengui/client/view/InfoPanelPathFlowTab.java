package com.tranhoangdai.korengui.client.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.view.widget.LinkCellTable;
import com.tranhoangdai.korengui.client.view.widget.NodeCellTable;

public class InfoPanelPathFlowTab extends VerticalPanel implements PathFlowNotifier {

	LinkCellTable cellTablePath = null;
	NodeCellTable cellTableNode = null;
	TabLayoutPanel parent = null;
	List<Switch> nodes = new ArrayList<Switch>();

	public InfoPanelPathFlowTab(InfoPanel parent) {
		super();
		this.parent = parent;
		Utility.INSTANCE.addPathFlowAble(this);
	}

	private void setupCellTablesFlowInit() {

		// UI portion for add nodes to find patflow
		Label lblPathLabel = new Label("Add nodes to get PathFlow");
		add(lblPathLabel);

		cellTableNode = new NodeCellTable();
		add(cellTableNode);

		

		Label lblEmpty1 = new Label("");
		lblEmpty1.setHeight("20px");
		add(lblEmpty1);

		Button btnGetPathFlow = new Button("Get PathFlow");
		add(btnGetPathFlow);

		btnGetPathFlow.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nodes.size() == 2) {				
					
					Switch node1 = nodes.get(0);
					Switch node2 = nodes.get(1);
					Utility.INSTANCE.downloadPathFlow(node1.getDpid(), node2.getDpid());
				}
			}
		});

		Button btnClearPathFlow = new Button("Clear");
		add(btnClearPathFlow);

		btnClearPathFlow.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				emptyNodes();
			}
		});

		Label lblEmpty2 = new Label("");
		lblEmpty2.setHeight("50px");
		add(lblEmpty2);

	}

	private void setupCellTablesFlowResult() {
		// ui portion for result of getting pathflow
		Label resultLabel = new Label("PathFlow result");
		add(resultLabel);

		cellTablePath = new LinkCellTable();
		add(cellTablePath);
	
	}

	@Override
	public void pathIsSetup(Map<Integer,Link> paths) {
		if (cellTablePath == null) {
			setupCellTablesFlowResult();		}

//		cellTablePath.addLinkModels(parent)
		parent.selectTab(this);
	}

	@Override
	public void addStartNode(Switch startNode) {
		addPathFlowNode(startNode);
	}

	@Override
	public void addEndNode(Switch endNode) {
		addPathFlowNode(endNode);
	}

	private void addPathFlowNode(Switch node) {
		if (cellTableNode == null) {
			setupCellTablesFlowInit();
		}		
		if (nodes.size() < 2) {
			parent.selectTab(this);
			nodes.add(node);
			cellTableNode.addNodeList(nodes);
		}
	}

	@Override
	public void emptyNodes() {
		if (cellTableNode != null) {
			cellTableNode.setRowCount(0);
			nodes = new ArrayList<Switch>();

		}
		if (cellTablePath != null) {
			cellTablePath.setRowCount(0);

		}
		Utility.INSTANCE.clearPathFlow();

	}

}
