package com.tranhoangdai.korengui.client.interf;

import java.util.List;

import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;

public interface PathFlowNotifier {
	public void addStartNode(Node startNode);
	public void addEndNode(Node endNode);
	public void emptyNodes();
	public void pathIsSetup(List<NodeLink> paths);
}
