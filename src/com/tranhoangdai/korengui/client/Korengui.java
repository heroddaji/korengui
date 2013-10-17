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
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;

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
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		rootPanel.add(verticalPanel_1);
		
		MenuBar menuBar = new MenuBar(false);
		verticalPanel_1.add(menuBar);
		menuBar.setWidth("616px");
		MenuBar submenu = new MenuBar();
		submenu.addItem("New", new MenuBar());
		MenuItem fileMenu = new MenuItem("File",submenu);
		menuBar.addItem(fileMenu);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel);
		
		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("Send");
		horizontalPanel.add(btnNewButton_1);
		
		Button btnNewButton_2 = new Button("New button");
		btnNewButton_2.setText("Receive");
		horizontalPanel.add(btnNewButton_2);
		
		Button btnNewButton_3 = new Button("New button");
		btnNewButton_3.setText("zoom out");
		horizontalPanel.add(btnNewButton_3);
		
		SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
		verticalPanel_1.add(splitLayoutPanel);
		splitLayoutPanel.setSize("617px", "479px");
		
		MapPanel mapPanel = new MapPanel();
		splitLayoutPanel.addWest(mapPanel, 500.0);

		
	}
}
