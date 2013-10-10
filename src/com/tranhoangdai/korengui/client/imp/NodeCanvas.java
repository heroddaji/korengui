package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Image;
import org.vaadin.gwtgraphics.client.animation.Animate;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Timer;

@SuppressWarnings("unused")
public class NodeCanvas extends DrawingArea {

	public enum State {
		STATE_0, STATE_MOVE, STATE_ZOOMIN, STATE_ZOOMOUT, STATE_CONNECT
	}

	private static NodeCanvas instance = null;

	public State state = State.STATE_0;
	private int registeredMouseDownNodeId = 0;
	private int registeredMouseUpNodeId = 0;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<NodePath> paths = new ArrayList<NodePath>();
	private ArrayList<Node> zoomStack = new ArrayList<Node>();

	public NodeCanvas(int width, int height) {
		super(width, height);
		instance = this;
		handleStateEvent();
	}

	public void init() {
		example1();
	}

	public void example1() {
		
		
		// setup node1
		Node node1 = new Node(100, 100, 20);
		node1.setFillColor("red");		
		node1.setNodeName("Kyung Hee");

		UniversityNode uniNode1 = new UniversityNode(200, 200, 15);
		uniNode1.setFillColor("yellow");
		uniNode1.setNodeName("OF1");

		UniversityNode uniNode2 = new UniversityNode(100, 300, 15);
		uniNode2.setFillColor("green");
		uniNode2.setNodeName("OF2");

		node1.addUniNode(uniNode1);
		node1.addUniNode(uniNode2);

		Node node2 = new Node(200, 200, 20);
		node2.setFillColor("blue");
		node2.setNodeName("Back Khoa");

		nodes.add(node1);
		nodes.add(node2);

		Utility.setRootNodes(nodes);

		NodePath path = new NodePath(node1.getNodeId(), node2.getNodeId(), true);
		paths.add(path);

		draw();
	}

	public Node getNode(int nodeId) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getNodeId() == nodeId) {
				return nodes.get(i);
			}
		}
		return null;
	}

	public void draw() {

		clear();
		Image im = new Image(200, 200, 200, 200, "file://home/dai-network-lab/Downloads/tiger.svg");
		add(im);
		for (Node n : nodes) {
			n.reSetUp();
			add(n);
			add(n.getGroup());
		}

		for (NodePath p : paths) {
			p.reSetUp();
			add(p);
		}		
	}
	
	public void zoomOut() {
		if (zoomStack.size() > 0) {

			// get the rootnode
			if (zoomStack.size() == 1) {
				nodes = Utility.getRootNodes();
				
				Node backNode = zoomStack.get(0);
				zoomStack.remove(0);
				animateZoomOut(backNode);
				

			} else {
				Node backNode = zoomStack.get(0);
				nodes = backNode.getUniNodes();
				paths = backNode.getUniPaths();
				zoomStack.remove(0);
				animateZoomOut(backNode);
				
			}			
		}
	}

	public void handleStateEvent() {

		addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				if (state == State.STATE_ZOOMIN) {
					if (registeredMouseDownNodeId == -1) {
						return;
					}
					if (getNode(registeredMouseDownNodeId) != null) {

						// push clicked node to zoom-stack
						Node zoomNode = getNode(registeredMouseDownNodeId);
						if (zoomNode.canZoom()) {
							zoomStack.add(zoomNode);

							// replace current nodes,paths with sub-nodes and
							// sub-paths inside the zoom one
							nodes = zoomNode.getUniNodes();
							paths = zoomNode.getUniPaths();

							// reset the clicked node id
							registeredMouseDownNodeId = -1;
							
							animateZoomIn(zoomNode);							
							
						} else {

						}
					}
				}

			}
		});

		addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				if (state == State.STATE_MOVE) {
					if (registeredMouseDownNodeId == -1) {
						return;
					}
					if (getNode(registeredMouseDownNodeId) != null) {
						
						Node clickedNode = getNode(registeredMouseDownNodeId);
						clickedNode.setX(event.getX());
						clickedNode.setY(event.getY());
						clickedNode.reSetUp();
						registeredMouseDownNodeId = -1;

						
					}
				}
				
			}
		});
	}
	public void animateZoomOut(Node node){
		
		final Node clone = new Node(node.getX(), node.getY(), node.getRadius());
		clear();
		add(clone);		
		new Animate(clone, "radius", 800, 0, 888).start();		
		Timer togglebuttonTimer = new Timer() {
			@Override
			public void run() {
				remove(clone);				
				draw();
			} 			     
		}; 
		togglebuttonTimer.schedule(777); 
		
	}
	public void animateZoomIn(Node node){
		final Node clone = new Node(node.getX(), node.getY(), node.getRadius());
		add(clone);
		new Animate(clone, "radius", 0, 800, 888).start();	
		Timer togglebuttonTimer = new Timer() {
			@Override
			public void run() {
				remove(clone);						
				draw();
			} 			     
		}; 
		togglebuttonTimer.schedule(777); 
		
	}

	public static NodeCanvas getInstance() {
		if (instance != null) {
			return instance;
		}
		return null; // bad singleton code, but cannot do the init in this
						// method because the constructor requires width and
						// height
	}

	public static void setInstance(NodeCanvas instance) {
		NodeCanvas.instance = instance;
	}

	public void setRegisteredMouseUpNodeId(int nodeId) {
		this.registeredMouseUpNodeId = nodeId;
	}

	public void setRegisteredMouseDownNodeId(int nodeId) {
		this.registeredMouseDownNodeId = nodeId;
	}

}
