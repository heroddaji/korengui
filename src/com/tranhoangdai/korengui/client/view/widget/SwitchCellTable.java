package com.tranhoangdai.korengui.client.view.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.tranhoangdai.korengui.client.model.Switch;

public class SwitchCellTable extends CellTable<Switch> {
	
	public SwitchCellTable(){
		TextColumn<Switch> nodeIdColumn = new TextColumn<Switch>() {

			@Override
			public String getValue(Switch object) {
				return object.getDpid();
			}
		};

		TextColumn<Switch> nodeTypeColumn = new TextColumn<Switch>() {

			@Override
			public String getValue(Switch object) {
				return object.getHarole();
			}
		};

		addColumn(nodeIdColumn, "ID");
		addColumn(nodeTypeColumn, "Type");	
	}
	
	public void addNodeModels(Map<String, Switch> nodeModels){
		setRowCount(nodeModels.size());
		List<Switch> nodeList = new ArrayList<Switch>();
		nodeList.addAll(nodeModels.values());
		setRowData(0, nodeList);
	}
	public void addNodeList(List<Switch> nodelist){
		setRowCount(nodelist.size());
		setRowData(0, nodelist);
	}
	
	
}
