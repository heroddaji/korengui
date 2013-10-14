package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGRect;
import org.vectomatic.dom.svg.OMSVGRectElement;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTransform;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class MapPanel extends FlowPanel {

	public static MapPanel INSTANCE = GWT.create(MapPanel.class);

	OMSVGSVGElement svg = null;
	Node clickedNode = null;
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<NodePath> paths = new ArrayList<NodePath>();

	public Button but1;
	public Button but2;
	public Button but3;

	public MapPanel() {
		super();

		OMSVGDocument doc = OMSVGParser.currentDocument();
		svg = doc.createSVGSVGElement();

		svg.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, 600);
		svg.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, 600);

		Gateway gate1 = new Gateway("10.0.0.1", 150, 150);
		Gateway gate2 = new Gateway("10.0.0.2", 150, 50);
		Switch switch1 = new Switch("10.0.0.3", 150, 250);
		Switch switch2 = new Switch("10.0.0.4", 250, 150);
		EndHost host1 = new EndHost("10.0.0.5", 100, 100);
		EndHost host2 = new EndHost("10.0.0.6", 80, 80);

		NodePath path1 = new NodePath(gate1, switch1);
		NodePath path2 = new NodePath(gate2, switch2);
		NodePath path3 = new NodePath(gate1, gate2);
		NodePath path4 = new NodePath(switch1, switch2);
		NodePath path5 = new NodePath(host1, gate1);
		NodePath path6 = new NodePath(host2, gate1);

		nodes.add(gate1);
		nodes.add(gate2);
		nodes.add(switch1);
		nodes.add(switch2);
		nodes.add(host1);
		nodes.add(host2);

		paths.add(path1);
		paths.add(path2);
		paths.add(path3);
		paths.add(path4);
		paths.add(path5);
		paths.add(path6);

		 for(NodePath path: paths){
		 svg.appendChild(path.getShape());
		 svg.appendChild(path.getShape());
		 }
		
		 for(Node node: nodes){
		 svg.appendChild(node.getShape());
		 svg.appendChild(node.getTextShape());
		 }

		RootPanel rootPanel = RootPanel.get();

		but1 = new Button("panel: x,y");
		but2 = new Button("object: x,y");
		but3 = new Button("object: x,y");

		rootPanel.add(but1);
		rootPanel.add(but2);
		rootPanel.add(but3);
		rootPanel.getElement().appendChild(svg.getElement());	

	}

	public OMSVGSVGElement getSvg() {
		return svg;
	}
}
