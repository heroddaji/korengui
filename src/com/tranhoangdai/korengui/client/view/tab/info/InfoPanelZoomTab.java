package com.tranhoangdai.korengui.client.view.tab.info;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Label;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.widget.LinkCellTable;
import com.tranhoangdai.korengui.client.view.widget.SwitchCellTable;

public class InfoPanelZoomTab extends InfoPanelAbstractInfoTab{

	Switch zoomModel = null;
	SwitchCellTable cellTableNode = null;
	LinkCellTable cellTableLink = null;
	Map<String, Host> childModels = new HashMap<String, Host>();
	Map<Integer, Link> linkModels = new HashMap<Integer, Link>();
	
	
	public InfoPanelZoomTab(InfoPanel _parent, Switch _zoomModel) {
		super(_parent);
		this.zoomModel = _zoomModel;
	}


	private void setupCellTables() {
		// table for display nodes information
		Label nodeLabel = new Label("Nodes information");
		add(nodeLabel);

		cellTableNode = new SwitchCellTable();
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
	public void showInfo() {
		if (cellTableNode == null || cellTableLink == null) {
			setupCellTables();
		}
		
		
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
