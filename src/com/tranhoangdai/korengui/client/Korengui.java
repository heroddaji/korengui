package com.tranhoangdai.korengui.client;

import org.vectomatic.dom.svg.OMSVGCircleElement;
import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;
import org.vectomatic.dom.svg.utils.SVGConstants;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Korengui implements EntryPoint {

	

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
	
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get();
		
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		rootPanel.add(dockLayoutPanel, 0, 22);
		dockLayoutPanel.setSize("686px", "471px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		dockLayoutPanel.addWest(verticalPanel, 7.7);
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("Zoom out");
		verticalPanel.add(btnNewButton);
		
//		SimplePanel simplePanel = new SimplePanel();
//		simplePanel.setStyleName("center");
//		dockLayoutPanel.add(simplePanel);
		
		
		MapPanel mapPanel = new MapPanel();
		mapPanel.setStyleName("center");
		dockLayoutPanel.add(mapPanel);
		
		Label lblStatusNormal = new Label("Status: normal");
		rootPanel.add(lblStatusNormal, 101, 1);
		lblStatusNormal.setSize("226px", "15px");

		
	}
}
