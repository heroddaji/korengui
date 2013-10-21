package com.tranhoangdai.korengui.client.imp;

import java.util.Map;

import org.apache.tools.ant.taskdefs.condition.Os;
import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGGElement;
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

	String imageHref = "images/router.svg";
	OMSVGImageElement shape;
	boolean dragging = false;
	OMSVGPoint beforeMovePoint = null;
	private boolean isScaleUp = false;

	public VisualNode() {
	}

	public VisualNode(String ip, int x, int y) {
		super(ip, x, y);

	}

	protected void setupShape() {
		shape = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
	}

	protected void setupTextShape() {
		textShape = new OMSVGTextElement(x, y, OMSVGLength.SVG_LENGTHTYPE_PX, dpid);
		// move text to middle of shape
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		int fontsize = 9;
		textShape.setAttribute("font-size", new Integer(fontsize).toString());
		float midShapeX = shape.getWidth().getBaseVal().getValue() / 2;
		int halfTextLength = dpid.length() * fontsize / 4;

		double moveLength = halfTextLength - midShapeX;

		// move text method 1
		OMSVGLength xCoord = svg.createSVGLength(OMSVGLength.SVG_LENGTHTYPE_PX, (float) (x - moveLength));
		textShape.getX().getBaseVal().clear();
		textShape.getX().getBaseVal().appendItem(xCoord);

		OMSVGLength yCoord = svg.createSVGLength(OMSVGLength.SVG_LENGTHTYPE_PX, y + fontsize/2);
		textShape.getY().getBaseVal().clear();
		textShape.getY().getBaseVal().appendItem(yCoord);

	}

	protected void setupGroupShape() {
		groupShape = new OMSVGGElement();
		groupShape.appendChild(shape);
		groupShape.appendChild(textShape);		
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

		if (isScaleUp) {
			scaleDown(event);
		}

		event.stopPropagation();
		event.preventDefault();
	}

	private void scaleDown(MouseEvent<?> event) {
		isScaleUp = false;
		
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		float x = (shape.getX().getBaseVal().getValue()+ shape.getWidth().getBaseVal().getValue() / 2) * (1 -scaleFactor);
		float y = (shape.getX().getBaseVal().getValue() + shape.getHeight().getBaseVal().getValue() / 2) * (1 -scaleFactor);
		OMSVGTransform t1 = svg.createSVGTransform();		
		OMSVGTransform t2 = svg.createSVGTransform();		
		OMSVGTransformList xforms = groupShape.getTransform().getBaseVal();
		xforms.appendItem(t1);
		xforms.appendItem(t2);
		t1.setTranslate(-x, -y);
		t2.setScale(1.0f / scaleFactor, 1.0f / scaleFactor);

		event.stopPropagation();
		event.preventDefault();
	}

	private void scaleUp(MouseEvent<?> event) {
		isScaleUp = true;
		
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		float x = (shape.getX().getBaseVal().getValue()+ shape.getWidth().getBaseVal().getValue() / 2) * (scaleFactor - 1);
		float y = (shape.getX().getBaseVal().getValue() + shape.getHeight().getBaseVal().getValue() / 2) * (scaleFactor - 1);
		OMSVGTransform t1 = svg.createSVGTransform();		
		OMSVGTransform t2 = svg.createSVGTransform();		
		OMSVGTransformList xforms = groupShape.getTransform().getBaseVal();
		xforms.appendItem(t1);
		xforms.appendItem(t2);
		t1.setTranslate(-x, -y);
		t2.setScale(scaleFactor, scaleFactor);		
		
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
	public OMSVGElement getGroupShape() {
		return groupShape;
	}

	@Override
	public void move(MouseEvent<?> event) {

	}

	public void translateTo(float x, float y) {
		setX(x);
		setY(y);
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		OMSVGTransform t;
		t = svg.createSVGTransform();
		OMSVGTransformList xforms = groupShape.getTransform().getBaseVal();
		xforms.appendItem(t);
		t.setTranslate(x, y);

	}

	@Override
	public void setX(float x) {
		this.x = x;

	}

	public void setY(float y) {
		this.y = y;

	}
}
