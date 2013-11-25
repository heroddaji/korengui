package com.tranhoangdai.korengui.client.helper;

import org.vectomatic.dom.svg.OMSVGImageElement;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.OMSVGTransform;
import org.vectomatic.dom.svg.OMSVGTransformList;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.tranhoangdai.korengui.client.model.Node;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.SvgPanelZoomTab;
import com.tranhoangdai.korengui.client.view.svg.Cluster;
import com.tranhoangdai.korengui.client.view.svg.EndHost;
import com.tranhoangdai.korengui.client.view.svg.Gateway;
import com.tranhoangdai.korengui.client.view.svg.Switch;
import com.tranhoangdai.korengui.client.view.svg.ZoomableNode;

public class SvgUtility {
	public static OMSVGImageElement scaleUp(OMSVGImageElement shape, float centerLocation, float scaleFactor) {

		OMSVGTransform t1 = null;
		OMSVGTransform t2 = null;
		OMSVGSVGElement svg = OMSVGParser.currentDocument().createSVGSVGElement();

		// float scaleUpX = ((centerLocation +
		// shape.getWidth().getBaseVal().getValue() / 2) * (scaleFactor - 1));
		// float scaleUpY = ((centerLocation +
		// shape.getHeight().getBaseVal().getValue() / 2) * (scaleFactor - 1));
		float scaleUpX = shape.getX().getBaseVal().getValue();
		float scaleUpY = shape.getY().getBaseVal().getValue();

		// t1 = svg.createSVGTransform();
		t2 = svg.createSVGTransform();
		OMSVGTransformList xforms = shape.getTransform().getBaseVal();
		// xforms.appendItem(t1);
		xforms.appendItem(t2);
		// t1.setTranslate(-scaleUpX, -scaleUpY);
		t2.setScale(scaleFactor, scaleFactor);

		return shape;
	}

	public static boolean checkIfZoomNodeExist(ZoomableNode node) {
		boolean result = false;

		for (SvgPanelZoomTab zt : SvgPanel.INSTANCE.getZoomTabs()) {
			if (zt.getZoomNode() != null && zt.getZoomNode().equals(node)) {
				result = true;
				break;
			}
		}

		return result;
	}
	
	

	public static Node cloneNode(Node node){
		Node returnNode = null;
		if(node instanceof Switch){
			returnNode = new Switch(node.getDpid(), node.getX(), node.getY());
		}else if(node instanceof Gateway){
			returnNode = new Gateway(node.getDpid(), node.getX(), node.getY());
		}
		else if(node instanceof EndHost){
			returnNode = new EndHost(node.getDpid(), node.getX(), node.getY());
		}
		else if(node instanceof Cluster){
			returnNode = new Cluster(node.getDpid(), node.getX(), node.getY());
		}		
		
		return returnNode;
	}
	
	
}
