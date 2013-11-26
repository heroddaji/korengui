package com.tranhoangdai.korengui.client.interf;

import java.util.Map;

import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.model.Link;

public interface PathFlowNotifier {
	public void addStartNode(Switch startNode);
	public void addEndNode(Switch endNode);
	public void emptyNodes();
	public void pathIsSetup(Map<Integer,Link> paths);
}
