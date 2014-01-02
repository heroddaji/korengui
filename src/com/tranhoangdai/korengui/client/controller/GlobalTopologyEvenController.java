package com.tranhoangdai.korengui.client.controller;

import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.tranhoangdai.korengui.client.Korengui2;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.util.ClientServiceAsync;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;
import com.tranhoangdai.korengui.client.ui.SvgPanel;

@SuppressWarnings("unused")
public class GlobalTopologyEvenController extends AbstractEventController {

	public GlobalTopologyEvenController() {
	}

	/**
	 * Download network topology, then draw
	 *
	 * @param source
	 */
	@Override
	public void handleEvent(final Object source) {

		final ClientServiceAsync<Map<String, Switch>, Map<Integer, Link>, Map<String, Host>> callback = new ClientServiceAsync<Map<String, Switch>, Map<Integer, Link>, Map<String, Host>>() {

			@Override
			public void onSuccess(Map<String, Switch> switches, Map<Integer, Link> links, Map<String, Host> hosts) {
				Log.debug("finish download network topology");

				Scheduler.get().scheduleDeferred(new Command() {
					public void execute() {
					}
				});


				//draw now
				Korengui2 korengui2 = (Korengui2)source;
				SvgPanel rightTabPanel1 = korengui2.getRightTabPanel1();
				rightTabPanel1.showGlobalTopology();
			}

			@Override
			public void onFailure(Throwable throwable) {
				System.err.println(throwable);
			}
		};
		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				ClientServiceHelper.INSTANCE.getGlobalTopology(callback);
			}
		});

	}

}
