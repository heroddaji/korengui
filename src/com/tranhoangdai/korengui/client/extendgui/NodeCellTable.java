package com.tranhoangdai.korengui.client.extendgui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.tranhoangdai.korengui.client.model.node.Node;

public class NodeCellTable extends CellTable<Node> {
	
	public NodeCellTable(){
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

		addColumn(nodeIdColumn, "ID");
		addColumn(nodeTypeColumn, "Type");	
	}
	
	public void addNodeMap(Map<String, Node> nodemap){
		setRowCount(nodemap.size());
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.addAll(nodemap.values());
		setRowData(0, nodeList);
	}
	public void addNodeList(List<Node> nodelist){
		setRowCount(nodelist.size());
		setRowData(0, nodelist);
	}
	
	
}
