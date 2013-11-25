package com.tranhoangdai.korengui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
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
import com.tranhoangdai.korengui.client.controller.Utility;
import com.tranhoangdai.korengui.client.controller.Utility.ActionState;
import com.tranhoangdai.korengui.client.model.link.NodeLink;
import com.tranhoangdai.korengui.client.model.node.Node;

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

	CellTable<Node> cellTableNode;
	CellTable<NodeLink> cellTableLink;

	Label lblStatus = null;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// /////////////////////gui components//////////////////////////
		RootPanel rootPanel = RootPanel.get();

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setWidth("100%");
		rootPanel.add(verticalPanel);

		MenuBar menuBar = new MenuBar(false);
		verticalPanel.add(menuBar);
		menuBar.setWidth("100%");
		MenuBar submenu = new MenuBar();
		submenu.addItem("New", new MenuBar());
		MenuItem fileMenu = new MenuItem("File", submenu);
		menuBar.addItem(fileMenu);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);

		Button btnTopology = new Button("Get topoloy");
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

		lblStatus = new Label("Status: normal");
		lblStatus.setPixelSize(30, 30);
		horizontalPanel_1.add(lblStatus);

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


		// ////////////////// button events///////////
		btnTopology.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lblStatus.setText("Action: Get topology information");
				Utility.INSTANCE.downloadGlobalTopology();
			}
		});

		btnZoomIn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//event will propagate to visual node in mousedown event handler
				lblStatus.setText("Action: Click on cluster node to zoom in ");
				Utility.INSTANCE.setState(ActionState.ZOOM);
			}
		});

		btnMakePath.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//event will propagate to visual node in mousedown event handler
				lblStatus.setText("Action: Click on 2 nodes to get path flow");				
				Utility.INSTANCE.setState(ActionState.FLOW);
			}
		});

	}
	

}
