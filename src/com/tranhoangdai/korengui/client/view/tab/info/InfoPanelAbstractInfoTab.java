package com.tranhoangdai.korengui.client.view.tab.info;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.view.InfoPanel;

public abstract class InfoPanelAbstractInfoTab extends VerticalPanel {
	protected InfoPanel parent = null;
	public InfoPanelAbstractInfoTab(InfoPanel _parent){
		this.parent = _parent;		
	}	
	
	public abstract void showInfo();

}
