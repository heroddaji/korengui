package com.tranhoangdai.korengui.client.controller;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.util.ClientServiceAsync;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;

@SuppressWarnings("unused")
public class GlobalTopologyEvenController extends AbstractEventController {

	public static GlobalTopologyEvenController INSTANCE = GWT.create(GlobalTopologyEvenController.class);
	
	private GlobalTopologyEvenController(){}

	/**
	 * Download network topology, then draw
	 * 
	 * @param source
	 */
	@Override
	public void handleEvent(Object source) {
		ClientServiceAsync<Map<String,Switch>, Map<Integer,Link>, Map<String,Host>> callback = new ClientServiceAsync<Map<String,Switch>, Map<Integer,Link>, Map<String,Host>>() {
			
			@Override
			public void onSuccess(Map<String, Switch> switches, Map<Integer, Link> links, Map<String, Host> hosts) {
				
				int a = 0;
			}
			
			@Override
			public void onFailure(Throwable throwable) {
				System.err.println(throwable);				
			}
		};
		
		ClientServiceHelper.INSTANCE.getGlobalTopology(callback);
	}

}
