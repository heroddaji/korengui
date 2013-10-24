package com.tranhoangdai.korengui.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.Utility.ActionState;
import com.tranhoangdai.korengui.client.imp.link.NodeLink;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.interf.TopologyNotifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Korengui implements EntryPoint, TopologyNotifier {
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

		///////////////////////gui components//////////////////////////
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

		Button btnNewButton_2 = new Button("New button");
		btnNewButton_2.setText("Receive");
		horizontalPanel.add(btnNewButton_2);

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

		final SvgPanel svgPanel = new SvgPanel();
		splitLayoutPanel.addWest(svgPanel, verticalPanel.getOffsetWidth() / 2);

		TabLayoutPanel tabLayoutPanel = new TabLayoutPanel(1.5, Unit.EM);

		VerticalPanel verticalPanel_tab1 = new VerticalPanel();
		tabLayoutPanel.add(verticalPanel_tab1, "Nodes/Links", false);
		verticalPanel_tab1.setWidth("100%");
		splitLayoutPanel.addEast(tabLayoutPanel, verticalPanel.getOffsetWidth() / 2);
		//////////////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////// button events///////////
		btnTopology.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lblStatus.setText("Action: Get topology information");
				svgPanel.setupGlobalTopology();
			}
		});
		
		btnZoomIn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {					
				lblStatus.setText("Action: Click on cluster node to zoom in ");
				Utility.INSTANCE.setState( ActionState.ZOOMIN);				
				
			}
		});
		///////////////////////////////////////////////////////////////
		
		///////////////////////////SETUP EVENT INTERFACE CONNECTION/////////////////////////
		Utility.INSTANCE.addTopologyAble(this);
		//////////////////////////////////////////////////////////////
		
		//////////////CELL TABLE FOR TOPOLOGY INFO EVENT CALLBACK///////////////////////////////
		setupCellTables(verticalPanel_tab1);
		/////////////////////////////////////////////////////////////////////////
		
		

	}

	private void setupCellTables(Panel panel) {
		// table for display nodes information
		Label nodeLabel = new Label("Nodes information");
		panel.add(nodeLabel);
		
		cellTableNode = new CellTable<Node>();
		panel.add(cellTableNode);
		TextColumn<Node> nodeIdColumn = new TextColumn<Node>() {

			@Override
			public String getValue(Node object) {
				return object.getDpid();
			}
		};

		TextColumn<Node> nodeTypeColumn = new TextColumn<Node>() {

			@Override
			public String getValue(Node object) {
				return object.getHarole();
			}
		};

		cellTableNode.addColumn(nodeIdColumn, "ID");
		cellTableNode.addColumn(nodeTypeColumn, "Type");

		//add empty lable for nice layout
		Label emptyLabel= new Label("");
		emptyLabel.setHeight("50px");
		panel.add(emptyLabel);
		
		// table for display link
		Label linkLabel = new Label("Links information");
		panel.add(linkLabel);
		cellTableLink = new CellTable<NodeLink>();
		panel.add(cellTableLink);
		TextColumn<NodeLink> linkSrcColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return object.getSrcSwitch();
			}
		};
		
		TextColumn<NodeLink> linkSrcPortColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return new Integer(object.getSrcPort()).toString();
			}
		};

		TextColumn<NodeLink> linkDestColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return object.getDstSwitch();
			}
		};
		
		TextColumn<NodeLink> linkDestPortColumn = new TextColumn<NodeLink>() {

			@Override
			public String getValue(NodeLink object) {
				return new Integer(object.getDstPort()).toString();
			}
		};
		
		cellTableLink.addColumn(linkSrcColumn, "src-switch");
		cellTableLink.addColumn(linkSrcPortColumn, "src-port");		
		cellTableLink.addColumn(linkDestColumn, "dst-switch");
		cellTableLink.addColumn(linkDestPortColumn, "dst-port");

	}

	@Override
	public void finishDownload(Map<String, Node> nodes, Map<Integer, NodeLink> links){
		cellTableNode.setRowCount(nodes.size());
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.addAll(nodes.values());
		cellTableNode.setRowData(0, nodeList);
		
		cellTableLink.setRowCount(links.size());
		List<NodeLink> linkList = new ArrayList<NodeLink>();
		linkList.addAll(links.values());
		cellTableLink.setRowData(0, linkList);
	}
	
	public void changeStatus(String newStatus){
		lblStatus.setText(newStatus);
	}

}
