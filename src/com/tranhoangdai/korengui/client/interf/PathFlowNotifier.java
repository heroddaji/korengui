package com.tranhoangdai.korengui.client.interf;

import java.util.Map;

import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.model.NodeLink;

public interface PathFlowNotifier {
	public void addStartNode(Node startNode);
	public void addEndNode(Node endNode);
	public void emptyNodes();
	public void pathIsSetup(Map<Integer,NodeLink> paths);
}
