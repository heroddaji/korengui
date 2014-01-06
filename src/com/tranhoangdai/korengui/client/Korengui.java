package com.tranhoangdai.korengui.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.RootPanel;
import com.tranhoangdai.korengui.client.controller.GlobalTopologyEvenController;
import com.tranhoangdai.korengui.client.controller.PathFlowEventController;
import com.tranhoangdai.korengui.client.controller.ZoomEventController;
import com.tranhoangdai.korengui.resources.KorenResources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Korengui implements EntryPoint {
	public static Korengui INSTANCE = GWT.create(Korengui.class);
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";
	static Korengui2 korengui2= null;
	EventBus eventBus = null;
	GlobalTopologyEvenController globalTopologyEvenController = null;
	ZoomEventController zoomEventController = null;
	PathFlowEventController pathFlowEventController = null;

	public void onModuleLoad() {

		//inject resource
		KorenResources.INSTANCE.css().ensureInjected();


		Log.setUncaughtExceptionHandler();

		// use deferred command to catch initialization exceptions in onModuleLoad2
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {

				korengui2 = new Korengui2();
				RootPanel.get().add(korengui2);
			}
		});

	}

	public Korengui2 getKorengui2() {
		if(korengui2 == null){
			korengui2 = new Korengui2();
		}
		return korengui2 ;
	}

	public EventBus getEventBus() {
		if(eventBus == null){
			eventBus = new EventBus();
		}
		return eventBus;
	}

	public GlobalTopologyEvenController getGlobalTopologyEvenController() {
		if(globalTopologyEvenController == null){
			globalTopologyEvenController = new GlobalTopologyEvenController();
		}
		return globalTopologyEvenController;
	}


	public ZoomEventController getZoomEventController() {
		if(zoomEventController == null){
			zoomEventController = new ZoomEventController();
		}
		return zoomEventController;
	}



	public PathFlowEventController getPathFlowEventController() {
		if(pathFlowEventController == null){
			pathFlowEventController = new PathFlowEventController();
		}
		return pathFlowEventController;
	}

}
