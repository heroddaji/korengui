package com.tranhoangdai.korengui.client.controller;

import java.util.HashMap;
import java.util.Map;

import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Node;

public class AbstractEventController {
	protected Map<String, Node> currentNodes = new HashMap<String, Node>();
	protected Map<Integer, Link> currentLinks = new HashMap<Integer, Link>();

	public <V, K> AbstractEventController(Map<K, V> nodes, Map<K, V> links) {
		
	}

	
}
