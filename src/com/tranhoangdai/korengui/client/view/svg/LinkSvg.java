package com.tranhoangdai.korengui.client.view.svg;

import java.util.List;

import org.vectomatic.dom.svg.OMSVGElement;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLineElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.utils.SVGConstants;

import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.ModelWithId;
import com.tranhoangdai.korengui.client.model.Switch;

public class LinkSvg extends AbstractElementSvg {

	Link linkModel = null;
	AbstractElementSvg srcNodeSvg = null;
	AbstractElementSvg dstNodeSvg = null;
	OMSVGTextElement textShapeSrcPort;
	OMSVGTextElement textShapeDstPort;
	OMSVGLineElement line = null;

	public LinkSvg() {

	}

	public LinkSvg(Link model) {
		linkModel = model;
	}

	public <E> void findAndMatchMultipleNodes(List<E> svgs) {
		// use brute-force to find nodes, since the list is quite small
		for (E e : svgs) {
			AbstractElementSvg eSvg = (AbstractElementSvg) e;
			ModelWithId model = eSvg.getModel();
			if (model.getId().equals(linkModel.getSrcSwitch())) {
				srcNodeSvg = eSvg;
			}
			if (model.getId().equals(linkModel.getDstSwitch())) {
				dstNodeSvg = eSvg;
			}
		}

		formElement();
	}

	public <T, E> void findAndMatchZoomNode(T zoomElementSvg, E svgElement) {		
		srcNodeSvg = (AbstractElementSvg) svgElement;
		dstNodeSvg = (AbstractElementSvg) zoomElementSvg;
		formElement();
	}

	@Override
	public OMSVGElement getShape() {
		return line;
	}

	@Override
	public OMSVGElement getText() {
		// TODO Auto-igenerated method stub
		return null;
	}

	@Override
	public Link getModel() {
		return linkModel;
	}

	@Override
	public void formElement() {
		if (srcNodeSvg == null || dstNodeSvg == null) {
			return;
		}

		float x1 = srcNodeSvg.getX();
		float y1 = srcNodeSvg.getY();
		float width1 = ((OMSVGImageElement) srcNodeSvg.getShape()).getWidth().getBaseVal().getValue();
		float height1 = ((OMSVGImageElement) srcNodeSvg.getShape()).getHeight().getBaseVal().getValue();

		float x2 = dstNodeSvg.getX();
		float y2 = dstNodeSvg.getY();
		float width2 = ((OMSVGImageElement) dstNodeSvg.getShape()).getWidth().getBaseVal().getValue();
		float height2 = ((OMSVGImageElement) dstNodeSvg.getShape()).getHeight().getBaseVal().getValue();

		if (line == null) {
			line = new OMSVGLineElement(x1, y1, x2, y2);
			appendChild(line);
			line.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
		}

		line.getX1().getBaseVal().setValue(x1 + width1 / 2);
		line.getY1().getBaseVal().setValue(y1 + height1 / 2);
		line.getX2().getBaseVal().setValue(x2 + width2 / 2);
		line.getY2().getBaseVal().setValue(y2 + height2 / 2);

	}

	@Override
	public int getX() {
		return 0;

	}

	@Override
	public int getY() {
		return 0;

	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

}
