package com.tranhoangdai.korengui.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.interf.GuiEventNotifier;
import com.tranhoangdai.korengui.client.model.Switch;

public class InfoPanel extends AbstractPanel {

	public static InfoPanel INSTANCE = GWT.create(InfoPanel.class);
	InfoPanelGeneralInfoTab nodeLinkTab = null;
	InfoPanelPathFlowTab pathFlowTab = null;
	
	public InfoPanel() {
		super(1.5, Unit.EM);
	}
	
	public void showGlobalTopology(){
		
	}

}
