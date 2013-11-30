package com.tranhoangdai.korengui.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;

public class GUIInstructionController {
	
	public static GUIInstructionController INSTANCE = GWT.create(GUIInstructionController.class);
	
	Label status;
	
	public void tellGlobalNetworkInstruction(){
		status.setText("GETTING NETWORK TOPOLOGY INFORMATION");
	}
	
	public void tellZoomInstruction(){
		status.setText("CLICK ON A NODE TO ZOOM IN");
	}
	
	public void tellDependentAction(){
		status.setText("PLEASE GET NETWORK TOPOLOGY FIRST");
	}
	
	public void clear(){
		status.setText("");
	}
	
	public void tellConnectedHostsToZoomIn(){
		Window.alert("This Node has no child Hosts to zoom in");
	}

	public Label getStatus() {
		return status;
	}

	public void setStatus(Label status) {
		this.status = status;
	}
}
