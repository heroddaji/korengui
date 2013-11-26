package com.tranhoangdai.korengui.client.interf;

import java.util.Map;

import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.model.Link;


public interface TopologyNotifier {
	public void finishDownload(Map<String, Switch> nodes, Map<Integer, Link> links);	
}
