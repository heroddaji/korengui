package com.tranhoangdai.korengui.client;

import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;

public class InfoPanelZoomTab extends VerticalPanel implements ZoomNotifier {

	private TabLayoutPanel parent = null;
	public InfoPanelZoomTab(TabLayoutPanel _parent){
		parent =  _parent;
	}
	
	@Override
	public void zoomIn(Node zoomNode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getZoomNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
