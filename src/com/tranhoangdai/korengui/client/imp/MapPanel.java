package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.ui.SVGImage;
import org.vectomatic.dom.svg.ui.SVGResource;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class MapPanel extends SimplePanel {

	public MapPanel() {
		super();

		OMSVGDocument doc = OMSVGParser.currentDocument();
		OMSVGSVGElement svg = doc.createSVGSVGElement();
		
		svg.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, 300);
		svg.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, 300);
		
//		OMSVGCircleElement circle = doc.createSVGCircleElement(150, 150, 200);
//		circle.getStyle().setSVGProperty(SVGConstants.CSS_FILL_PROPERTY, "blue");
		//svg.appendChild(circle);
		
		OMSVGImageElement 	image = new OMSVGImageElement(0, 0, 200, 200, GWT.getModuleBaseURL() + "images/switch.svg");
		
		svg.appendChild(image);
		
		RootPanel.get().getElement().appendChild(svg.getElement());
	}

}
