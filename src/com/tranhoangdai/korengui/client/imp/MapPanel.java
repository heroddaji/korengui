package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class MapPanel extends SimplePanel {

	public static MapPanel INSTANCE = GWT.create(MapPanel.class);

	OMSVGSVGElement svg = null;
	Node clickedNode = null;
	ArrayList<Node> nodes = new ArrayList<Node>();

	public MapPanel() {
		super();

		OMSVGDocument doc = OMSVGParser.currentDocument();
		svg = doc.createSVGSVGElement();

		svg.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, 700);
		svg.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, 700);

		handleMouseDownEvent();
		handleMouseMoveEvent();
		handleMouseUpEvent();

		Gateway gate1 = new Gateway("10.0.0.1", 150, 150);
		Gateway gate2 = new Gateway("10.0.0.2", 150, 50);
		Switch switch1 = new Switch("10.0.0.3", 150, 250);
		Switch switch2 = new Switch("10.0.0.4", 250, 150);
		NodePath path1 = new NodePath(gate1, switch1);
		NodePath path2 = new NodePath(gate2, switch2);
		NodePath path3 = new NodePath(gate1, gate2);
		NodePath path4 = new NodePath(switch1, switch2);

		svg.appendChild(path1.getShape());
		svg.appendChild(path2.getShape());
		svg.appendChild(path3.getShape());
		svg.appendChild(path4.getShape());
		svg.appendChild(gate1.getShape());
		svg.appendChild(gate2.getShape());
		svg.appendChild(switch1.getShape());
		svg.appendChild(switch2.getShape());

		RootPanel.get().getElement().appendChild(svg.getElement());
		
		nodes.add(gate1);
		nodes.add(gate2);
		nodes.add(switch1);
		nodes.add(switch2);

	}

	private void handleMouseDownEvent() {

		svg.addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				if (clickedNode == null) {
					return;
				}

				clickedNode.move(event.getX(), event.getY());
				clickedNode = null;
			}
		});
	}

	private void handleMouseMoveEvent() {
		svg.addMouseMoveHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (clickedNode == null) {
					return;
				}
				
				clickedNode.move(event.getX(), event.getY());
				for(Node node: nodes){
					
					node.calculateForces();
				}
			}
		});
	}

	private void handleMouseUpEvent() {
		svg.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				if (clickedNode != null) {
					clickedNode = null;
				}
				
			}
		});
	}	
	

	public Node getClickedNode() {
		return clickedNode;
	}

	public void setClickedNode(Node clickedNode) {
		this.clickedNode = clickedNode;
	}

}
