package com.tranhoangdai.korengui.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;

public class NodeLinkInfoTab extends VerticalPanel implements TopologyNotifier {

	CellTable<Node> cellTableNode = null;
	CellTable<NodeLink> cellTableLink = null;
	TabLayoutPanel parent = null;
	
	public NodeLinkInfoTab(TabLayoutPanel parent){
		super();
		this.parent = parent;
		setupEventHandlers();
	}
	
	private void setupEventHandlers() {

		Utility.INSTANCE.addTopologyAble(this);
	}

	private void setupCellTables() {
		// table for display nodes information
		Label nodeLabel = new Label("Nodes information");
		add(nodeLabel);

		cellTableNode = new CellTable<Node>();
		add(cellTableNode);
		TextColumn<Node> nodeIdColumn = new TextColumn<Node>() {

			@Override
			public String getValue(Node object) {
				return object.getDpid();
			}
		};

		TextColumn<Node> nodeTypeColumn = new TextColumn<Node>() {

			@Override
			public String getValue(Node object) {
				return object.getHarole();
			}
		};

		cellTableNode.addColumn(nodeIdColumn, "ID");
		cellTableNode.addColumn(nodeTypeColumn, "Type");

		// add empty lable for nice layout
		Label emptyLabel = new Label("");
		emptyLabel.setHeight("50px");
		add(emptyLabel);

		// table for display link
		Label linkLabel = new Label("Links information");
		add(linkLabel);
		cellTableLink = new CellTable<NodeLink>();
		add(cellTableLink);
		TextColumn<NodeLink> linkSrcColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return object.getSrcSwitch();
			}
		};

		TextColumn<NodeLink> linkSrcPortColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return new Integer(object.getSrcPort()).toString();
			}
		};

		TextColumn<NodeLink> linkDestColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return object.getDstSwitch();
			}
		};

		TextColumn<NodeLink> linkDestPortColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return new Integer(object.getDstPort()).toString();
			}
		};

		cellTableLink.addColumn(linkSrcColumn, "src-switch");
		cellTableLink.addColumn(linkSrcPortColumn, "src-port");
		cellTableLink.addColumn(linkDestColumn, "dst-switch");
		cellTableLink.addColumn(linkDestPortColumn, "dst-port");

	}

	@Override
	public void finishDownload(Map<String, Node> nodes, Map<Integer, NodeLink> links) {
		if(cellTableNode == null || cellTableLink == null){
			setupCellTables();
		}
		
		cellTableNode.setRowCount(nodes.size());
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.addAll(nodes.values());
		cellTableNode.setRowData(0, nodeList);

		cellTableLink.setRowCount(links.size());
		List<NodeLink> linkList = new ArrayList<NodeLink>();
		linkList.addAll(links.values());
		cellTableLink.setRowData(0, linkList);
	}

}
