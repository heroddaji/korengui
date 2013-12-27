package com.tranhoangdai.korengui.client.view.tab.info;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Label;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.ModelWithId;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.widget.ModelCellTable;

public class InfoPanelGlobalTopologyTab extends InfoPanelAbstractInfoTab {

	ModelCellTable<Switch> switchesCellTable= null;
	ModelCellTable<Link> linksCellTable= null;
	Map<String,Switch> switchModels = new HashMap<String, Switch>();
	Map<Integer,Link> linkModels = new HashMap<Integer, Link>();	

	public InfoPanelGlobalTopologyTab(InfoPanel _parent) {
		super(_parent);
	}

	private void setupCellTables() {
		// table for display nodes information
		Label nodeLabel = new Label("Nodes information");
		add(nodeLabel);
		
		switchesCellTable = new ModelCellTable<Switch>(ModelWithId.MODEL_GETID,ModelWithId.SWITCH_GETROLE);
		add(switchesCellTable);

		// add empty lable for nice layout
		Label emptyLabel = new Label("");	

		emptyLabel.setHeight("50px");
		add(emptyLabel);

		// table for display link
		Label linkLabel = new Label("Links information");
		add(linkLabel);

		linksCellTable = new ModelCellTable<Link>(ModelWithId.LINK_GETSRCID, ModelWithId.LINK_GETSRCPORT, ModelWithId.LINK_GETDSTID, ModelWithId.LINK_GETDSTPORT);
		add(linksCellTable);

	}

	@Override
	public void showInfo() {
		if (switchesCellTable == null || linksCellTable == null) {
			setupCellTables();
		}
		switchesCellTable.addModelData(switchModels);
		linksCellTable.addModelData(linkModels);
	}	

	public void setLinkModels(Map<Integer, Link> linkModels) {
		this.linkModels = linkModels;
	}

	public void setSwitchModels(Map<String, Switch> switchModels) {
		this.switchModels = switchModels;
	}	

	
}
