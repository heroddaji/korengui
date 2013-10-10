package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.imp.Node;
import com.tranhoangdai.korengui.client.imp.NodeCanvas;
import com.tranhoangdai.korengui.client.imp.NodeCanvas.State;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Korengui implements EntryPoint {

	NodeCanvas canvas;

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		initNodeCanvas();

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get();

		SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
		rootPanel.add(splitLayoutPanel, 5, 5);
		splitLayoutPanel.setSize("800px", "800px");

		VerticalPanel verticalPanel = new VerticalPanel();
		splitLayoutPanel.addWest(verticalPanel, 80.0);

		Button btnMove = new Button("Move");
		btnMove.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				canvas.state = State.STATE_MOVE;
			}
		});
		verticalPanel.add(btnMove);
		btnMove.setHeight("24px");

		Button btnZoomIn = new Button("ZoomIn");
		btnZoomIn.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				canvas.state = State.STATE_ZOOMIN;
			}
		});
		
		Button btnZoomOut = new Button("ZoomOut");
		btnZoomOut.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				canvas.zoomOut();				
			}
		});
		verticalPanel.add(btnZoomOut);

		verticalPanel.add(btnZoomIn);
		btnZoomIn.setHeight("24px");

		Button btnConnect = new Button("Connect");
		btnConnect.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				canvas.state = State.STATE_CONNECT;
			}
		});
		verticalPanel.add(btnConnect);

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		splitLayoutPanel.addEast(verticalPanel_1, 120.0);

		Button btnNewButton_2 = new Button("New button");
		verticalPanel_1.add(btnNewButton_2);

		Button btnNewButton_3 = new Button("New button");
		verticalPanel_1.add(btnNewButton_3);

		SimplePanel mapPanel = new SimplePanel();
		splitLayoutPanel.add(mapPanel);
		mapPanel.add(canvas);

		mapPanel.addStyleName("general");
		btnNewButton_3.addStyleName("general");
	}

	public void initNodeCanvas() {
		canvas = new NodeCanvas(400, 400);
		canvas.init();
		
	}
}
