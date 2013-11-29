package com.tranhoangdai.korengui.client.view.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.tranhoangdai.korengui.client.model.Link;

public class LinkCellTable extends CellTable<Link> {
	
	public LinkCellTable(){
		TextColumn<Link> linkSrcColumn = new TextColumn<Link>() {

			@Override
			public String getValue(Link object) {
				return object.getSrcSwitch();
			}
		};

		TextColumn<Link> linkSrcPortColumn = new TextColumn<Link>() {

			@Override
			public String getValue(Link object) {
				return new Integer(object.getSrcPort()).toString();
			}
		};

		TextColumn<Link> linkDestColumn = new TextColumn<Link>() {

			@Override
			public String getValue(Link object) {
				return object.getDstSwitch();
			}
		};

		TextColumn<Link> linkDestPortColumn = new TextColumn<Link>() {

			@Override
			public String getValue(Link object) {
				return new Integer(object.getDstPort()).toString();
			}
		};		

		addColumn(linkSrcColumn, "src-switch");
		addColumn(linkSrcPortColumn, "src-port");
		addColumn(linkDestColumn, "dst-switch");
		addColumn(linkDestPortColumn, "dst-port");
	}
	
	public void addLinkModels(Map<Integer, Link> linkModels){
		setRowCount(linkModels.size());
		List<Link> linkList = new ArrayList<Link>();
		linkList.addAll(linkModels.values());
		setRowData(0, linkList);
	}
	
	
}
