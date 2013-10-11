package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGLineElement;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;
import org.vectomatic.dom.svg.utils.SVGConstants;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class MapPanel extends SimplePanel {
	
	OMSVGSVGElement svg = null;
	Node clickedNode = null;
	public MapPanel() {
		super();

		OMSVGDocument doc = OMSVGParser.currentDocument();
		svg = doc.createSVGSVGElement();

		svg.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, 700);
		svg.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, 700);

		Gateway gate1 = new Gateway("10.0.0.1", 150, 150);
		Gateway gate2 = new Gateway("10.0.0.2", 150, 50);
		Switch switch1 = new Switch("10.0.0.3", 150, 250);
		Switch switch2 = new Switch("10.0.0.4", 250, 150);
		NodePath path1 = new NodePath(gate1, switch1);
		NodePath path2 = new NodePath(gate2, switch2);

		svg.appendChild(path1.getShape());
		svg.appendChild(path2.getShape());
		svg.appendChild(gate1.getShape());
		svg.appendChild(gate2.getShape());
		svg.appendChild(switch1.getShape());
		svg.appendChild(switch2.getShape());

		RootPanel.get().getElement().appendChild(svg.getElement());
		
		svg.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				if(clickedNode == null){
					return;
				}
				
				(OMsvgclickedNode
			}
		});
	}
	

}
