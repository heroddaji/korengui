package com.tranhoangdai.korengui.client.ui;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.ModelWithId;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;

public class InfoPane extends Composite {

	private static InfoPaneUiBinder uiBinder = GWT.create(InfoPaneUiBinder.class);

	interface InfoPaneUiBinder extends UiBinder<Widget, InfoPane> {
	}

	@UiField
	HTMLPanel htmlPanel;

	@UiField
	ModelCellTable<Switch> switchesCellTable;

	@UiField
	ModelCellTable<Link> linksCellTable;

	@UiField
	ModelCellTable<Host> hostsCellTable;

	public InfoPane() {
		initWidget(uiBinder.createAndBindUi(this));
		init();

	}

	private void  init(){
		switchesCellTable.setAttributes(ModelWithId.MODEL_GETID, ModelWithId.SWITCH_GETROLE);
		linksCellTable.setAttributes(ModelWithId.MODEL_GETID, ModelWithId.LINK_GETSRCID,ModelWithId.LINK_GETSRCPORT, ModelWithId.LINK_GETDSTID, ModelWithId.LINK_GETDSTPORT);
		hostsCellTable.setAttributes(ModelWithId.MODEL_GETID);
	}

	public void showGlobalTopology(){
		switchesCellTable.addModelData(ClientServiceHelper.INSTANCE.getTopologySwitches());
		linksCellTable.addModelData(ClientServiceHelper.INSTANCE.getTopologyLinks());
	}

	public void showZoomTopology(Switch  zoomModel, Map<String, Host>childHosts, Map<Integer, Link> childLinks){
		if(hostsCellTable == null){
			hostsCellTable = new ModelCellTable<Host>(ModelWithId.MODEL_GETID);
		}
		switchesCellTable.addModelData(zoomModel);
		hostsCellTable.addModelData(childHosts);

	}

}
