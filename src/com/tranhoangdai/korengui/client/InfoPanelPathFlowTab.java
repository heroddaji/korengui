package com.tranhoangdai.korengui.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.interf.PathFlowNotifier;

public class InfoPanelPathFlowTab extends VerticalPanel implements PathFlowNotifier {

	CellTable<NodeLink> cellTablePath = null;
	CellTable<Node> cellTableNode = null;
	TabLayoutPanel parent = null;
	List<Node> nodes = new ArrayList<Node>();

	public InfoPanelPathFlowTab(TabLayoutPanel parent) {
		super();
		this.parent = parent;
		Utility.INSTANCE.addPathFlowAble(this);
		
	}
	
	private void setupCellTablesFlowInit() {

		// UI portion for add nodes to find patflow
		Label lblPathLabel = new Label("Add nodes to get PathFlow");
		add(lblPathLabel);

		cellTableNode = new CellTable<Node>();
		add(cellTableNode);

		TextColumn<Node> nodeColumn = new TextColumn<Node>() {

			@Override
			public String getValue(Node object) {
				return object.getDpid();
			}
		};

		cellTableNode.addColumn(nodeColumn);

		Label lblEmpty1 = new Label("");
		lblEmpty1.setHeight("20px");
		add(lblEmpty1);

		Button btnGetPathFlow = new Button("Get PathFlow");
		add(btnGetPathFlow);

		btnGetPathFlow.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nodes.size() == 2) {
					Node node1 = nodes.get(0);
					Node node2 = nodes.get(1);
					Utility.INSTANCE.downloafPathFlow(node1.getDpid(), node2.getDpid());
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

		cellTablePath = new CellTable<NodeLink>();
		add(cellTablePath);

		TextColumn<NodeLink> linkSrcColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return object.getSrcSwitch();
			}
		};

		TextColumn<NodeLink> linkDstColumn = new TextColumn<NodeLink>() {

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
		if (cellTablePath == null) {
			setupCellTablesFlowResult();
		}

		cellTablePath.setRowCount(paths.size());
		cellTablePath.setRowData(0, paths);

		parent.selectTab(this);
	}

	@Override
	public void addStartNode(Node startNode) {
		addPathFlowNode(startNode);
	}

	@Override
	public void addEndNode(Node endNode) {
		addPathFlowNode(endNode);
	}

	private void addPathFlowNode(Node node) {
		if (cellTableNode == null) {
			setupCellTablesFlowInit();
		}
		parent.selectTab(this);
		nodes.add(node);
		cellTableNode.setRowCount(nodes.size());
		cellTableNode.setRowData(0, nodes);
	}

	@Override
	public void emptyNodes() {
		if(cellTableNode != null){
			cellTableNode.setRowCount(0);
			nodes = new ArrayList<Node>();
			
		}
		if(cellTablePath != null){			
			cellTablePath.setRowCount(0);
				
		}
		Utility.INSTANCE.clearPathFlow();

	}

}
