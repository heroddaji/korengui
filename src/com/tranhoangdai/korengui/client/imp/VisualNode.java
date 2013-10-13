package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.OMSVGTransform;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DragEnterEvent;
import com.google.gwt.event.dom.client.DragEnterHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;

@SuppressWarnings("unused")
public class VisualNode extends Node {

	String imageHref = GWT.getModuleBaseURL() + "images/router.svg";
	OMSVGImageElement shape;
	OMSVGTextElement textShape;

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
		shape.addDragEnterHandler(new DragEnterHandler() {

			@Override
			public void onDragEnter(DragEnterEvent event) {

				int a = 0;

			}
		});
	}

	private void handleMouseDownEvent(MouseDownEvent event) {
		OMSVGDocument doc = OMSVGParser.currentDocument();
		MapPanel.INSTANCE.setClickedNode(this);
	}

	@Override
	public void move(int x, int y) {
		super.move(x, y);

		shape.getX().getBaseVal().setValue(x);
		shape.getY().getBaseVal().setValue(y);

		OMSVGSVGElement svg = new OMSVGSVGElement();
		OMSVGLength xCoord = svg.createSVGLength((short) 1, x - shape.getWidth().getBaseVal().getValue() / 4);
		textShape.getX().getBaseVal().clear();
		textShape.getX().getBaseVal().appendItem(xCoord);
		OMSVGLength yCoord = svg.createSVGLength((short) 1, y);
		textShape.getY().getBaseVal().clear();
		textShape.getY().getBaseVal().appendItem(yCoord);

		for (NodePath path : paths.values()) {
			path.adjust();
		}

	}

	@Override
	public OMSVGElement getShape() {
		return shape;
	}

	@Override
	public OMSVGElement getTextShape() {
		return textShape;
	}
}
