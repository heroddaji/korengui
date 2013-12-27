package com.tranhoangdai.korengui.client.view.svg;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGMatrix;
import org.vectomatic.dom.svg.OMSVGPoint;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.OMSVGTransform;
import org.vectomatic.dom.svg.OMSVGTransformList;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.EventBus;
import com.tranhoangdai.korengui.client.Korengui;
import com.tranhoangdai.korengui.client.model.GeneralModel;
import com.tranhoangdai.korengui.client.model.ModelWithId;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.svg.util.SvgTransformationHelper;
import com.tranhoangdai.korengui.client.view.widget.SvgElementTooltip;

public class NodeSvg extends AbstractElementSvg{

	protected  int WIDTH = 60;
	protected  int HEIGHT = 60;
	protected  int x;
	protected  int y;
	protected int scaleFactor = 2;
	protected ModelWithId nodeModel;
	protected String imageHref = "images/router.svg";
	protected OMSVGImageElement shape;
	protected  OMSVGTextElement textShape;
	protected boolean dragging = false;
	protected OMSVGPoint beforeMovePoint = null;
	protected boolean isScaleUp = false;

	public NodeSvg(){

	}

	public NodeSvg(GeneralModel _model){
		this.nodeModel = (Switch) _model;
	}

	public void formElement(){
		setupShape();
		setupTextShape();
		setupEventHandler();
		setupGroupShape();
	}

	protected void setupShape() {
		shape = new OMSVGImageElement(x, y, WIDTH, HEIGHT, imageHref);
	}

	protected void setupTextShape() {

		Switch model = (Switch)nodeModel;
		textShape = new OMSVGTextElement(x, y, OMSVGLength.SVG_LENGTHTYPE_PX, model.getId());
		// move text to middle of shape
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		int fontsize = 9;
		textShape.setAttribute("font-size", new Integer(fontsize).toString());
		float midShapeX = shape.getWidth().getBaseVal().getValue() / 2;
		int halfTextLength = model.getId().length() * fontsize / 4;

		double moveLength = halfTextLength - midShapeX;

		// move text method 1
		OMSVGLength xCoord = svg.createSVGLength(OMSVGLength.SVG_LENGTHTYPE_PX, (float) (x - moveLength));
		textShape.getX().getBaseVal().clear();
		textShape.getX().getBaseVal().appendItem(xCoord);

		OMSVGLength yCoord = svg.createSVGLength(OMSVGLength.SVG_LENGTHTYPE_PX, y + fontsize / 2);
		textShape.getY().getBaseVal().clear();
		textShape.getY().getBaseVal().appendItem(yCoord);

	}

	protected void setupGroupShape() {
		appendChild(shape);
		appendChild(textShape);
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
				displayTooltip();
			}
		});

		shape.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				scaleDown(event);
			}
		});
	}

	protected void displayTooltip() {


	}

	protected void handleMouseDownEvent(MouseDownEvent event) {

		Korengui.INSTANCE.getEventBus().deliverEventUserClickedNode(this);
		event.stopPropagation();
		event.preventDefault();
	}

	protected void handleMouseMoveEvent(MouseMoveEvent event) {
	}

	protected void handleMouseUpEvent(MouseUpEvent event) {
		// dragging = false;
		// DOMHelper.releaseCaptureElement();

		// if (isScaleUp) {
		// scaleDown(event);
		// }
		//
		// event.stopPropagation();
		// event.preventDefault();
	}

	boolean fisrtTimeScaleUp = true, fisrtTimeScaleDown = true;
	float scaleUpX = 0, scaleUpY = 0, scaleDownX = 0, scaleDownY = 0;
	float scaleDownFactor;

	protected void scaleDown(MouseEvent<?> event) {
		isScaleUp = false;

		if (fisrtTimeScaleDown) {
			scaleDownX = ((shape.getX().getBaseVal().getValue() + shape.getWidth().getBaseVal().getValue() / 2) * (1 - scaleFactor));
			scaleDownY = ((shape.getY().getBaseVal().getValue() + shape.getHeight().getBaseVal().getValue() / 2) * (1 - scaleFactor));
			scaleDownFactor = 1.0f / scaleFactor;
			fisrtTimeScaleDown = false;
		}

		SvgTransformationHelper.scaleDown(this, scaleDownX, scaleDownY, scaleDownFactor);

//		OMSVGTransform t1 = null;
//		OMSVGTransform t2 = null;
//		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
//		t1 = svg.createSVGTransform();
//		t2 = svg.createSVGTransform();
//		OMSVGTransformList xforms = getTransform().getBaseVal();
//		xforms.appendItem(t2);
//		xforms.appendItem(t1);
//		t2.setScale(scaleDownFactor, scaleDownFactor);
//		t1.setTranslate(-scaleDownX, -scaleDownY);

		event.stopPropagation();
		event.preventDefault();
	}

	protected void scaleUp(MouseEvent<?> event) {
		isScaleUp = true;

		if (fisrtTimeScaleUp) {
			scaleUpX = ((shape.getX().getBaseVal().getValue() + shape.getWidth().getBaseVal().getValue() / 2) * (scaleFactor - 1));
			scaleUpY = ((shape.getX().getBaseVal().getValue() + shape.getHeight().getBaseVal().getValue() / 2) * (scaleFactor - 1));
			fisrtTimeScaleUp = false;
		}

		SvgTransformationHelper.scaleUp(this, scaleUpX, scaleUpY, scaleFactor);


//		OMSVGTransform t1 = null;
//		OMSVGTransform t2 = null;
//		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
//
//
//		t1 = svg.createSVGTransform();
//		t2 = svg.createSVGTransform();
//		OMSVGTransformList xforms = getTransform().getBaseVal();
//		xforms.appendItem(t1);
//		xforms.appendItem(t2);
//		t1.setTranslate(-scaleUpX, -scaleUpY);
//		t2.setScale(scaleFactor, scaleFactor);

		event.stopPropagation();
		event.preventDefault();
	}

	protected OMSVGPoint getLocalCoordinates(MouseEvent<?> event) {
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		OMSVGPoint p = svg.createSVGPoint(event.getClientX(), event.getClientY());
		GWT.log("client point:" + p.getDescription());
		OMSVGMatrix matrix = shape.getScreenCTM().inverse();

		OMSVGPoint p1 = p.matrixTransform(matrix);
		GWT.log("client point transform:" + p1.getDescription());
		return p1;
	}

	public OMSVGElement getShape() {
		return shape;
	}

	@Override
	public OMSVGElement getText() {
		return textShape;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public ModelWithId getModel() {
		return nodeModel;
	}


}
