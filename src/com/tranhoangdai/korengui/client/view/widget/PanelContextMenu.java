package com.tranhoangdai.korengui.client.view.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.EventBus;

public class PanelContextMenu extends ContextMenu{

	public PanelContextMenu(Widget _parent) {
		super(_parent);
		Button closeBtn = new Button("Close tab");
		closeBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EventBus.INSTANCE.deliverEventUserClickCloseTabContextMenu(parent);
				hide();
			}
		});

		SvgElementTooltip tooltip = new SvgElementTooltip();
		addPopupElement(tooltip);
		addPopupElement(closeBtn);
		hide();

		addDomHandler(this, MouseOutEvent.getType());
	}

}
