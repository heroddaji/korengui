package com.tranhoangdai.korengui.client.imp;

import java.util.ArrayList;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.VectorObjectContainer;
import org.vaadin.gwtgraphics.client.shape.Circle;
import org.vaadin.gwtgraphics.client.shape.Text;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;

public class Node extends Circle implements VectorObjectContainer {

	//group is 
	private Group group = new Group();
	int nodeId;
	String nodeName = "";
	Text textNode = new Text(0, 0, nodeName);	
	private ArrayList<Node> uniNodes = new ArrayList<Node>();
	private ArrayList<NodePath> uniPaths = new ArrayList<NodePath>();

	public Node(int x, int y, int radius) {
		super(x, y, radius);
		init();
		group.add(textNode);

		addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				addMouseDownObjectToCanvas();
			}
		});

		addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				addMouseUpObjectToCanvas();
			}
		});
	}
	
	public void init(){
		//get id
		nodeId = Utility.nextId();		
		textNode.setFontSize(getRadius()/2);		
	}

	// reorganize node position when event happens
	public void reSetUp() {

		// reposition textNode
		textNode.setX(getX() - textNode.getTextWidth()/2);
		textNode.setY(getY() - getRadius() - textNode.getTextHeight()/4);
	}
	
	public boolean canZoom(){
		boolean result = false;
		if (uniNodes.size() > 0){
			result = true;
		}
		
		return result;
	}
	
	public void addUniNode(UniversityNode node){
		uniNodes.add(node);
	}
	
	public ArrayList<Node> getUniNodes(){
		return uniNodes;
	}
	
	public ArrayList<NodePath> getUniPaths(){
		return uniPaths;
	}
	
	public int getNodeId(){
		return nodeId;
	}

	public Group getGroup() {
		return group;
	}

	public void setNodeName(String name) {
		nodeName = name;
		textNode.setText(nodeName);
	}
	

	public void addMouseDownObjectToCanvas() {
		if (NodeCanvas.getInstance() != null) {
			NodeCanvas.getInstance().setRegisteredMouseDownNodeId(nodeId);
		}
	}

	public void addMouseUpObjectToCanvas() {
		if (NodeCanvas.getInstance() != null) {
			NodeCanvas.getInstance().setRegisteredMouseUpNodeId(nodeId);
		}
	}

	@Override
	public VectorObject add(VectorObject vo) {
		return group.add(vo);
	}

	@Override
	public VectorObject insert(VectorObject vo, int beforeIndex) {
		return group.insert(vo, beforeIndex);
	}

	@Override
	public VectorObject remove(VectorObject vo) {
		return group.remove(vo);
	}

	@Override
	public VectorObject bringToFront(VectorObject vo) {
		return group.bringToFront(vo);
	}

	@Override
	public void clear() {
		group.clear();
	}

	@Override
	public int getVectorObjectCount() {
		return group.getVectorObjectCount();
	}

	@Override
	public VectorObject getVectorObject(int index) {
		return group.getVectorObject(index);
	}

}
