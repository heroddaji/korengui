package com.tranhoangdai.korengui.client.ui.svg;

import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTextElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.tranhoangdai.korengui.client.model.GeneralModel;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Switch;

public class HostSvg extends NodeSvg {

	private Host hostModel;

	public HostSvg() {
	}

	public HostSvg(Host model) {
		this.hostModel = model;
		imageHref = "images/endhost.svg";
	}

	@Override
	protected void setupTextShape() {		
		textShape = new OMSVGTextElement(x, y, OMSVGLength.SVG_LENGTHTYPE_PX, hostModel.getMacAddress());
		// move text to middle of shape
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();
		int fontsize = 9;
		textShape.setAttribute("font-size", new Integer(fontsize).toString());
		float midShapeX = shape.getWidth().getBaseVal().getValue() / 2;
		int halfTextLength = hostModel.getMacAddress().length() * fontsize / 4;

		double moveLength = halfTextLength - midShapeX;

		// move text method 1
		OMSVGLength xCoord = svg.createSVGLength(OMSVGLength.SVG_LENGTHTYPE_PX, (float) (x - moveLength));
		textShape.getX().getBaseVal().clear();
		textShape.getX().getBaseVal().appendItem(xCoord);

		OMSVGLength yCoord = svg.createSVGLength(OMSVGLength.SVG_LENGTHTYPE_PX, y + fontsize / 2);
		textShape.getY().getBaseVal().clear();
		textShape.getY().getBaseVal().appendItem(yCoord);

	}

	@Override
	public Host getModel() {
		return hostModel;
	}

}
