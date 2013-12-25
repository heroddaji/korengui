package com.tranhoangdai.korengui.client;

import com.allen_sauer.gwt.log.client.Log;
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
import com.tranhoangdai.korengui.client.controller.GUIController;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.SvgPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Korengui implements EntryPoint {
	public static Korengui INSTANCE = GWT.create(Korengui.class);
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	private long startTimeMillis;

	CellTable<Switch> cellTableNode;
	CellTable<Link> cellTableLink;

	Label lblStatus = null;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		Log.setUncaughtExceptionHandler();

		// use deferred command to catch initialization exceptions in onModuleLoad2
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				onModuleLoad2();
			}
		});

	}

	public void onModuleLoad2() {
	
		if (Log.isDebugEnabled()) {
			startTimeMillis = System.currentTimeMillis();
		}

		// /////////////////////gui components//////////////////////////
		RootPanel rootPanel = RootPanel.get();

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setWidth("100%");
		rootPanel.add(verticalPanel);

		//---------MENU-----------------
		MenuBar menuBar = new MenuBar(false);
		verticalPanel.add(menuBar);
		menuBar.setWidth("100%");
		
		MenuBar newMenuBar = new MenuBar();
		newMenuBar.addItem("New session", new Command() {
			
			@Override
			public void execute() {
				EventBus.INSTANCE.deliverEventUserClickNewMenu();				
			}
		});
		MenuItem fileMenu = new MenuItem("File", newMenuBar);
		menuBar.addItem(fileMenu);
		
		
		
		MenuBar aboutMenuBar = new MenuBar();
		aboutMenuBar.addItem("About KorenGUI", new Command() {
			
			@Override
			public void execute() {
				EventBus.INSTANCE.deliverEventUserClickAboutMenu();
				
			}
		});
		
		MenuItem helpMenu = new MenuItem("Help", aboutMenuBar);
		menuBar.addItem(helpMenu);
		
		//---------MENU-----------------
		
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		HorizontalPanel horizontalPanel_status = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_status);

		final Button btnTopology = new Button("Get topoloy");
		btnTopology.setText("Get topology");
		horizontalPanel.add(btnTopology);

		Button btnMakePath = new Button("path");
		btnMakePath.setText("Make Path");
		horizontalPanel.add(btnMakePath);

		Button btnZoomIn = new Button("zoomin");
		btnZoomIn.setText("zoom in");
		horizontalPanel.add(btnZoomIn);

		SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
		splitLayoutPanel.setHeight(new Integer(Window.getClientHeight() - 100).toString() + "px");
		verticalPanel.add(splitLayoutPanel);

		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		splitLayoutPanel.addSouth(horizontalPanel_1, 20);

		lblStatus = new Label("");
		lblStatus.setPixelSize(30, 30);
		lblStatus.setWidth("100%");
		horizontalPanel_status.add(lblStatus);

		// svg panel //
		final SvgPanel svgPanel = SvgPanel.INSTANCE;
		ScrollPanel scrollPanel = new ScrollPanel();
		splitLayoutPanel.addWest(scrollPanel, verticalPanel.getOffsetWidth() / 2);
		scrollPanel.setWidth("100%");
		scrollPanel.setHeight(new Integer(Window.getClientHeight() - 100).toString() + "px");
		scrollPanel.add(svgPanel);
		svgPanel.setWidth("100%");
		svgPanel.setHeight(new Integer(Window.getClientHeight() - 100).toString() + "px");

		// information panel //
		final InfoPanel infoPanel = InfoPanel.INSTANCE;
		splitLayoutPanel.addEast(infoPanel, verticalPanel.getOffsetWidth() / 2);

		// ////////////////// button events///////////////
		GUIController.INSTANCE.setStatus(lblStatus);

		btnTopology.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				EventBus.INSTANCE.deliverDownloadGlobalTopologyEvent(event.getSource());
			}
		});

		btnZoomIn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EventBus.INSTANCE.deliverEventUserClickedZoomButton(event.getSource());
			}
		});

		btnMakePath.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EventBus.INSTANCE.deliverGetPathFlowEvent(event.getSource());
			}
		});
	}

}
