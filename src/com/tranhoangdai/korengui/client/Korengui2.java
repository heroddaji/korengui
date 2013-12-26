package com.tranhoangdai.korengui.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vectomatic.dom.svg.OMNode;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.TabPane;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;
import com.tranhoangdai.korengui.client.ui.AboutBox;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.svg.AbstractElementSvg;
import com.tranhoangdai.korengui.client.view.svg.HostSvg;
import com.tranhoangdai.korengui.client.view.svg.LinkSvg;
import com.tranhoangdai.korengui.client.view.svg.NodeSvg;
import com.tranhoangdai.korengui.client.view.svg.SwitchSvg;
import com.tranhoangdai.korengui.client.view.svg.util.SvgTransformationHelper;

public class Korengui2 extends Composite {

	private static Korengui2UiBinder uiBinder = GWT.create(Korengui2UiBinder.class);

	interface Korengui2UiBinder extends UiBinder<Widget, Korengui2> {
	}

	@UiField
	NavLink topologyBtn;

	@UiField
	TabPanel rightTabPanel1;

	@UiField
	TabPane globalTab;

	@UiField
	HTMLPanel hero;

	@UiField
	Button aboutBtn;

	@UiField
	AboutBox aboutBox;

	float center = 0;
	protected OMSVGSVGElement svgElement = null;

	public Korengui2() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("aboutBtn")
	void onUserClick(ClickEvent e){
		aboutBox.show();
	}

	@UiHandler("topologyBtn")
	void onClick(ClickEvent e) {

		SvgPanel svgPanel = SvgPanel.INSTANCE;
		EventBus.INSTANCE.deliverDownloadGlobalTopologyEvent(hero);

		svgElement = OMSVGParser.currentDocument().createSVGSVGElement();
		svgElement.setWidth(OMSVGLength.SVG_LENGTHTYPE_PX, hero.getElement().getClientWidth());
		svgElement.setHeight(OMSVGLength.SVG_LENGTHTYPE_PX, hero.getElement().getClientHeight());

		NodeSvg nodeSvgClass = new NodeSvg(null);
		LinkSvg linkSvgClass = new LinkSvg(null);
		List<NodeSvg> nodeSvgs = createSvgElements(nodeSvgClass, ClientServiceHelper.INSTANCE.getTopologySwitches().values());
		drawNodeElementsSvg(nodeSvgs);

		if(hero.getElement().getChildCount() > 0){
			hero.getElement().removeChild(hero.getElement().getLastChild());
		}
		hero.getElement().appendChild(svgElement.getElement());
	}

	protected <E> void drawNodeElementsSvg(List<? extends AbstractElementSvg> eSvgs) {
		float radius = 200;
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

	protected void calCenter() {
		if (SvgPanel.INSTANCE.getOffsetHeight() < SvgPanel.INSTANCE.getOffsetWidth()) {
			center = SvgPanel.INSTANCE.getOffsetHeight() / 2;
		} else {
			center = SvgPanel.INSTANCE.getOffsetWidth() / 2;
		}
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
}
