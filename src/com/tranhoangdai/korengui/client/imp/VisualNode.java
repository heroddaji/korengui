package com.tranhoangdai.korengui.client.imp;

import java.util.Map;

import org.apache.tools.ant.taskdefs.condition.Os;
import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGMatrix;
import org.vectomatic.dom.svg.OMSVGPoint;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.OMSVGTransform;
import org.vectomatic.dom.svg.OMSVGTransformList;
import org.vectomatic.dom.svg.OMText;
import org.vectomatic.dom.svg.utils.DOMHelper;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DragEnterEvent;
import com.google.gwt.event.dom.client.DragEnterHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.tranhoangdai.korengui.client.MapPanel;

@SuppressWarnings("unused")
public class VisualNode extends Node {

	String imageHref = GWT.getModuleBaseURL() + "images/router.svg";
	OMSVGImageElement shape;	
	boolean dragging = false;
	OMSVGPoint beforeMovePoint = null;
	private boolean isScaleUp = false;
	public VisualNode() {
	}

	public VisualNode(String ip, int x, int y) {
		super(ip, x, y);
		shape = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
		
	}

	protected void setupEventHandler() {
		shape.addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				handleMouseDownEvent(event);
			}
		});

		shape.addMouseMoveHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				handleMouseMoveEvent(event);
			}
		});

		shape.addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				handleMouseUpEvent(event);
			}
		});

		shape.addMouseOverHandler(new MouseOverHandler() {

			@Override
			public void onMouseOver(MouseOverEvent event) {
				scaleUp(event);
			}
		});

		shape.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				scaleDown(event);
			}
		});
	}
	
	private void handleMouseDownEvent(MouseDownEvent event) {
		dragging = true;
		beforeMovePoint = getLocalCoordinates(event);
		DOMHelper.setCaptureElement(shape, null);
		event.stopPropagation();
		event.preventDefault();

	}

	private void handleMouseMoveEvent(MouseMoveEvent event) {
		if (!dragging) {
			return;
		}
		move(event);
		
		event.stopPropagation();
		event.preventDefault();
	}

	private void handleMouseUpEvent(MouseUpEvent event) {
		dragging = false;
		DOMHelper.releaseCaptureElement();
		
		if(isScaleUp){
			scaleDown(event);
		}
		
		event.stopPropagation();
		event.preventDefault();
	}

	private void scaleDown(MouseEvent<?> event) {
		isScaleUp = false;
		OMSVGSVGElement svg = MapPanel.INSTANCE.getSvg();
		float x = ((shape.getX().getBaseVal().getValue() + shape.getWidth().getBaseVal().getValue() / 2) * (1 - scaleFactor)) / scaleFactor;
		float y = ((shape.getY().getBaseVal().getValue() + shape.getHeight().getBaseVal().getValue() / 2) * (1 - scaleFactor)) / scaleFactor;
		OMSVGTransform t1 = shape.getOwnerSVGElement().createSVGTransformFromMatrix(svg.getCTM());
		t1.setTranslate(-x, -y);
		OMSVGTransform t2 = shape.getOwnerSVGElement().createSVGTransformFromMatrix(svg.getCTM());
		t2.setScale(1.0f / scaleFactor, 1.0f / scaleFactor);
		shape.getTransform().getBaseVal().appendItem(t1);
		shape.getTransform().getBaseVal().appendItem(t2);

		event.stopPropagation();
		event.preventDefault();
	}

	private void scaleUp(MouseEvent<?> event) {
		isScaleUp = true;
		OMSVGSVGElement svg = MapPanel.INSTANCE.getSvg();
		float x = (shape.getX().getBaseVal().getValue() + shape.getWidth().getBaseVal().getValue() / 2) * (scaleFactor - 1);
		float y = (shape.getY().getBaseVal().getValue() + shape.getHeight().getBaseVal().getValue() / 2) * (scaleFactor - 1);
		OMSVGTransform t1 = shape.getOwnerSVGElement().createSVGTransformFromMatrix(svg.getCTM());
		t1.setTranslate(-x, -y);
		OMSVGTransform t2 = shape.getOwnerSVGElement().createSVGTransformFromMatrix(svg.getCTM());
		t2.setScale(scaleFactor, scaleFactor);
		shape.getTransform().getBaseVal().appendItem(t1);
		shape.getTransform().getBaseVal().appendItem(t2);
		event.stopPropagation();
		event.preventDefault();
	}

	private OMSVGPoint getLocalCoordinates(MouseEvent<?> event) {
		OMSVGPoint p = MapPanel.INSTANCE.getSvg().createSVGPoint(event.getClientX(), event.getClientY());
		GWT.log("client point:" + p.getDescription());
		OMSVGMatrix matrix = shape.getScreenCTM().inverse();

		OMSVGPoint p1 = p.matrixTransform(matrix);
		GWT.log("client point transform:" + p1.getDescription());
		return p1;
	}

	@Override
	public OMSVGElement getShape() {
		return shape;
	}

	@Override
	public void move(MouseEvent<?> event) {

		// // move shape
		// OMSVGPoint afterMovePoint = getLocalCoordinates(event);
		// float dx = afterMovePoint.getX() - beforeMovePoint.getX();
		// float dy = afterMovePoint.getY() - beforeMovePoint.getY();
		// float x = shape.getX().getBaseVal().getValue();
		// GWT.log("shape base val:" + x + "," + y);
		// float y = shape.getY().getBaseVal().getValue();
		//
		// shape.getX().getBaseVal().setValue(x + dx);
		// shape.getY().getBaseVal().setValue(y + dy);
		//
		// moveText(x, y, event);
		//
		// // adjust connected paths
		// for (NodeLink path : paths.values()) {
		// path.adjust();
		// }
		//
		// beforeMovePoint = afterMovePoint;
		// MapPanel.INSTANCE.but1.setText("client coor:" + event.getClientX() +
		// "," + event.getClientY());
		// MapPanel.INSTANCE.but2.setText("target coor" + event.getX() + "," +
		// event.getY());
		// GWT.log("relavetove coor:" +
		// event.getRelativeX(MapPanel.INSTANCE.getSvg().getElement()) + "," +
		// event.getRelativeY(MapPanel.INSTANCE.getSvg().getElement()));
		//
		// MapPanel.INSTANCE.but3.setText("screen coor" + event.getScreenX() +
		// "," + event.getScreenY());

	}

	public void adjustText(float x, float y) {

		// move text method 1
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		OMSVGLength xCoord = svg.createSVGLength(OMSVGLength.SVG_LENGTHTYPE_PX, x - shape.getWidth().getBaseVal().getValue() / 4);
		textShape.getX().getBaseVal().clear();
		textShape.getX().getBaseVal().appendItem(xCoord);
		OMSVGLength yCoord = svg.createSVGLength(OMSVGLength.SVG_LENGTHTYPE_PX, y);
		textShape.getY().getBaseVal().clear();
		textShape.getY().getBaseVal().appendItem(yCoord);
		// textShape.get
		// textShape.getElement().setInnerText(ipAddress); text is too big
		// OMText textValue = (OMText) textShape.getFirstChild();
		// textValue.setData(ipAddress+"hjhj");

		// method 2
		// OMSVGPoint afterMovePoint = getLocalCoordinates(event);
		// OMSVGSVGElement svg2 = textShape.getOwnerSVGElement();
		// OMSVGTransformList transforms =
		// textShape.getTransform().getBaseVal();
		// OMSVGTransform t = svg2.createSVGTransform();
		// t.setTranslate(event.getX(), event.getY());
		// transforms.appendItem(t);

		//
		// OMSVGTransformList transforms = gElement.getTransform().getBaseVal();
		// OMSVGTransform t = svg.createSVGTransform();
		// t.setTranslate(50, 50);
		// transforms.appendItem(t);
		// OMSVGTransform s = svg.createSVGTransform();
		// s.setScale(1, 1);
		// transforms.appendItem(s);
	}

	@Override
	public void setX(float x) {
		shape.getX().getBaseVal().setValue(x);

	}

	public void setY(float y) {
		shape.getY().getBaseVal().setValue(y);

	}
}
