package com.tranhoangdai.korengui.client;

import com.allen_sauer.gwt.log.client.Log;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.controller.GlobalTopologyEvenController;
import com.tranhoangdai.korengui.client.controller.PathFlowEventController;
import com.tranhoangdai.korengui.client.controller.ZoomEventController;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.SvgPanel;

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
