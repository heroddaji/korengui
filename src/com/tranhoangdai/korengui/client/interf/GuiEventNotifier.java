package com.tranhoangdai.korengui.client.interf;

import com.tranhoangdai.korengui.client.model.Switch;

public interface GuiEventNotifier {
	
	void eventGlobalTopology();	
	void eventGetPathFlow(Switch node);
	
	
}
