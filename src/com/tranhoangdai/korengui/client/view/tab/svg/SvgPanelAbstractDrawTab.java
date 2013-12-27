package com.tranhoangdai.korengui.client.view.tab.svg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vectomatic.dom.svg.OMNode;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.tranhoangdai.korengui.client.model.GeneralModel;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.AbstractPanel;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.svg.AbstractElementSvg;
import com.tranhoangdai.korengui.client.view.svg.HostSvg;
import com.tranhoangdai.korengui.client.view.svg.LinkSvg;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;
import com.tranhoangdai.korengui.client.view.svg.SwitchSvg;
import com.tranhoangdai.korengui.client.view.svg.util.SvgTransformationHelper;
import com.tranhoangdai.korengui.client.view.widget.ContextMenu;
import com.tranhoangdai.korengui.client.view.widget.PanelContextMenu;

@SuppressWarnings("unchecked")
public abstract class SvgPanelAbstractDrawTab extends ScrollPanel implements ContextMenuHandler{

	int id = 0;
	static int uniqueID = 0;

	Map<String,Switch> globalSwitchModels = new HashMap<String, Switch>();
	Map<Integer,Link> globalLinkModels = new HashMap<Integer, Link>();
	private PanelContextMenu contextMenu;

	protected OMSVGSVGElement svgElement = null;
	protected AbstractPanel parent = null;

	protected float center = 0;

	public SvgPanelAbstractDrawTab(AbstractPanel _parent) {
		id = ++uniqueID;
		parent = _parent;
		contextMenu = new PanelContextMenu(this);
		addDomHandler(this, ContextMenuEvent.getType());

		this.setWidth("100%");
		this.setHeight(new Integer(Window.getClientHeight()) + "px");

		svgElement = OMSVGParser.currentDocument().createSVGSVGElement();
		svgElement.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientWidth());
		svgElement.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, Window.getClientHeight());
		this.getElement().appendChild(svgElement.getElement());
	}

	@Override
	public void onContextMenu(ContextMenuEvent event) {
		contextMenu.show(event.getNativeEvent().getClientX(), event.getNativeEvent().getClientY());
		event.preventDefault();
		event.stopPropagation();
	}

	protected Object createSvgElement(Class svgType, GeneralModel model) {
		AbstractElementSvg svg = null;

		if (svgType.equals(SwitchSvg.class)) {
			svg = new SwitchSvg((Switch) model);
		} else if (svgType.equals(NodeSvg.class)) {
			svg = new NodeSvg((Switch) model);
		} else if (svgType.equals(LinkSvg.class)) {
			svg = new LinkSvg((Link) model);
		}

		return svg;
	}

	protected <E, T> List<T> createSvgElements(T svgType, Collection<E> models) {

		List<T> svgs = new ArrayList<T>();
		AbstractElementSvg svg = null;
		for (E mod : models) {
			if (svgType instanceof SwitchSvg) {
				svg = new SwitchSvg((Switch) mod);
			}

			else if (svgType instanceof HostSvg) {
				svg = new HostSvg((Host) mod);
			}

			else if (svgType instanceof NodeSvg) {
				svg = new NodeSvg((Switch) mod);
			}

			else if (svgType instanceof LinkSvg) {
				svg = new LinkSvg((Link) mod);
			}

			svgs.add((T) svg);

		}

		return svgs;
	}

	protected <E> void drawNodeElementsSvg(List<? extends AbstractElementSvg> eSvgs) {
		float radius = SvgPanel.INSTANCE.getOffsetWidth() / 4;
		calCenter();

		float slice = (float) (2 * Math.PI / eSvgs.size());

		int counter = 1;
		try {
			for (AbstractElementSvg element : eSvgs) {

				element.formElement();
				int x = (int) (radius * Math.cos(counter * slice) + center);
				int y = (int) (radius * Math.sin(counter * slice) + center);
				SvgTransformationHelper.translateTo(element, x, y);
				++counter;
				svgElement.appendChild((OMNode) element);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * This method must be call after all the nodes have been drawn * otherwise
	 * it cannot setup the coordinate of line also insert line as first element
	 * to make them stay behind the nodes
	 *
	 * @param <E>
	 */
	protected <E> void drawLinkSvgs(List<LinkSvg> linkSvgs, List<E> nodeSvgs) {

		for (LinkSvg linkSvg : linkSvgs) {
			linkSvg.findAndMatchMultipleNodes(nodeSvgs);
			svgElement.getElement().insertFirst(linkSvg.getElement());
		}
	}

	protected void calCenter() {
		if (SvgPanel.INSTANCE.getOffsetHeight() < SvgPanel.INSTANCE.getOffsetWidth()) {
			center = SvgPanel.INSTANCE.getOffsetHeight() / 2;
		} else {
			center = SvgPanel.INSTANCE.getOffsetWidth() / 2;
		}
	}

	protected abstract void draw();

	public void setGlobalSwitchModels(Map<String, Switch> globalSwitchModels) {
		this.globalSwitchModels = globalSwitchModels;
	}

	public void setGlobalLinkModels(Map<Integer, Link> globalLinkModels) {
		this.globalLinkModels = globalLinkModels;
	}

	public int getId() {
		return id;
	}

}
