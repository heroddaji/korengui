package com.tranhoangdai.korengui.client.view.tab.info;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Label;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.ModelWithId;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.ui.ModelCellTable;
import com.tranhoangdai.korengui.client.view.InfoPanel;

public class InfoPanelZoomTab extends InfoPanelAbstractInfoTab{
	
	ModelCellTable<Switch> switchCellTable = null;
	ModelCellTable<Host> hostsCellTable = null;
	Switch zoomModel = null;
	Map<String, Host> childModels = new HashMap<String, Host>();
	Map<Integer, Link> linkModels = new HashMap<Integer, Link>();
	
	
	public InfoPanelZoomTab(InfoPanel _parent, Switch _zoomModel) {
		super(_parent);
		this.zoomModel = _zoomModel;
	}


	private void setupCellTables() {
		// table for display nodes information
		Label nodeLabel = new Label("Switch information");
		add(nodeLabel);

		switchCellTable = new ModelCellTable<Switch>(ModelWithId.MODEL_GETID);
		add(switchCellTable);

		// add empty lable for nice layout
		Label emptyLabel = new Label("");
		emptyLabel.setHeight("50px");
		add(emptyLabel);

		// table for display link
		Label linkLabel = new Label("Hosts information");
		add(linkLabel);
		
		hostsCellTable = new ModelCellTable<Host>(ModelWithId.MODEL_GETID);
		add(hostsCellTable);
	}


	@Override
	public void showInfo() {
		if (switchCellTable == null || hostsCellTable == null) {
			setupCellTables();
		}
		
		switchCellTable.addModelData(zoomModel);
		hostsCellTable.addModelData(childModels);
		
	}


	public boolean hasZoomModel(Switch zoomSwitchModel) {
		if ( zoomModel.getId().equals(zoomSwitchModel.getId())){
			return true;
		}
		
		return false;
	}


	public void setChildModels(Map<String, Host> childModels) {
		this.childModels = childModels;
	}


	public void setLinkModels(Map<Integer, Link> linkModels) {
		this.linkModels = linkModels;
	}	
	
}
