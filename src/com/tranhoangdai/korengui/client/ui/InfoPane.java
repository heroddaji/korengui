package com.tranhoangdai.korengui.client.ui;

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

	ModelCellTable<Switch> switchesCellTable = null;
	ModelCellTable<Link> linksCellTable = null;
	ModelCellTable<Host> hostsCellTable = null;

	public InfoPane() {
		initWidget(uiBinder.createAndBindUi(this));
		init();

	}

	private void  init(){
		switchesCellTable = new ModelCellTable<Switch>(ModelWithId.MODEL_GETID, ModelWithId.SWITCH_GETROLE);
		htmlPanel.add(switchesCellTable);
	}

	public void showGlobalTopology(){
		switchesCellTable.addModelData(ClientServiceHelper.INSTANCE.getTopologySwitches());
	}

}
