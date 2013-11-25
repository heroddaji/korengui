package com.tranhoangdai.korengui.client.gui.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.tranhoangdai.korengui.client.model.link.NodeLink;

public class LinkCellTable extends CellTable<NodeLink> {
	
	public LinkCellTable(){
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
		

		addColumn(linkSrcColumn, "src-switch");
		addColumn(linkSrcPortColumn, "src-port");
		addColumn(linkDestColumn, "dst-switch");
		addColumn(linkDestPortColumn, "dst-port");
	}
	
	public void addLinkMap(Map<Integer, NodeLink> linkmap){
		setRowCount(linkmap.size());
		List<NodeLink> linkList = new ArrayList<NodeLink>();
		linkList.addAll(linkmap.values());
		setRowData(0, linkList);
	}
	
	
}
