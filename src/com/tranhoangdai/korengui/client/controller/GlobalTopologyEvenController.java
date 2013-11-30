package com.tranhoangdai.korengui.client.controller;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.DefaultLevel.Info;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.util.ClientServiceAsync;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.SvgPanel;

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
				
				/*
				 * Never pass svg elements to Svgpanel or Infopanel, since those svg elements
				 * will be draw on screen, they can get cross references in different tabs.
				 * Always pass just the models, then the draw will create only svg elements for 
				 * that tab to draw  
				 */
				SvgPanel.INSTANCE.setModelInformation(switches, links, hosts);
				SvgPanel.INSTANCE.drawGlobalTopology();
				InfoPanel.INSTANCE.setModelInformation(switches, links, hosts);
				InfoPanel.INSTANCE.showGlobalTopology();
			}
			
			@Override
			public void onFailure(Throwable throwable) {
				System.err.println(throwable);				
			}
		};
		
		ClientServiceHelper.INSTANCE.getGlobalTopology(callback);
	}

}
