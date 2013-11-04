package com.tranhoangdai.korengui.client.interf;

import java.util.List;

import com.tranhoangdai.korengui.client.imp.link.NodeLink;

public interface PathFlowNotifier {
	public void pathIsSetup(List<NodeLink> paths);
}
